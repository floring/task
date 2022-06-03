package lib

class HoleFillingExecutor(private val connectivity: IConnectivityType, private val weightFunction: IWeightFunction) {

    fun fillHole(image: MyImage): MyImage {
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

    private fun findHole(image: MyImage): Hole {
        val points = hashSetOf<MyPoint>()

        // omit case where hole is on image edges
        for (i in 1 until image.width - 1) {
            for (j in 1 until image.height - 1) {
                image.points[i][j].takeIf { it.isHole() }?.let { points.add(it) }
            }
        }
        return Hole(points)
    }

    private fun findBoundary(image: MyImage, hole: Hole): Boundary {
        val points = hashSetOf<MyPoint>()

        for (holePoint in hole.points) {
            points.addAll(connectivity.getConnectionsFor(holePoint, image))
        }
        return Boundary(points)
    }

    private fun calculateColor(holePoint: MyPoint, boundary: Boundary): Float {
        var numerator = 0f
        var denominator = 0f

        for (boundaryPoint in boundary.points) {
            val weight = weightFunction.weight(holePoint, boundaryPoint)
            numerator += (weight + boundaryPoint.color)
            denominator += weight
        }

        val bla = numerator / denominator

        return bla
    }
}