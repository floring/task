package cli

import lib.*
import java.awt.Color
import java.io.File
import javax.imageio.ImageIO


class ImagePreprocessor {

    /**
     * Preprocess image to an input format for a HoleFilling library.
     * Creates hole by applying mask hole pixel over image pixel.
     *  @param imagePath
     *  @param maskPath
     *  @return image with hole
     */
    fun process(imagePath: String, maskPath: String): Image {
        val image = ImageIO.read(File(imagePath))
        val mask = ImageIO.read(File(maskPath))

        val points = Array(mask.width) { Array(mask.height) { Point() } }

        for (x in 0 until mask.width) {
            for (y in 0 until mask.width) {
                val maskPixelColor = Color(mask.getRGB(x, y))
                val normalizedColor =
                    if (maskPixelColor.toNormalizedGrayscale() > 0.5f)
                        Color(image.getRGB(x, y)).toNormalizedGrayscale()
                    else -1f

                points[x][y] = Point(x, y, normalizedColor)
            }
        }

        return Image(points)
    }
}