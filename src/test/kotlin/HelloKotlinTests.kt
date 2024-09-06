import br.dev.ardc.kotlinsandbox.*
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test

class HelloKotlinTests  {

    @Test
    fun `aStandaloneFunction should accept a name`() {
        aStandaloneFunction("Kotlin")
        // should not throw an exception
    }

    @Test
    fun `aStandaloneFunction should not accept whitespaces or empty strings`() {
        val invalidInputs = listOf("", " ", "  ", "   ")
        invalidInputs.forEach {
            assertThrows<IllegalArgumentException> {
                aStandaloneFunction(it)
            }
        }
    }

    @Test
    fun `vals and vars are ways to declare variables`() {
        assert(valsAndVars() > 0)
    }

    @Test
    fun `if statements can be used to control execution flow`(): Unit {
        val cases = listOf(
            Pair("Kotlin", false),
            Pair("Kotlin is awesome", true)
        )
        cases.forEach {
            assert(checkNameWithIf(it.first) == it.second)
        }
    }

    @Test
    fun `fooBarNameLength returns foo when length is even, bar if else`(): Unit {
        val cases = listOf(
            Pair("Kotlin", fooString),
            Pair("Kotlin is awesome", barString)
        )
        cases.forEach {
            assert(fooBarNameLength(it.first) == it.second)
        }
    }

    @Test
    fun `nameIsEventWithWhen returns true when a name is even, false if odd`(): Unit {
        val cases = listOf(
            Pair("Kotlin", true),
            Pair("Kotlin is awesome", false)
        )
        cases.forEach {
            assert(nameIsEvenWithWhen(it.first) == it.second)
        }
    }
}