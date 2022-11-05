package Formats

import Configurations.ImageConfiguration

enum class Format {
    P5 {
        override fun write(width: Int, height: Int, maxShade: Int, byteArray: ByteArray?): ByteArray {
            return P5().HandleWriter(width, height, maxShade, byteArray)
        }

        override fun read(width: Int, height: Int, maxShade: Int, body: ByteArray): ImageConfiguration {
            return P5().HandleReader(width, height, maxShade, body)
        }

    },
    P6 {
        override fun write(width: Int, height: Int, maxShade: Int, byteArray: ByteArray?): ByteArray {
            return P6().HandleWriter(width, height, maxShade, byteArray)
        }

        override fun read(width: Int, height: Int, maxShade: Int, body: ByteArray): ImageConfiguration {
            return P6().HandleReader(width, height, maxShade, body)
        }

    };
    abstract fun write(width: Int, height: Int, maxShade: Int, byteArray: ByteArray?) : ByteArray
    abstract fun read(width: Int, height: Int, maxShade: Int, body: ByteArray) : ImageConfiguration
}
