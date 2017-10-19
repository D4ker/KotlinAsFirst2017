@file:Suppress("UNUSED_PARAMETER")
package lesson5.task1

/**
 * Пример
 *
 * Время представлено строкой вида "11:34:45", содержащей часы, минуты и секунды, разделённые двоеточием.
 * Разобрать эту строку и рассчитать количество секунд, прошедшее с начала дня.
 */
fun timeStrToSeconds(str: String): Int {
    val parts = str.split(":")
    var result = 0
    for (part in parts) {
        val number = part.toInt()
        result = result * 60 + number
    }
    return result
}

/**
 * Пример
 *
 * Дано число n от 0 до 99.
 * Вернуть его же в виде двухсимвольной строки, от "00" до "99"
 */
fun twoDigitStr(n: Int) = if (n in 0..9) "0$n" else "$n"

/**
 * Пример
 *
 * Дано seconds -- время в секундах, прошедшее с начала дня.
 * Вернуть текущее время в виде строки в формате "ЧЧ:ММ:СС".
 */
fun timeSecondsToStr(seconds: Int): String {
    val hour = seconds / 3600
    val minute = (seconds % 3600) / 60
    val second = seconds % 60
    return String.format("%02d:%02d:%02d", hour, minute, second)
}

/**
 * Пример: консольный ввод
 */
fun main(args: Array<String>) {
    println("Введите время в формате ЧЧ:ММ:СС")
    val line = readLine()
    if (line != null) {
        val seconds = timeStrToSeconds(line)
        if (seconds == -1) {
            println("Введённая строка $line не соответствует формату ЧЧ:ММ:СС")
        }
        else {
            println("Прошло секунд с начала суток: $seconds")
        }
    }
    else {
        println("Достигнут <конец файла> в процессе чтения строки. Программа прервана")
    }
}

/**
 * Средняя
 *
 * Дата представлена строкой вида "15 июля 2016".
 * Перевести её в цифровой формат "15.07.2016".
 * День и месяц всегда представлять двумя цифрами, например: 03.04.2011.
 * При неверном формате входной строки вернуть пустую строку
 */
fun dateStrToDigit(str: String): String {
    val month = listOf<String>("января", "февраля", "марта", "апреля", "мая",
            "июня", "июля", "августа", "сентября", "октября", "ноября", "декабря")
    val s = str.split(" ")
    return try {
        val a = s[0].toInt()
        val b = month.indexOf(s[1]) + 1 //Если элемента нет в списке, b = -1+1 = 0
        val c = s[2].toInt()
        if ((s.size == 3) && (a in 1..31) && (b != 0) && (c >= 0))
            String.format("%02d.%02d.%d", a, b, c)
        else ""
    } catch (e: Exception) {
        ""
    }
}

/**
 * Средняя
 *
 * Дата представлена строкой вида "15.07.2016".
 * Перевести её в строковый формат вида "15 июля 2016".
 * При неверном формате входной строки вернуть пустую строку
 */
fun dateDigitToStr(digital: String): String {
    val month = listOf<String>("января", "февраля", "марта", "апреля", "мая",
            "июня", "июля", "августа", "сентября", "октября", "ноября", "декабря")
    val s = digital.split(".")
    return try {
        val a = s[0].toInt()
        val b = month[s[1].toInt() - 1]
        val c = s[2].toInt()
        if ((s.size == 3) && (a in 1..31) && (c >= 0))
            String.format("%d %s %d", a, b, c)
        else ""
    } catch (e: Exception) {
        ""
    }
}

/**
 * Средняя
 *
 * Номер телефона задан строкой вида "+7 (921) 123-45-67".
 * Префикс (+7) может отсутствовать, код города (в скобках) также может отсутствовать.
 * Может присутствовать неограниченное количество пробелов и чёрточек,
 * например, номер 12 --  34- 5 -- 67 -98 тоже следует считать легальным.
 * Перевести номер в формат без скобок, пробелов и чёрточек (но с +), например,
 * "+79211234567" или "123456789" для приведённых примеров.
 * Все символы в номере, кроме цифр, пробелов и +-(), считать недопустимыми.
 * При неверном формате вернуть пустую строку
 */
fun flattenPhoneNumber(phone: String): String {
    val list = mutableListOf<Char>()
    var b = false
    for (ch in phone) {
        if ((ch == ' ') || (ch == '-')) continue
        when (ch) {
            in '0'..'9' -> list.add(ch)
            '+', '(' -> if (ch !in list) list.add(ch) else return ""
            ')' -> if (!b) return ""
            else -> return ""
        }
        if ((ch == '(') || (ch == ')')) b = !b
    }
    if ('(' in list)
        if (!b) list.remove('(') else return ""
    return list.joinToString(separator = "")
}

