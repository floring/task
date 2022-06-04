package lib.weightFunction

import lib.Point

/**
 * Weighting function which assigns a non-negative float weight to a pair of two pixel coordinates in the image.
 */
interface IWeightFunction {

    fun weight(from: Point, to: Point): Float
}