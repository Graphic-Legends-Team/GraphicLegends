package Configurations

import ColorSpaces.ColorSpace
import ColorSpaces.ColorSpaceInstance
import Converters.Bitmap
import Converters.GammaConverter
import Formats.Format
import Gammas.GammaPurpose
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import java.awt.image.BufferedImage

class ImageConfiguration(
    _format: Format,
    _width: Int,
    _height: Int,
    _maxShade: Int,
    _pixels: Array<ColorSpaceInstance>
) {
    val format: Format = _format
    val width = _width
    val height = _height
    val maxShade = _maxShade
    private var pixels = _pixels

    constructor() : this(Format.P6, 0, 0, 0, arrayOf())

    fun getImageBitmap(): ImageBitmap {
        val bufferedImage = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
        for (posY in 0 until height) {
            for (posX in 0 until width) {
                val pixel = AppConfiguration.Gamma.AssignMode.Apply(
                    AppConfiguration.Component.selected.GetRGBPixelValues(pixels[posY * width + posX]), GammaPurpose.Assign)
                bufferedImage.setRGB(
                    posX,
                    posY,
                    Color((pixel[0] * 255).toInt(), (pixel[1] * 255).toInt(), (pixel[2] * 255).toInt()).toArgb()
                )
            }
        }
        return Bitmap.imageFromBuffer(bufferedImage)
    }

    fun getPixels(): Array<ColorSpaceInstance> {
        return pixels
    }

    fun changeColorSpace(colorSpace: ColorSpace) {
        for (pixel in pixels) {
            pixel.Kind = colorSpace
        }
    }

    fun changeGamma() {
        for (pixel in pixels) {
            pixel.UpdateValues(
                AppConfiguration.Gamma.ConvertMode.Apply(pixel.GetFloatArrayOfValues(), GammaPurpose.Convert)
            )
        }
    }

    @Composable
    fun ImageView() {
        AppConfiguration.GetBitmap().let {
            Image(
                bitmap = it,
                modifier = if (it.height > 900 && it.width > 1500) Modifier.height(700.dp)
                    .width(1500.dp)
                else if (it.height > 900) Modifier.height(700.dp)
                else if (it.width > 1500) Modifier.width(1500.dp)
                else Modifier,
                contentDescription = "image",
                contentScale = ContentScale.Crop
            )
        }
    }
}