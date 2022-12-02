package Configurations

import App.HeaderDropdownButton
import App.TextFieldActivity.LineSettingsTextField
import Filtration.FiltrationMode
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.ImageBitmap

class AppConfiguration() {
    val imageConfiguration = mutableStateOf(ImageConfiguration())
    val spaceConfiguration = mutableStateOf(SpaceConfiguration())
    val componentConfiguration = mutableStateOf(ComponentConfiguration())
    val gammaConfiguration = mutableStateOf(GammaConfiguration())
    val lineConfiguration = mutableStateOf(LineConfiguration())
    val bitmap : MutableState<ImageBitmap?> = mutableStateOf(null)
    val hasContent =  mutableStateOf(false)

    companion object{
        private var configuration : AppConfiguration = AppConfiguration()
        var Image : ImageConfiguration
            get() {
                return configuration.imageConfiguration.value
            }
            set(value)
            {
                configuration.hasContent.value = true
                configuration.imageConfiguration.value = value
                Component.selected = FiltrationMode.ALL
                updateBitmap()
            }
        var Space : SpaceConfiguration = configuration.spaceConfiguration.value
        var Component : ComponentConfiguration = configuration.componentConfiguration.value
        var Gamma : GammaConfiguration = configuration.gammaConfiguration.value
        var Line : LineConfiguration = configuration.lineConfiguration.value
        var Dithering : DitheringConfiguration = configuration.ditheringConfiguration.value
        val SaveScript : SaveMode = configuration.saveScript.value
        fun HasContent() : Boolean {
            return configuration.hasContent.value
        }
        fun GetBitmap() : ImageBitmap
        {
            return configuration.bitmap.value!!
        }

        fun showDithered() {
            configuration.bitmap.value = Image.getDitheredImageBitmap()
        }

        fun updateBitmap(){
            configuration.bitmap.value = Image.getOriginalImageBitmap()
        }

        fun HideTextFields() {
            Line.colorExpanded.value = false
            Line.saturationExpanded.value = false
            Line.thicknessExpanded.value = false
            Gamma.assignTextFieldHidden.value = true
            Gamma.convertTextFieldHidden.value = true
        }
    }

}