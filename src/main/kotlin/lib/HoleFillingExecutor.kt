package lib

import lib.weightFunction.DefaultWeightFunction
import lib.weightFunction.IWeightFunction

/**
 * Represents a main class of a HoleFilling library.
 * Support filling holes in grayscale images, where each pixel value is a float in
 * the range [0, 1], and hole (missing) values which are marked with the value -1.
 */
class HoleFillingExecutor(
    private val connectivity: IConnectivityType,
    private val weightFunction: IWeightFunction = DefaultWeightFunction()
) {

    /**
     * Executes hole filling algorithm.
     * @param image image with hole
     * For representation of image input format:
     * @see Image
     * @return filled image
     */
    fun fillHole(image: Image): Image {
        val hole = findHole(image)

        val boundaryPoints = findBoundary(image, hole)

        for (holePoint in hole.points) {
            holePoint.color = calculateColor(holePoint, boundaryPoints)
        }

        for (holePoint in hole.points) {
            image.points[holePoint.x][holePoint.y].color = holePoint.color
        }

        return image
    }

    /**
     * Find single Hole in the image.
     * Hole is represented as a set of Points.
     * @see Hole
     * @return hole object
     */
    private fun findHole(image: Image): Hole {
        val points = hashSetOf<Point>()

        // omit case where hole is on image edges
        for (i in 1 until image.width - 1) {
            for (j in 1 until image.height - 1) {
                image.points[i][j].takeIf { it.isHole() }?.let { points.add(it) }
            }
        }
        return Hole(points)
    }

    /**
     * Find Boundary for a Hole.
     * Boundary is represented as a set of Points.
     * @see Boundary
     * @return boundary object
     */
    private fun findBoundary(image: Image, hole: Hole): Boundary {
        val points = hashSetOf<Point>()

        for (holePoint in hole.points) {
            points.addAll(connectivity.getConnectionsFor(holePoint, image))
        }
        return Boundary(points)
    }

    /**
     * Calculate color to fill for a hole point by its boundary.
     * @return normalized color in range [0,1].
     */
    private fun calculateColor(holePoint: Point, boundary: Boundary): Float {
        var numerator = 0f
        var denominator = 0f

        for (boundaryPoint in boundary.points) {
            val weight = weightFunction.weight(holePoint, boundaryPoint)
            numerator += weight * boundaryPoint.color
            denominator += weight
        }

        return numerator / denominator
    }
}