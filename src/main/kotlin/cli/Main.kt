import cli.ImagePreprocessor
import lib.*
import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO


fun main(args: Array<String>) {
    val paramZ = args[0].toFloat()
    val paramE = args[1].toFloat()

    val paramConnectivityType = args[2].toInt()

    // check on missing params
    // check if CC not 4 or 8
    // pass output path for result?
    // extend to work with multiple images

    val paramImagePath = args[3]
    val paramMaskPath = args[4]

    // preprocess
    // fill hole, return image
    // write result image

    val image = ImagePreprocessor().process(paramImagePath, paramMaskPath)

    val weightFunction = DefaultWeightFunction(paramZ, paramE)
    val connectivity = when (paramConnectivityType) {
        4 -> Type4Connected()
        8 -> Type8Connected()
        else -> throw IllegalStateException("Supported connectivity type: 4-connected or 8-connected.")
    }

    val executor = HoleFillingExecutor(connectivity, weightFunction)

    val resultImage = executor.fillHole(image)
    saveImage(resultImage)
}

fun saveImage(image: MyImage) {
    val resultImage = BufferedImage(image.width, image.height, BufferedImage.TYPE_INT_RGB)

    for (y in 0 until image.height) {
        for (x in 0 until image.width) {
            val color = image.points[y][x].color.run {
                println(this)
                Color(this, this, this)
            }
            resultImage.setRGB(x, y, color.rgb)
        }
    }

    val fileOutput = File("/User/Arles/Downloads/sample.png")
    ImageIO.write(resultImage, "png", fileOutput)
}
