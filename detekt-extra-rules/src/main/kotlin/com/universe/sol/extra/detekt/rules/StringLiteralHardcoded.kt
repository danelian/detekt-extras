package com.universe.sol.extra.detekt.rules

import io.gitlab.arturbosch.detekt.api.*
import org.jetbrains.kotlin.psi.*

class StringLiteralHardcoded(
        config: Config = Config.empty
) : ThresholdRule(config, 1) {

    override val issue = Issue(javaClass.simpleName, Severity.Maintainability,
            "Multiple occurrences of the same string literal within a single file detected.",
            Debt.FIVE_MINS)

    private val ignoreAnnotation = valueOrDefault(IGNORE_ANNOTATION, true)
    private val ignoreLogMessages = valueOrDefault(IGNORE_LOG_MESSAGES, true)
    private val ignoreAssert = valueOrDefault(IGNORE_ASSERTS, true)
    private val excludeStringsWithLessThan5Characters = valueOrDefault(EXCLUDE_SHORT_STRING, true)
    private val ignoreStringsRegex = Regex(valueOrDefault(IGNORE_STRINGS_REGEX, "$^"))

    override fun visitKtFile(file: KtFile) {
        val visitor = StringLiteralVisitor()
        file.accept(visitor)
        val type = "SIZE: "
        for ((name, value) in visitor.getLiteralsOverThreshold()) {
            val (main, references) = visitor.entitiesForLiteral(name)
            report(ThresholdedCodeSmell(issue,
                    main,
                    Metric(type + name, value, 1),
                    "",
                    references))
        }
    }

    internal inner class StringLiteralVisitor : DetektVisitor() {

        private var literals = HashMap<String, Int>()
        private var literalReferences = HashMap<String, MutableList<KtLiteralStringTemplateEntry>>()
        private val pass: Unit = Unit

        fun getLiteralsOverThreshold(): Map<String, Int> = literals.filterValues { it >= 1 }


        fun entitiesForLiteral(literal: String): Pair<Entity, List<Entity>> {
            val references = literalReferences[literal]
            if (references != null && references.isNotEmpty()) {
                val mainEntity = references[0]
                val referenceEntities = references.subList(1, references.size)
                return Entity.from(mainEntity) to referenceEntities.map { Entity.from(it) }
            }
            throw IllegalStateException("No KtElements for literal '$literal' found!")
        }

        override fun visitLiteralStringTemplateEntry(entry: KtLiteralStringTemplateEntry) {
            when {
                ignoreAnnotation && entry.isPartOf(KtAnnotationEntry::class) -> pass
                entry.isPartOf(KtProperty::class)
                        || isBinaryExpression(entry)
                        || entry.isPartOf(KtThrowExpression::class) -> pass
                ignoreLogMessages && isLog(entry) -> pass
                ignoreAssert && isAssert(entry) -> pass
                excludeStringsWithLessThan5Characters && entry.text.length < STRING_EXCLUSION_LENGTH -> pass
                entry.text.matches(ignoreStringsRegex) -> pass
                else -> add(entry)
            }
        }

        private fun isAssert(entry: KtLiteralStringTemplateEntry): Boolean {
            val methodName = entry.parent?.parent?.parent?.parent?.firstChild?.text
            return "assertEquals" == methodName
        }

        private fun isLog(entry: KtLiteralStringTemplateEntry): Boolean {
            val methodName = entry.parent?.parent?.parent?.parent?.parent?.firstChild?.text
            return "Log" == methodName || "Timber" == methodName
        }

        private fun isBinaryExpression(entry: KtLiteralStringTemplateEntry): Boolean {
            return entry.parent?.parent is KtBinaryExpression
        }

        private fun add(entry: KtLiteralStringTemplateEntry) {
            val text = entry.text
            literals.compute(text) { _, oldValue -> oldValue?.plus(1) ?: 1 }
            literalReferences.compute(text) { _, entries ->
                entries?.add(entry); entries ?: mutableListOf(entry)
            }
        }
    }

    companion object {

        const val STRING_EXCLUSION_LENGTH = 5
        const val IGNORE_LOG_MESSAGES = "ignoreLogMessages"
        const val IGNORE_ASSERTS = "ignoreAsserts"
        const val IGNORE_ANNOTATION = "ignoreAnnotation"
        const val EXCLUDE_SHORT_STRING = "excludeStringsWithLessThan5Characters"
        const val IGNORE_STRINGS_REGEX = "ignoreStringsRegex"
    }
}
