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

/**
 * val is used for immutable variables, var is used for mutable ones.
 */
fun valsAndVars(): Int {
    val immutable = 2
    var mutable = immutable
    mutable *= immutable
    return mutable
}