package lib.weightFunction

import lib.Point

interface IWeightFunction {

    fun weight(from: Point, to: Point): Float
}