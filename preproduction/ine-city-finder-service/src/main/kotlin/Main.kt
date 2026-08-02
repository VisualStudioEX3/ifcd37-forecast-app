package org.example

import org.example.ine.models.IneCityData
import org.example.ine.services.IneService

val service = IneService()

suspend fun main() {
    var select: String

    println("INE service simple terminal program.")

    do {
        print(
            "\nSelect option:\n" +
                    "1: Find cities by name.\n" +
                    "2: Get state name by code.\n" +
                    "0: Exit.\n" +
                    ">"
        ); select = readln()

        when (select) {
            "1" -> findCitiesByName()
            "2" -> getStateNameByCode()
            else -> println("Wrong selection. Try again.")
        }
    } while (select != "0")
}

suspend fun findCitiesByName() {
    try {
        print("Enter a name of a city: ")
        val name: String = readln()
        val result: List<IneCityData> = service.findCitiesByName(name)

        println("Found ${result.size} matches:")
        result.forEach {
            println(it)
        }
    } catch (e: Exception) {
        println(e.message)
    }
}

suspend fun getStateNameByCode() {
    try {
        print("Enter a code of a state: ")
        val code: String = readln()
        val result: String = service.getStateNameByCode(code)

        println("State name: $result")
    } catch (e: Exception) {
        println(e.message)
    }
}