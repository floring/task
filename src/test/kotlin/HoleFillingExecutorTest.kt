import lib.HoleFillingExecutor
import lib.Image
import lib.Point
import org.junit.jupiter.api.Assertions.assertTrue
import lib.connectivity.Type8Connected
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class HoleFillingExecutorTest {
    private lateinit var holeImage: Image
    private val executor = HoleFillingExecutor(Type8Connected())

    @BeforeEach
    fun setup() {
        val points = Array(100) { Array(100) { Point() } }
        for (i in points.indices) {
            for (j in points[0].indices) {
                points[i][j] = Point(i, j, Math.random().toFloat())
            }
        }

        // add single hole
        for (i in 20 until 30) {
            for (j in 20 until 30) {
                points[i][j] = Point(i, j, -1f)
            }
        }
        holeImage = Image(points)
    }

    @Test
    fun fillHole() {
        val filledImage = executor.fillHole(holeImage)

        var allColored = true
        for (i in filledImage.points.indices) {
            for (j in filledImage.points[0].indices) {
                if (filledImage.points[i][j].isHole()) {
                    allColored = false
                    break
                }
            }
        }
        assertTrue(allColored)
    }
}