package lib

data class MyPoint(var x: Int = 0, var y: Int = 0, var color: Float = 0f) {
    fun isHole(): Boolean = this.color == -1f
}

data class Hole(val points: Set<MyPoint>)

data class Boundary(val points: Set<MyPoint>)

data class MyImage(val points: Array<Array<MyPoint>>) {

    val width: Int
        get() = points[0].size
    val height: Int
        get() = points.size

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MyImage

        if (!points.contentDeepEquals(other.points)) return false

        return true
    }

    override fun hashCode(): Int {
        return points.contentDeepHashCode()
    }
}