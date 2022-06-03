package lib

interface IWeightFunction {

    fun weight(from: MyPoint, to: MyPoint): Float
}