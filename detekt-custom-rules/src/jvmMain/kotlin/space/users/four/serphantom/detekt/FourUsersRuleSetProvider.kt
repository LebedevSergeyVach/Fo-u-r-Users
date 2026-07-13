package space.users.four.serphantom.detekt

import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.RuleSet
import io.gitlab.arturbosch.detekt.api.RuleSetProvider

/**
 * Регистрация кастомных правил detekt для проекта FourUsers.
 *
 * Все правила из этого модуля попадают в rule set "four-users-style".
 * Для подключения добавьте в build.gradle.kts модуля:
 * ```kotlin
 * detektPlugins(projects.detektCustomRules)
 * ```
 */
class FourUsersRuleSetProvider : RuleSetProvider {

    override val ruleSetId: String = "four-users-style"

    override fun instance(config: Config): RuleSet = RuleSet(
        id = ruleSetId,
        rules = listOf(
            // Правило 3: пустая строка после заголовка класса/объекта
            BlankLineAfterClassHeader(config),
        ),
    )
}
