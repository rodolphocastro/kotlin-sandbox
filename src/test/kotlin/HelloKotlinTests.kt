import br.dev.ardc.kotlinsandbox.aStandaloneFunction
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
}