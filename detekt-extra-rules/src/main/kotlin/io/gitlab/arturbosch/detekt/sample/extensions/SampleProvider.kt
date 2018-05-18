package io.gitlab.arturbosch.detekt.sample.extensions

import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.RuleSet
import io.gitlab.arturbosch.detekt.api.RuleSetProvider
import io.gitlab.arturbosch.detekt.sample.extensions.rules.StringLiteralHardcoded

/**
 * @author Artur Bosch
 */
class SampleProvider : RuleSetProvider {

    override val ruleSetId: String = "sample"

    override fun instance(config: Config): RuleSet {
        return RuleSet(ruleSetId, listOf(
                StringLiteralHardcoded()
        ))
    }
}
