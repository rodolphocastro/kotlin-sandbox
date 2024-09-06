package br.dev.ardc.kotlinsandbox

class HelloKotlin {

}

/***
 * a standalone function that prints a given name.
 */
fun aStandaloneFunction(name: String) {
    if (name.isBlank()) {
        throw IllegalArgumentException("Name cannot be blank")
    }
    println("Hello, $name!")
}