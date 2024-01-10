package org.example

import kotlin.math.sqrt

fun checkOrder(order: String) {
    if (order != "higher" && order != "lower") throw Exception("Grouping order must be \"higher\" or \"lower\" only.")
}

fun getNumber(): String {
    while (true) {
        try {
            print("Enter a number: ")
            val intNum = readlnOrNull()?.toIntOrNull() ?: throw NumberFormatException()
            if (intNum <= 0) throw Exception()
            return intNum.toString()
        } catch (_: NumberFormatException) {
            println("Number must have integer type")
        } catch (_: Exception) {
            println("Number must be more than 0")
        }
    }
}

fun isPrime(digit: String): Boolean {
    val intDigit = digit.toInt()
    for (i in 2..sqrt(intDigit.toDouble()).toInt()) {
        if (intDigit % i == 0) return false
    }
    return true
}

fun main(args: Array<String>) {
    val groupOrder = args.first()
    checkOrder(groupOrder)
    println("The grouping order is: $groupOrder")
    var number = getNumber()
    var subNumber = ""
    if (groupOrder == "lower") number = number.reversed()
    println("Result:")
    for (digit in number) {
        subNumber += digit
        print(subNumber)
        if (isPrime(subNumber)) print(" - prime")
        println()
    }
}