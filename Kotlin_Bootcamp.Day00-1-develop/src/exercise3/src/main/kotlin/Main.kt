package org.example

import kotlin.math.round

fun checkTempMode(tempMode: String) {
    if (tempMode != "Celsius" && tempMode != "Kelvin" && tempMode != "Fahrenheit") {
        throw Exception("It must be Kelvin, Celsius or Fahrenheit temperature scale mod")
    }
}

fun convertTemp(temp: Double, tempMode: String): Double {
    return when (tempMode) {
        "Kelvin" -> temp + 273.15
        "Fahrenheit" -> (temp * 9 / 5) + 32
        else -> temp
    }
}

fun convertMode(tempMode: String): String {
    return when (tempMode) {
        "Kelvin" -> "K"
        "Fahrenheit" -> "˚F"
        else -> "˚C"
    }
}

fun main(args: Array<String>) {
    val tempMode = if (args.isEmpty()) "Celsius" else args.first()
    checkTempMode(tempMode)
    println("Output mode: $tempMode")
    print("Enter a season - (W)inter or (S)ummer: ")
    var season = readln()
    while (season != "W" && season != "S") {
        print("Incorrect input. Enter a season - (W)inter or (S)ummer: ")
        season = readln()
    }
    print("Season: ${if (season == "W") "Winter" else "Summer"}. Enter a temperature: ")
    var temp = readln().toDoubleOrNull()
    while (temp == null) {
        print("Incorrect input. Enter a temperature: ")
        temp = readln().toDoubleOrNull()
    }
    println()

    temp = convertTemp(temp, tempMode)
    val modeShort = convertMode(tempMode)
    val comfortableMin: Double
    val comfortableMax: Double
    if (season == "W") {
        comfortableMin = convertTemp(20.0, tempMode)
        comfortableMax = convertTemp(22.0, tempMode)
    } else {
        comfortableMin = convertTemp(22.0, tempMode)
        comfortableMax = convertTemp(25.0, tempMode)
    }
    println("The temperature is $temp $modeShort")
    println("The comfortable temperature is from $comfortableMin to $comfortableMax $modeShort")
    println(
        when {
            temp < comfortableMin -> "Please, make it warmer by ${round((comfortableMin - temp) * 100) / 100} degrees."
            temp > comfortableMax -> "Please, make it colder by ${round((temp - comfortableMax) * 100) / 100} degrees."
            else -> "S'all good, man!"
        }
    )
}