/**
 * Средняя
 *
 * Результаты спортсмена на соревнованиях в прыжках в длину представлены строкой вида
 * "706 - % 717 % 703".
 * В строке могут присутствовать числа, черточки - и знаки процента %, разделённые пробелами;
 * число соответствует удачному прыжку, - пропущенной попытке, % заступу.
 * Прочитать строку и вернуть максимальное присутствующее в ней число (717 в примере).
 * При нарушении формата входной строки или при отсутствии в ней чисел, вернуть -1.
 */
fun bestLongJump(jumps: String): Int {
    val a = jumps.split(" ").filter { it != "" && it != "-" && it != "%" }
    var mx = -1
    try {
        for (i in a) mx = Math.max(mx, i.toInt())
    } catch (e: Exception) {
        return -1
    }
    return mx
}

/**
 * Сложная
 *
 * Результаты спортсмена на соревнованиях в прыжках в высоту представлены строкой вида
 * "220 + 224 %+ 228 %- 230 + 232 %%- 234 %".
 * Здесь + соответствует удачной попытке, % неудачной, - пропущенной.
 * Высота и соответствующие ей попытки разделяются пробелом.
 * Прочитать строку и вернуть максимальную взятую высоту (230 в примере).
 * При нарушении формата входной строки вернуть -1.
 */
fun bestHighJump(jumps: String): Int {
    val a = jumps.split(" ")
    var mx = -1
    try {
        for (i in 1 until a.size step 2)
            for (j in 0 until a[i].length)
                if (a[i][j] == '+') mx = Math.max(mx, a[i - 1].toInt())
    } catch (e: Exception) {
        return -1
    }
    return mx
}

/**
 * Сложная
 *
 * В строке представлено выражение вида "2 + 31 - 40 + 13",
 * использующее целые положительные числа, плюсы и минусы, разделённые пробелами.
 * Наличие двух знаков подряд "13 + + 10" или двух чисел подряд "1 2" не допускается.
 * Вернуть значение выражения (6 для примера).
 * Про нарушении формата входной строки бросить исключение IllegalArgumentException
 */
fun plusMinus(expression: String): Int {
    val a = expression.split(" ")
    try {
        var s = a[0].toInt()
        for (i in 1..a.size - 2 step 2)
            when (a[i]) {
                "+" -> s += a[i + 1].toInt()
                "-" -> s -= a[i + 1].toInt()
                else -> throw IllegalArgumentException()
            }
        return s
    } catch (e: Exception) {
        throw IllegalArgumentException()
    }
}

/**
 * Сложная
 *
 * Строка состоит из набора слов, отделённых друг от друга одним пробелом.
 * Определить, имеются ли в строке повторяющиеся слова, идущие друг за другом.
 * Слова, отличающиеся только регистром, считать совпадающими.
 * Вернуть индекс начала первого повторяющегося слова, или -1, если повторов нет.
 * Пример: "Он пошёл в в школу" => результат 9 (индекс первого 'в')
 */
fun firstDuplicateIndex(str: String): Int {
    val a = str.split(" ")
    var p = 0
    if (a.size == 1) return -1
    if (a[0].toLowerCase() == a[1].toLowerCase()) return 0
    for (i in 1..a.size - 2) {
        p += a[i - 1].length + 1
        if (a[i].toLowerCase() == a[i + 1].toLowerCase()) return p
    }
    return -1
}

/**
 * Сложная
 *
 * Строка содержит названия товаров и цены на них в формате вида
 * "Хлеб 39.9; Молоко 62.5; Курица 184.0; Конфеты 89.9".
 * То есть, название товара отделено от цены пробелом,
 * а цена отделена от названия следующего товара точкой с запятой и пробелом.
 * Вернуть название самого дорогого товара в списке (в примере это Курица),
 * или пустую строку при нарушении формата строки.
 * Все цены должны быть положительными
 */
fun mostExpensive(description: String): String {
    val a = description.split("; ")
    var mx = 0
    return try {
        var tp = a[mx].split(" ")[1].toDouble()
        for (i in 0 until a.size)
            if (a[i].split(" ")[1].toDouble() > tp) {
                mx = i
                tp = a[mx].split(" ")[1].toDouble()
            }
        a[mx].split(" ")[0]
    } catch (e: Exception) {
        ""
    }
}

/**
 * Сложная
 *
 * Перевести число roman, заданное в римской системе счисления,
 * в десятичную систему и вернуть как результат.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: XXIII = 23, XLIV = 44, C = 100
 *
 * Вернуть -1, если roman не является корректным римским числом
 */
