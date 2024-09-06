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

/**
 * checks if a name has more than 10 characters by using an if statement.
 */
fun checkNameWithIf(name: String): Boolean {
    // suppressing, we want to showcase the structure of an if statement in Kotlin
    @Suppress("RedundantIf")
    if (name.length >= 10) return true
    else return false
}

/**
 * const vals are used for compile-time constants.
 */
const val fooString = "Foo"
const val barString = "Bar"

/**
 * returns foo if the name's length is even, bar if it's odd.
 */
fun fooBarNameLength(name: String): String {
    // since if is an expression there's no ternary operator in Kotlin
    // instead we use an idiomatic if statement
    val result = if (name.length % 2 > 0) barString else fooString
    return result
}

/**
 * returns if a name's length is even or not.
 */
fun nameIsEvenWithWhen(name: String): Boolean {
    // when can be used as a switch statement
    return when (name.length % 2) {
        0 -> true
        else -> false
    }
}