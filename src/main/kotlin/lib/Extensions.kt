package lib

import java.awt.Color

fun Color.toGrayscale(): Float = (this.red + this.green + this.blue) / (255 * 3f)
