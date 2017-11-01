@file:Suppress("UNUSED_PARAMETER")
package lesson4.task1

import lesson1.task1.discriminant

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
        when {
            y < 0 -> listOf()
            y == 0.0 -> listOf(0.0)
            else -> {
                val root = Math.sqrt(y)
                // Результат!
                listOf(-root, root)
            }
        }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + Math.sqrt(d)) / (2 * a)
    val y2 = (-b - Math.sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double {
    var k = 0.0
    for (element in v) k += element * element
    return Math.sqrt(k)
}

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double =
        if (list.isNotEmpty()) list.sum() / list.size else 0.0

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val s = mean(list)
    for (i in 0 until list.size) list[i] -= s
    return list
}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.0.
 */
fun times(a: List<Double>, b: List<Double>): Double {
    var c = 0.0
    for (i in 0 until a.size) c += a[i] * b[i]
    return c
}

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0.0 при любом x.
 */
fun polynom(p: List<Double>, x: Double): Double {
    var k = 0.0
    if (p.isNotEmpty())
        for (i in 0 until p.size)
            k += p[i] * Math.pow(x, i.toDouble())
    return k
}

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Double>): MutableList<Double> {
    if (list.isNotEmpty())
        for (i in 1 until list.size) list[i] += list[i - 1]
    return list
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    val a = mutableListOf<Int>()
    var k = n
    var i = 2
    while (i in 2..Math.sqrt(k.toDouble()).toInt()) {
        if (k % i == 0) {
            a.add(i)
            k /= i
            i = 1
        }
        i++
    }
    a.add(k)
    return a
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 */
fun factorizeToString(n: Int): String = factorize(n).joinToString(separator = "*")

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    val a = mutableListOf<Int>()
    var k = n
    while (k > 0) {
        a.add(0, k % base)
        k /= base
    }
    if (n == 0) a.add(0)
    return a
}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 */
val alphabet = "0123456789abcdefghijklmnopqrstuvwxyz"

fun convertToString(n: Int, base: Int): String {
    val a = mutableListOf<String>()
    var k = n
    while (k > 0) {
        a.add(0, alphabet[k % base].toString())
        k /= base
    }
    if (n == 0) a.add("0")
    return a.joinToString(separator = "")
}

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
// Вспомогательная функция
fun numToPow(num: Int, power: Int): Int {
    var newNum = 1
    for (i in 0 until power) {
        newNum *= num
    }
    return newNum
}

fun decimal(digits: List<Int>, base: Int): Int {
    var s = 0
    for (i in 0 until digits.size)
        s += digits[i] * numToPow(base, digits.size - 1 - i)
    return s
}

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 */
fun decimalFromString(str: String, base: Int): Int {
    val newDigits = mutableListOf<Int>()
    for (i in 0 until str.length)
        newDigits.add(alphabet.indexOf(str[i], 0))
    return decimal(newDigits, base)
}

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    val res = mutableListOf<String>()
    val numToSymbol = mapOf(1 to "I", 4 to "IV", 5 to "V", 9 to "IX", 10 to "X", 40 to "XL",
            50 to "L", 90 to "XC", 100 to "C", 400 to "CD", 500 to "D", 900 to "CM", 1000 to "M")
    var partNum = n
    /* Счётчик, в который будем заносить степень десятки при переборе цифр числа.
    Первой цифре соответствует 10^0, второй - 10^1, третьей - 10^2, а четвёртой и всем последующим - 10^3 */
    var counter = 1
    // Перебираем все цифры числа (справа налево)
    while (partNum > 0) {
        // Если нынешняя цифра не относится к тясячам, занести её в 'newDigit', иначе занести все оставшиеся цифры
        val newDigit = if (counter < 1000) partNum % 10 else partNum
        /* Если произведение цифры на степень десятки находится в имеющемся объекте 'numToSymbol',
        занести в итоговый список соответствующий символ из данного объекта */
        if (numToSymbol.containsKey(newDigit * counter)) res.add(0, numToSymbol.getValue(newDigit * counter))
        else {
            // Упрощаем цифру, отнимая от неё 5, если она в промежутке от 6 до 8 и степень десятки меньше 3
            val simplifiedDigit = if ((newDigit in 6..8) && (counter < 1000)) newDigit - 5 else newDigit
            // Добавляем в итоговый список 'simplifiedDigit' символов 'I', 'X', 'C', 'M'
            for (i in 1..simplifiedDigit) res.add(0, numToSymbol.getValue(counter))
            /* Если наша цифра поддавалась ранее упрощению и стпень десятки была меньше 3,
            добавить в итоговую строку 'V', 'L', 'D' */
            if ((newDigit in 6..8) && (counter < 1000)) res.add(0, numToSymbol.getValue(5 * counter))
        }
        // Если счётчик равен 10^3, значит программа уже проверила тысячную цифру -> выходим из цикла
        if (counter == 1000) break
        // В следующей интерации будем брать новую цифру -> увеличиваем степень десятки
        counter *= 10
        // Выбрасываем проверенную только что цифру из числа
        partNum /= 10
    }
    return res.joinToString("")
}

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    val firstNum = listOf("", "один", "два", "три", "четыре", "пять", "шесть",
            "семь", "восемь", "девять")
    val secondNum = listOf("десять", "одиннадцать", "двенадцать", "тринадцать","четырнадцать", "пятнадцать",
            "шестнадцать", "семнадцать", "восемнадцать", "девятнадцать")
    val thirdNum = listOf("", "", "двадцать", "тридцать", "сорок", "пятьдесят", "шестьдесят",
            "семьдесят", "восемьдесят", "девяносто")
    val fourthNum = listOf("", "сто", "двести", "триста", "четыреста", "пятьсот", "шестьсот",
            "семьсот", "восемьсот", "девятьсот")
    val res = mutableListOf<String>()
    var partNum = n
    var counter = 0
    while (partNum > 0) {
        val newDigit = partNum % 10
        counter++
        when (counter) {

            1 -> if (partNum % 100 !in 10..19) res.add(0, firstNum[newDigit])
                    else res.add(0, secondNum[newDigit])

            2, 5 -> res.add(0, thirdNum[newDigit])

            3, 6 -> res.add(0, fourthNum[newDigit])

            4 -> if (partNum % 100 in 10..19)
                    res.add(0, secondNum[newDigit] + " тысяч")
                else when (newDigit) {
                    0 -> res.add(0, "тысяч")
                    1 -> res.add(0, "одна тысяча")
                    2 -> res.add(0, "две тысячи")
                    3, 4 -> res.add(0, firstNum[newDigit] + " тысячи")
                    in 5..9 -> res.add(0, firstNum[newDigit] + " тысяч")
                }

        }
        partNum /= 10
    }
    return res.filter { it != "" }.joinToString(separator = " ")
}