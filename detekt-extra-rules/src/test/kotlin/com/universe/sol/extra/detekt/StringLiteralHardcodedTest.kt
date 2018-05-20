package com.universe.sol.extra.detekt

import io.gitlab.arturbosch.detekt.api.Rule
import com.universe.sol.extra.detekt.rules.StringLiteralHardcoded
import io.gitlab.arturbosch.detekt.test.RuleTest
import io.gitlab.arturbosch.detekt.test.lint
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class TooManyFunctionsTest : RuleTest {

    override val rule: Rule = StringLiteralHardcoded()

    @Test
    fun findOneFile() {
        val findings = rule.lint(codeWithLogMessages)
        assertThat(findings).hasSize(2)
    }

}

val code2: String =
        """
            @RunWith(AndroidJUnit4::class)
            class ExampleInstrumentedTest {
                @Test
                fun useAppContext() {
                    // Context of the app under test.
                    val appContext = InstrumentationRegistry.getTargetContext()
//                    assertEquals("com.universe.sol", appContext.packageName)
                }
            }

        """

val codeWithLogMessages: String =
        """
            @RunWith(AndroidJUnit4::class)
            class ExampleInstrumentedTest {
                @Test
                fun useAppContext() {
                   activity?.componentName?.className?.contains("Splash") != true
                   throw IllegalArgumentException("Incorrect keyboard button id")
                   Log.d("TAG", "Some log message")
                   Timber.e(e, "get file from gallery error")
                   assertEquals("com.universe.sol", appContext.packageName)
                   some_legal_crypt_function("AES_ALGORITHM")
                   photoPickerIntent.type = "image/*"
                }

                companion object {
                    const val COMPUTATION_SCHEDULER: String = "COMPUTATION_SCHEDULER"
                    const val IO_SCHEDULER: String = "IO_SCHEDULER"
                    const val MAIN_SCHEDULER: String = "MAIN_SCHEDULER"
                }
            }

        """

val code: String =
        """
			class TooManyFunctions : Rule("TooManyFunctions") {

				override fun visitUserType(type: KtUserType) {
					super.visitUserType(type)
				}

				override fun visitReferenceExpression(expression: KtReferenceExpression) {
					super.visitReferenceExpression(expression)
				}

				override fun visitCallExpression(expression: KtCallExpression) {
					super.visitCallExpression(expression)
				}

				override fun visitBlockStringTemplateEntry(entry: KtBlockStringTemplateEntry) {
					super.visitBlockStringTemplateEntry(entry)
				}

				override fun visitUnaryExpression(expression: KtUnaryExpression) {
					super.visitUnaryExpression(expression)
				}

				override fun visitDynamicType(type: KtDynamicType) {
					super.visitDynamicType(type)
				}

				override fun visitDynamicType(type: KtDynamicType, data: Void?): Void {
					return super.visitDynamicType(type, data)
				}

				override fun visitSuperTypeCallEntry(call: KtSuperTypeCallEntry) {
					super.visitSuperTypeCallEntry(call)
				}

				override fun visitParenthesizedExpression(expression: KtParenthesizedExpression) {
					super.visitParenthesizedExpression(expression)
				}

				override fun visitFinallySection(finallySection: KtFinallySection) {
					super.visitFinallySection(finallySection)
				}

				override fun visitStringTemplateExpression(expression: KtStringTemplateExpression) {
					super.visitStringTemplateExpression(expression)
				}

				override fun visitDeclaration(dcl: KtDeclaration) {
					super.visitDeclaration(dcl)
				}

				override fun visitLabeledExpression(expression: KtLabeledExpression) {
					super.visitLabeledExpression(expression)
				}

				override fun visitEscapeStringTemplateEntry(entry: KtEscapeStringTemplateEntry) {
					super.visitEscapeStringTemplateEntry(entry)
				}

				override fun visitScript(script: KtScript) {
					super.visitScript(script)
				}

				override fun visitTypeConstraintList(list: KtTypeConstraintList) {
					super.visitTypeConstraintList(list)
				}

			}
		"""
