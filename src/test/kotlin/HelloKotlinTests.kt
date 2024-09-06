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

    @Test
    fun `sumRanges returns the sum of all numbers up to its biggest one`(): Unit {
        val cases = listOf(
            Pair(1, 1),
            Pair(2, 3),
            Pair(3, 6),
            Pair(4, 10),
            Pair(5, 15),
            Pair(6, 21),
            Pair(7, 28),
            Pair(8, 36),
            Pair(9, 45),
            Pair(10, 55)
        )
        cases.forEach {
            assert(sumRanges(it.first) == it.second)
        }
    }

    @Test
    fun `sumOddsRange returns the sum of all odd numbers up to its biggest one`(): Unit {
        val cases = listOf(
            Pair(1, 1),
            Pair(2, 1),
            Pair(3, 4),
            Pair(4, 4),
            Pair(5, 9),
            Pair(6, 9),
            Pair(7, 16),
            Pair(8, 16),
            Pair(9, 25),
            Pair(10, 25)
        )
        cases.forEach {
            assert(sumOddsRange(it.first) == it.second)
        }
    }

    @Test
    fun `appendAWhile must return a number of As based on its runs`(): Unit {
        val cases = listOf(
            Pair(1, "a"),
            Pair(2, "aa"),
            Pair(3, "aaa"),
            Pair(4, "aaaa"),
            Pair(5, "aaaaa"),
            Pair(6, "aaaaaa"),
            Pair(7, "aaaaaaa"),
            Pair(8, "aaaaaaaa"),
            Pair(9, "aaaaaaaaa"),
            Pair(10, "aaaaaaaaaa")
        )
        cases.forEach {
            assert(appendAWhile(it.first) == it.second)
        }
    }

    @Test
    fun `appendAWhile doesn't accept runs lower or equal to zero`(): Unit {
        val invalidInputs = listOf(-1, -2, -3, -4, -5)
        invalidInputs.forEach {
            assertThrows<IllegalArgumentException> {
                appendAWhile(it)
            }
        }
    }

    @Test
    fun `appenbdADoWhile must return a number of As based on its runs`(): Unit {
        val cases = listOf(
            Pair(0, "a"),       // note how this is different than the previous function
            Pair(1, "a"),
            Pair(2, "aa"),
            Pair(3, "aaa"),
            Pair(4, "aaaa"),
            Pair(5, "aaaaa"),
            Pair(6, "aaaaaa"),
            Pair(7, "aaaaaaa"),
            Pair(8, "aaaaaaaa"),
            Pair(9, "aaaaaaaaa"),
            Pair(10, "aaaaaaaaaa")
        )
        cases.forEach {
            assert(appendADoWhile(it.first) == it.second)
        }
    }

    @Test
    fun `appendADoWhile should not accept amounts lower than zero`(): Unit {
        val invalidInputs = listOf(-1, -2, -3, -4, -5)
        invalidInputs.forEach {
            assertThrows<IllegalArgumentException> {
                appendADoWhile(it)
            }
        }
    }

    @Test
    fun `addTwoNumbers should return the sum of its parameters`(): Unit {
        val cases = listOf(
            Triple(1, 1, 2),
            Triple(2, 2, 4),
            Triple(3, 3, 6),
            Triple(4, 4, 8),
            Triple(5, 5, 10),
            Triple(6, 6, 12),
            Triple(7, 7, 14),
            Triple(8, 8, 16),
            Triple(9, 9, 18),
            Triple(10, 10, 20)
        )
        cases.forEach {
            assert(addTwoNumbers(it.first, it.second) == it.third)
        }
    }
}