package org.example

import kotlin.math.pow
import kotlin.math.sqrt

data class Circle(val x: Double, val y: Double, val r: Double)

fun getCoordinate(coordinate: String): Double {
    while (true) {
        try {
            print("Input $coordinate: ")
            return readlnOrNull()?.toDoubleOrNull() ?: throw NumberFormatException()
        } catch (e: NumberFormatException) {
            println("Couldn't parse a number. Please, try again")
        }
    }
}

fun getCircle(num: String): Circle {
    val x = getCoordinate("x$num")
    val y = getCoordinate("y$num")
    val r = getCoordinate("r$num")
    return Circle(x, y, r)
}

fun main() {
    val circle1 = getCircle("1")
    val circle2 = getCircle("2")

    print("Result: ")
    if (circle1 == circle2) println("Circles are coincide")
    val d = sqrt(
        (circle2.x - circle1.x).pow(2) +
                (circle2.y - circle1.y).pow(2)
    )
    val rSum = circle1.r + circle2.r
    println(
        when {
            d <= circle2.r - circle1.r -> "the circle 1 is inside circle 2"
            d <= circle1.r - circle2.r -> "the circle 2 is inside circle 1"
            d < rSum -> "the circles intersect"
            d == rSum -> "the circles touch each other"
            else -> "the circles do not intersect"
        }
    )
}