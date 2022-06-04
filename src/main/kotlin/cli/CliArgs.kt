package cli

import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.InvalidArgumentException

class CliArgs(parser: ArgParser) {
    val zParam by parser.storing(
        "-z",
        help = "Specify Z float parameter for a default weight function"
    ) { toFloat() }

    val eParam by parser.storing(
        "-e",
        help = "Specify Epsilon float parameter for a default weight function"
    ) { toFloat() }

    val output by parser.storing(
        "--output",
        help = "Specify output directory for the result image"
    )

    val connectivity by
    parser.storing("--connectivity", help = "Specify pixel connectivity type number") { toInt() }
        .addValidator {
            if (value != 4 && value != 8)
                throw InvalidArgumentException(
                    "Supported connectivity type: 4 or 8"
                )
        }

    val images by parser.adding(
        "-I", help = "Image path to process"
    )

    val masks by parser.adding(
        "-M", help = "Mask path to for image to process"
    )
}