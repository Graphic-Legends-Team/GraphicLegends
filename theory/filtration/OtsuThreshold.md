# Пороговая фильтрация методом Оцу
Зачастую пользователю нужно подстроить порог для лучшего качества изображения. Конечно, делать это вручную тяжело, поэтому существует несколько методов автоматической настройки порога, одним из которых является Оцу.

Суть метода Оцу заключается в разделении на два класса "полезные" и "фоновые" и последующей минимизации дисперсии внутри этих классов, и она определяется как взвешенная сумма дисперсий двух классов:

![image](https://user-images.githubusercontent.com/79001610/211884883-447893c8-01f9-4204-9715-4631467323bf.png) (1)

, где веса - вероятности двух классов, разделённых порогом t.

Оцу было доказано, что минимизация дисперсии внутри классов равносильна максимизации дисперсии между классами:

![image](https://user-images.githubusercontent.com/79001610/211885259-27dc61ee-1939-4136-ae0c-ee64e58ed362.png) (2)

![image](https://user-images.githubusercontent.com/79001610/211885405-e34a1e88-c935-44fa-9f31-488c97a98065.png)

А если проще, то:
1) Собираем гистограмму. Она показывает количество пикселей на каждое значение интенсивности от 0 до 255.
2) Считаем сумму интенсивностей пикселей

Для каждого значения порога от 0 до 254:
1) Рассчитываем вероятности попадания пикселей в класс "полезных" и в класс "фоновых". Класс полезных - количество пикселей, интенсивность которых меньше либо равна значению порога.
2) Рассчитываем значения среднего арифметического для двух классов.
3) По формуле 2 вычисляем дисперсию и сравниваем с наибольшей, запоминая порог

[Тестирование (изображение 3)](content/threshold.jpg)