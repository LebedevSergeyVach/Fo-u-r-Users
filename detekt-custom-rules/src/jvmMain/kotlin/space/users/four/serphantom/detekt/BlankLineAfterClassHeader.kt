package space.users.four.serphantom.detekt

import io.gitlab.arturbosch.detekt.api.CodeSmell
import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.Debt
import io.gitlab.arturbosch.detekt.api.Entity
import io.gitlab.arturbosch.detekt.api.Issue
import io.gitlab.arturbosch.detekt.api.Rule
import io.gitlab.arturbosch.detekt.api.Severity
import org.jetbrains.kotlin.com.intellij.psi.PsiWhiteSpace
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtClassBody
import org.jetbrains.kotlin.psi.KtNamedDeclaration
import org.jetbrains.kotlin.psi.KtObjectDeclaration

/**
 * Правило 3: После заголовка (названия + главного конструктора) класса или объекта
 * перед первым членом класса должна быть ровно одна пустая строка.
 *
 * Корректный пример:
 * ```kotlin
 * class MyClass(
 *     val name: String,
 * ) {
 *
 *     fun doSomething() { ... }
 * }
 * ```
 *
 * Некорректный пример (нет пустой строки):
 * ```kotlin
 * class MyClass(val name: String) {
 *     fun doSomething() { ... }
 * }
 * ```
 *
 * Исключения:
 * - Пустые тела классов `class Foo {}`
 */
class BlankLineAfterClassHeader(
    config: Config = Config.empty,
) : Rule(config) {

    override val issue = Issue(
        id = "BlankLineAfterClassHeader",
        severity = Severity.Style,
        description = "После заголовка класса/объекта (имя + конструктор) " +
            "перед первым членом должна быть одна пустая строка.",
        debt = Debt.FIVE_MINS,
    )

    override fun visitClass(klass: KtClass) {
        super.visitClass(klass)
        klass.body?.checkBlankLineAfterOpenBrace(klass)
    }

    override fun visitObjectDeclaration(declaration: KtObjectDeclaration) {
        super.visitObjectDeclaration(declaration)
        declaration.body?.checkBlankLineAfterOpenBrace(declaration)
    }

    private fun KtClassBody.checkBlankLineAfterOpenBrace(owner: KtNamedDeclaration) {
        // Пустое тело — правило не применяется
        val members = declarations
        if (members.isEmpty()) return

        // KtClassBody.lBrace — PsiElement открывающей скобки `{`
        val lBraceNode = lBrace?.node ?: return

        // Следующий узел дерева после `{`
        val nextNode = lBraceNode.treeNext ?: return

        // Проверяем: после `{` должен идти PsiWhiteSpace с двумя и более переносами строк
        // — первый `\n` для перехода на следующую строку, второй `\n` для пустой строки
        val hasBlankLine = if (nextNode.psi is PsiWhiteSpace) {
            val newlineCount = nextNode.text.count { char -> char == '\n' }
            newlineCount >= 2
        } else {
            false
        }

        if (!hasBlankLine) {
            val firstMember = members.first()
            report(
                finding = CodeSmell(
                    issue = issue,
                    entity = Entity.from(firstMember),
                    message = "После открывающей скобки класса/объекта " +
                        "'${owner.name.orEmpty()}' " +
                        "должна быть пустая строка перед первым членом.",
                ),
            )
        }
    }
}
