package cli

import lib.*
import java.awt.Color
import java.io.File
import javax.imageio.ImageIO


class ImagePreprocessor {

    fun process(imagePath: String, maskPath: String): MyImage {
        val image = ImageIO.read(File(imagePath))
        val mask = ImageIO.read(File(maskPath))

        val points = Array(mask.height) { Array(mask.width) { MyPoint() } }

        for (y in 0 until mask.height) {
            for (x in 0 until mask.width) {
                val maskPixelColor = Color(mask.getRGB(x, y))
                val normalizedColor =
                    if (maskPixelColor.toGrayscale() > 0.5f) Color(image.getRGB(x, y)).toGrayscale() else -1f

                points[y][x] = MyPoint(x, y, normalizedColor)
            }
        }

        return MyImage(points)
    }
}