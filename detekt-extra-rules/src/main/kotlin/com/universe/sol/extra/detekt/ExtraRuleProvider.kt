package com.universe.sol.extra.detekt

import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.RuleSet
import io.gitlab.arturbosch.detekt.api.RuleSetProvider
import com.universe.sol.extra.detekt.rules.StringLiteralHardcoded

class ExtraRuleProvider : RuleSetProvider {

    override val ruleSetId: String = "extra"

    override fun instance(config: Config): RuleSet {
        return RuleSet(ruleSetId, listOf(
                StringLiteralHardcoded()
        ))
    }
}
