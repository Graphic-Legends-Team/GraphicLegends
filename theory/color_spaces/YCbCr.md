# YCbCr
YCbCr – семейство цветовых пространств, которые используются для передачи цветных изображений в компонентном видео и цифровой фотографии.

Y — компонента яркости (от 0 до 1)

Cb и Cr являются синей и красной цветоразностными компонентами (от -0.5 до 0.5)

Y’CbCr не является абсолютным цветовым пространством; скорее это способ кодирования информации сигналов RGB. Для систем отображения используются 
сигналы основных цветов RGB (красный, зелёный и синий). Эти сигналы не являются эффективными для хранения и передачи изображений, так как они 
имеют большую избыточность. Поэтому перевод в систему Y’CbCr позволяет передать информацию о яркости с полным разрешением, а для цветоразностных 
компонент произвести субдискретизацию, то есть выборку с уменьшением числа передаваемых элементов изображения, так как человеческий глаз менее 
чувствителен к перепадам цвета. Это повышает эффективность системы, позволяя уменьшить поток видеоданных. Значение, выраженное в Y’CbCr будет 
предсказуемо, если первично использовались сигналы основных цветов RGB.

|     | BT.601 | BT.709 | BT.2020 |
|-----|--------|--------|---------|
| a   | 0.299  | 0.2126 | 0.2627  |
| b   | 0.587  | 0.7152 | 0.6780  |
| c   | 0.114  | 0.0722 | 0.0593  |
| d   | 1.772  | 1.8556 | 1.8814  |
| e   | 1.402  | 1.5748 | 1.4747  |

### From RGB
```
Y  = a * R + b * G + c * B
Cb = (B - Y) / d
Cr = (R - Y) / e
```

### TO RGB
```
R = Y + e * Cr
G = Y - (a * e / b) * Cr - (c * d / b) * Cb
B = Y + d * Cb
```

Цветное изображение и его компоненты:

![image](content/320px-Barns_grand_tetons_YCbCr_separation.jpg)

Source:
- [wiki](https://ru.wikipedia.org/wiki/YCbCr)
- [github](https://gist.github.com/yohhoy/dafa5a47dade85d8b40625261af3776a)