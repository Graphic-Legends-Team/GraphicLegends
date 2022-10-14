// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import Configuration.MutableConfigurationsState
import Converters.Bitmap
import Parsers.BytesParser
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.ComposeWindow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import java.awt.Dialog
import java.awt.FileDialog
import java.awt.FileDialog.SAVE

@Composable
fun App() {
    val state = remember { mutableStateOf(false) }
    val obj = Configuration.Image()
    val imageBMP = remember { mutableStateOf(Bitmap.imageFromBuffer(null)) }
    val image = remember { mutableStateOf(obj) }
    MaterialTheme {
        Box(
            modifier = Modifier.background(Color.DarkGray)
        ) {
            Row(Modifier.fillMaxSize(), Arrangement.spacedBy(15.dp)) {
                Button(
                    modifier = Modifier.padding(15.dp, 0.dp, 0.dp, 0.dp),
                    onClick = {
                        val fd = FileDialog(ComposeWindow())
                        fd.isVisible = true
                        if (fd.files.isNotEmpty())
                        {
                            val file = fd.files[0]
                            imageBMP.value = BytesParser.ParseBytesForFile(file)!!
                            image.value.bufferedImage = MutableConfigurationsState.bufferedImage
                            image.value.byteArray = MutableConfigurationsState.byteArray
                            state.value = true
                        }
                    },
                    colors = ButtonDefaults.buttonColors(Color.Green)
                ) {
                    Text("Open")
                }

                Button(
                    onClick = {
                        if (state.value) {
                            val dialog : Dialog? = null
                            val fd = FileDialog(dialog, "Write the name of file", SAVE)
                            fd.isVisible = true
                            if (fd.files.isNotEmpty())
                            {
                                val file = fd.files[0]
                                val width = image.value.bufferedImage?.width
                                val height = image.value.bufferedImage?.height
                                if (width != null && height != null) {
                                    BytesParser.ParseFileInBytes(file.absolutePath, width, height,
                                        MutableConfigurationsState.shade, image.value.byteArray)
                                }
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(Color.Green)
                ) {
                    Text("Save")
                }
            }

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                if (state.value) {
                    Card(
                        elevation = 10.dp
                    ) {
                        imageBMP.value?.let {
                            Image(
                                bitmap = it,
                                contentDescription = "image",
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }
            }
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication,
        title = "Compose for Desktop",
        state = rememberWindowState(width = 700.dp, height = 700.dp)
    ) {
        App()
    }
}