package lib

import kotlin.math.pow
import kotlin.math.sqrt

class DefaultWeightFunction(val z: Float, val epsilon: Float) : IWeightFunction {

    override fun weight(from: MyPoint, to: MyPoint): Float {
        return 1 / (euclideanDistance(from, to).pow(z) + epsilon)
    }

    private fun euclideanDistance(from: MyPoint, to: MyPoint): Float {
        val xDiff = (from.x - to.x).toFloat()
        val yDiff = (from.y - to.y).toFloat()

        return sqrt(xDiff * xDiff + yDiff * yDiff)
    }
}