fun fromRoman(roman: String): Int {
    if (roman == "") return -1
    val romanNum = listOf<String>("I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M")
    val num = listOf<Int>(1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000)
    var str = roman
    var res = 0
    return try {
        while (str.length > 1) {
            val p = str.substring(0, 2)
            if (p in romanNum) {
                res += num[romanNum.indexOf(p)]
                if (str.length > 2) str = str.substring(2, str.length)
                else break
            }
            else {
                res += num[romanNum.indexOf(str[0].toString())]
                str = str.substring(1, str.length)
            }
        }
        if (str.length == 1) res += num[romanNum.indexOf(str[0].toString())]
        res
    } catch (e: Exception) {
        -1
    }
}

/**
 * Очень сложная
 *
 * Имеется специальное устройство, представляющее собой
 * конвейер из cells ячеек (нумеруются от 0 до cells - 1 слева направо) и датчик, двигающийся над этим конвейером.
 * Строка commands содержит последовательность команд, выполняемых данным устройством, например +>+>+>+>+
 * Каждая команда кодируется одним специальным символом:
 *	> - сдвиг датчика вправо на 1 ячейку;
 *  < - сдвиг датчика влево на 1 ячейку;
 *	+ - увеличение значения в ячейке под датчиком на 1 ед.;
 *	- - уменьшение значения в ячейке под датчиком на 1 ед.;
 *	[ - если значение под датчиком равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей следующей командой ']' (с учётом вложенности);
 *	] - если значение под датчиком не равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей предыдущей командой '[' (с учётом вложенности);
 *      (комбинация [] имитирует цикл)
 *  пробел - пустая команда
 *
 * Изначально все ячейки заполнены значением 0 и датчик стоит на ячейке с номером N/2 (округлять вниз)
 *
 * После выполнения limit команд или всех команд из commands следует прекратить выполнение последовательности команд.
 * Учитываются все команды, в том числе несостоявшиеся переходы ("[" при значении под датчиком не равном 0 и "]" при
 * значении под датчиком равном 0) и пробелы.
 *
 * Вернуть список размера cells, содержащий элементы ячеек устройства после завершения выполнения последовательности.
 * Например, для 10 ячеек и командной строки +>+>+>+>+ результат должен быть 0,0,0,0,0,1,1,1,1,1
 *
 * Все прочие символы следует считать ошибочными и формировать исключение IllegalArgumentException.
 * То же исключение формируется, если у символов [ ] не оказывается пары.
 * Выход за границу конвейера также следует считать ошибкой и формировать исключение IllegalStateException.
 * Считать, что ошибочные символы и непарные скобки являются более приоритетной ошибкой чем выход за границу ленты,
 * то есть если в программе присутствует некорректный символ или непарная скобка, то должно быть выброшено
 * IllegalArgumentException.
 * IllegalArgumentException должен бросаться даже если ошибочная команда не была достигнута в ходе выполнения.
 *
 */
fun computeDeviceCells(cells: Int, commands: String, limit: Int): List<Int> {
    val const = listOf<Char>('>', '<', '+', '-', '[', ']', ' ')
    var counter = 0
    var numCom = -1

    for (i in commands) {
        if (i in const) when (i) { //проверка на парность
                '[' -> counter++
                ']' -> counter--
            }
        else throw IllegalArgumentException() //выбросить, если символ некорректен
        if (counter < 0) throw IllegalArgumentException() //выбросить, если парной скобки нет
    }
    if (counter != 0) throw IllegalArgumentException() //выбросить, если парной скобки нет
    //на этом проверка строки окончена, и есть смысл начать программу

    counter = 0
    var position = cells / 2
    val res = mutableListOf<Int>()

    for (i in 0 until cells) res.add(0) //заполняем массив нулями

    while ((numCom + 1 != commands.length) && (limit != counter)) { //пока есть команды и лимит не исчерпан:
        numCom++
        counter++
        var bracketNum = 0 //первая переменная для проверки скобок
        var bracketNum2 = 0 //вторая переменная
        when (commands[numCom]) {
            '>' -> if (position + 1 >= cells) throw IllegalStateException()
                    else position++

            '<' -> if (position - 1 < 0) throw IllegalStateException()
                    else position--

            '+' -> res[position]++

            '-' -> res[position]--

            '[' -> {
                if (res[position] == 0)
        //если 0, считаем скобки [, пока те не кончатся, а после находим скобку ] под номером последней скобки [
                    for (i in numCom until commands.length) {
                        if (commands[i] == '[') bracketNum++
                        if (commands[i] == ']') bracketNum2++
                        if (bracketNum == bracketNum2) {
                            numCom = i
                            break
                        }
                    }
            }

            ']' -> {
                if (res[position] != 0)
                    for (i in numCom downTo 0) {
                        if (commands[i] == '[') bracketNum++
                        if (commands[i] == ']') bracketNum2++
                        if (bracketNum == bracketNum2) {
                            numCom = i
                            break
                        }
                    }
            }

        }
    }
    return res
}
