package lib.weightFunction

import lib.Point
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Represents default weight function for the hole filling algorithm.
 */
class DefaultWeightFunction(val z: Float = 2f, val epsilon: Float = 0.000001f) : IWeightFunction {

    override fun weight(from: Point, to: Point): Float {
        return 1 / (euclideanDistance(from, to).pow(z) + epsilon)
    }

    private fun euclideanDistance(from: Point, to: Point): Float {
        val xDiff = (from.x - to.x).toFloat()
        val yDiff = (from.y - to.y).toFloat()

        return sqrt(xDiff * xDiff + yDiff * yDiff)
    }
}