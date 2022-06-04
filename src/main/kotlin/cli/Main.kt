package cli

import cli.ImageWriter.getOutputPath
import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.mainBody
import lib.*
import lib.connectivity.Type4Connected
import lib.connectivity.Type8Connected
import lib.weightFunction.DefaultWeightFunction


fun main(args: Array<String>) = mainBody {

    ArgParser(args).parseInto(::CliArgs).run {
        val imagesCollection = images
        val masksCollection = masks

        val weightFunction = DefaultWeightFunction(zParam, eParam)

        val connectivity = when (connectivity) {
            4 -> Type4Connected()
            8 -> Type8Connected()
            else -> throw IllegalStateException("Supported connectivity type: 4-connected or 8-connected.")
        }

        val executor = HoleFillingExecutor(connectivity, weightFunction)
        val preprocessor = ImagePreprocessor()

        for (i in imagesCollection.indices) {
            val image = preprocessor.process(imagesCollection[i], masksCollection[i])
            val resultImage = executor.fillHole(image)

            ImageWriter.saveImage(resultImage, getOutputPath(output, imagesCollection[i]))
        }
    }
}
