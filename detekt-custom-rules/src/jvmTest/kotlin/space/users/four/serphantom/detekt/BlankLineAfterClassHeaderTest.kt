package space.users.four.serphantom.detekt

import io.gitlab.arturbosch.detekt.test.lint
import kotlin.test.Test
import kotlin.test.assertEquals

class BlankLineAfterClassHeaderTest {

    private val rule = BlankLineAfterClassHeader()

    @Test
    fun `class with blank line after header - no findings`() {
        val code = """
            class MyClass(
                val name: String,
            ) {

                fun doSomething() {}
            }
        """.trimIndent()

        val findings = rule.lint(code)
        assertEquals(
            expected = 0,
            actual = findings.size,
            message = "Ожидается 0 нарушений, когда пустая строка присутствует",
        )
    }

    @Test
    fun `class without blank line after header - reports finding`() {
        val code = """
            class MyClass(val name: String) {
                fun doSomething() {}
            }
        """.trimIndent()

        val findings = rule.lint(code)
        assertEquals(
            expected = 1,
            actual = findings.size,
            message = "Ожидается 1 нарушение, когда пустая строка отсутствует",
        )
    }

    @Test
    fun `object with blank line after header - no findings`() {
        val code = """
            object MyObject {

                val constant = 42
            }
        """.trimIndent()

        val findings = rule.lint(code)
        assertEquals(
            expected = 0,
            actual = findings.size,
        )
    }

    @Test
    fun `object without blank line - reports finding`() {
        val code = """
            object MyObject {
                val constant = 42
            }
        """.trimIndent()

        val findings = rule.lint(code)
        assertEquals(
            expected = 1,
            actual = findings.size,
        )
    }

    @Test
    fun `empty class body - no findings`() {
        val code = """
            class EmptyClass
        """.trimIndent()

        val findings = rule.lint(code)
        assertEquals(
            expected = 0,
            actual = findings.size,
            message = "Пустой класс не должен нарушать правило",
        )
    }

    @Test
    fun `data class with blank line - no findings`() {
        val code = """
            data class User(
                val id: String,
                val name: String,
            ) {

                fun displayName() = name
            }
        """.trimIndent()

        val findings = rule.lint(code)
        assertEquals(expected = 0, actual = findings.size)
    }
}
