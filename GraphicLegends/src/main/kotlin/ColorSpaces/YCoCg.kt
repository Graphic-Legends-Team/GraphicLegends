package ColorSpaces

import Interfaces.IColorSpace

class YCoCg : IColorSpace {

    override fun ToRGB(values: FloatArray): FloatArray {
        val r = values[0] + values[1] - values[2]
        val g = values[0] + values[2]
        val b = values[0] - values[1] - values[2]

        return floatArrayOf(r, g, b)
    }

    override fun ToCMY(values: FloatArray): FloatArray {
        TODO("Not yet implemented")
    }

    override fun ToHSL(values: FloatArray): FloatArray {
        TODO("Not yet implemented")
    }

    override fun ToHSV(values: FloatArray): FloatArray {
        TODO("Not yet implemented")
    }

    override fun ToYCbCr601(values: FloatArray): FloatArray {
        TODO("Not yet implemented")
    }

    override fun ToYCbCr709(values: FloatArray): FloatArray {
        TODO("Not yet implemented")
    }

    override fun ToYCoCg(values: FloatArray): FloatArray {
        return values
    }
}