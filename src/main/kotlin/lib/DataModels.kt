package lib

data class Point(var x: Int = 0, var y: Int = 0, var color: Float = 0f) {
    fun isHole(): Boolean = this.color == -1f
}

data class Hole(val points: Set<Point>)

data class Boundary(val points: Set<Point>)

data class Image(val points: Array<Array<Point>>) {

    val width: Int
        get() = points.size
    val height: Int
        get() = points[0].size

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Image

        if (!points.contentDeepEquals(other.points)) return false

        return true
    }

    override fun hashCode(): Int {
        return points.contentDeepHashCode()
    }
}