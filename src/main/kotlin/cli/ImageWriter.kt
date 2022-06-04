package cli

import lib.Image
import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

object ImageWriter {
    const val PNG_FORMAT = "png"
    private const val PREFIX = "filled_"

    /**
     * Save filled image to defined location.
     * @param image output image
     * @param path output path
     * @param format image format, PNG by default
     */
    fun saveImage(image: Image, path: String, format: String = PNG_FORMAT) {
        val resultImage = BufferedImage(image.width, image.height, BufferedImage.TYPE_INT_RGB)

        for (x in 0 until image.width) {
            for (y in 0 until image.height) {
                val color = image.points[x][y].color.run {
                    Color(this, this, this)
                }
                resultImage.setRGB(x, y, color.rgb)
            }
        }

        ImageIO.write(resultImage, format, File(path))
    }

    /**
     * @return output full path for the filled image. Prefix "filled_" is appended to a filename.
     */
    fun getOutputPath(dir: String, filename: String): String =
        "$dir/$PREFIX${filename.substringAfterLast('/')}"
}