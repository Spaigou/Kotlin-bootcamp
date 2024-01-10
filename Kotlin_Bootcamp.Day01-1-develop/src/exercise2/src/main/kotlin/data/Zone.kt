package org.example.data


open class Zone(open val x1: Int, open val y1: Int) {
    open val name: String = "none"
    open val phone: String = "88008473824"

    open fun isIncidentInside(xo: Int, yo: Int): Boolean {
        return false
    }
}

class CircleZone(x: Int, y: Int, private val r: Int) : Zone(x, y) {
    override val name = "circle"
    override val phone = "89347362826"

    override fun isIncidentInside(xo: Int, yo: Int): Boolean {
        return ((xo - x1) * (xo - x1)) + ((yo - y1) * (yo - y1)) <= r * r
    }
}

class TriangleZone(
    x1: Int,
    y1: Int,
    private val x2: Int,
    private val y2: Int,
    private val x3: Int,
    private val y3: Int
) : Zone(x1, y1) {
    override val name = "triangle"
    override val phone = "89931569323"

    override fun isIncidentInside(xo: Int, yo: Int): Boolean {
        val p = vect(x1 - xo, y1 - yo, x2 - x1, y2 - y1)
        val q = vect(x2 - xo, y2 - yo, x3 - x2, y3 - y2)
        val r = vect(x3 - xo, y3 - yo, x1 - x3, y1 - y3)
        return (p <= 0 && q <= 0 && r <= 0) || (p >= 0 && q >= 0 && r >= 0)
    }

    private fun vect(x1: Int, y1: Int, x2: Int, y2: Int): Int = x1 * y2 - y1 * x2
}

class TetragonZone(
    x1: Int,
    y1: Int,
    private val x2: Int,
    private val y2: Int,
    private val x3: Int,
    private val y3: Int,
    private val x4: Int,
    private val y4: Int
) : Zone(x1, y1) {
    override val name = "tetragon"
    override val phone = "83535880102"

    override fun isIncidentInside(xo: Int, yo: Int): Boolean {
        val p = vect(x1 - xo, y1 - yo, x2 - x1, y2 - y1)
        val q = vect(x2 - xo, y2 - yo, x3 - x2, y3 - y2)
        val r = vect(x3 - xo, y3 - yo, x4 - x3, y4 - y3)
        val s = vect(x4 - xo, y4 - yo, x1 - x4, y1 - y4)
        return (p <= 0 && q <= 0 && r <= 0 && s <= 0) || (p >= 0 && q >= 0 && r >= 0 && s >= 0)
    }

    private fun vect(x1: Int, y1: Int, x2: Int, y2: Int): Int = x1 * y2 - y1 * x2
}