package lib.connectivity

import lib.Image
import lib.Point
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


internal class Type8ConnectedTest {

    private lateinit var testImage: Image
    private val connectivity = Type8Connected()

    @BeforeEach
    fun setup() {
        val points = Array(100) { Array(100) { Point() } }
        for (i in points.indices) {
            for (j in points[0].indices) {
                points[i][j] = Point(i, j, Math.random().toFloat())
            }
        }
        testImage = Image(points)
    }

    @Test
    fun getConnectionsFor_singlePointHole_8connections() {
        val hole = Point(4, 45, -1f)
        testImage.points[4][45] = hole

        val boundary = hashSetOf(
            testImage.points[3][44],
            testImage.points[4][44],
            testImage.points[5][44],
            testImage.points[3][45],
            testImage.points[5][45],
            testImage.points[3][46],
            testImage.points[4][46],
            testImage.points[5][46]
        )

        assertEquals(connectivity.getConnectionsFor(hole, testImage), boundary)
    }

    @Test
    fun getConnectionsFor_2pointsHole_holeConnectionNotIncluded() {
        val testHole = Point(4, 45, -1f)
        testImage.points[4][45] = testHole
        testImage.points[5][45] = Point(5, 45, -1f)

        val boundary = hashSetOf(
            testImage.points[3][44],
            testImage.points[4][44],
            testImage.points[5][44],
            testImage.points[3][45],
            testImage.points[3][46],
            testImage.points[4][46],
            testImage.points[5][46]
        )

        assertEquals(connectivity.getConnectionsFor(testHole, testImage), boundary)
    }
}