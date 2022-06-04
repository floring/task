package lib

/**
 * Point is coordinate in Image 2d array with specified float color.
 * Color of a pixel coordinate is in range [0, 1] if point is filled, otherwise -1 (if hole).
 */
data class Point(var x: Int = 0, var y: Int = 0, var color: Float = 0f) {
    fun isHole(): Boolean = this.color == -1f
}

/**
 * Represents Hole entity which is a set of all the hole (missing) pixel coordinates.
 * Hole pixels are 8-connected with each other.
 */
data class Hole(val points: Set<Point>)

/**
 * Represents Boundary entity which is a set of all the boundary pixel coordinates.
 * A boundary pixel is defined as a pixel that is connected to a hole pixel, but is not in the hole itself.
 * Pixels can be either 4- or 8-connected to the hole based on input.
 */
data class Boundary(val points: Set<Point>)

/**
 * Image entity for a HoleFilling library.
 * Represents an Image as 2d array, where each coordinate is a Point.
 * Point with color in float range [0, 1] if filled, -1 is a hole.
 * @see Point
 */
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