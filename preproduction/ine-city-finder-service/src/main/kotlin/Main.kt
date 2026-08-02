package org.example

import org.example.ine.services.IneService

suspend fun main() {
    print("Enter a name of a city: ")
    val name: String = readln()
    val result = IneService().findCitiesByName(name)

    result.forEach {
        println(it)
    }
}