@file:Suppress("UNUSED_PARAMETER")
package lesson6.task2

import lesson6.task3.Graph

/**
 * Клетка шахматной доски. Шахматная доска квадратная и имеет 8 х 8 клеток.
 * Поэтому, обе координаты клетки (горизонталь row, вертикаль column) могут находиться в пределах от 1 до 8.
 * Горизонтали нумеруются снизу вверх, вертикали слева направо.
 */
data class Square(val column: Int, val row: Int) {
    /**
     * Пример
     *
     * Возвращает true, если клетка находится в пределах доски
     */
    fun inside(): Boolean = column in 1..8 && row in 1..8

    /**
     * Простая
     *
     * Возвращает строковую нотацию для клетки.
     * В нотации, колонки обозначаются латинскими буквами от a до h, а ряды -- цифрами от 1 до 8.
     * Для клетки не в пределах доски вернуть пустую строку
     */
    fun notation(): String {
        if (!inside()) return ""
        return "${alphabet[column]}$row"
    }
}

val alphabet = " abcdefgh"

/**
 * Простая
 *
 * Создаёт клетку по строковой нотации.
 * В нотации, колонки обозначаются латинскими буквами от a до h, а ряды -- цифрами от 1 до 8.
 * Если нотация некорректна, бросить IllegalArgumentException
 */
fun square(notation: String): Square {
    try {
        return Square(alphabet.indexOf(notation[0]), notation[1].toString().toInt())
    } catch (e: NumberFormatException) {
        throw IllegalArgumentException()
    }
}

/**
 * Простая
 *
 * Определить число ходов, за которое шахматная ладья пройдёт из клетки start в клетку end.
 * Шахматная ладья может за один ход переместиться на любую другую клетку
 * по вертикали или горизонтали.
 * Ниже точками выделены возможные ходы ладьи, а крестиками -- невозможные:
 *
 * xx.xxххх
 * xх.хxххх
 * ..Л.....
 * xх.хxххх
 * xx.xxххх
 * xx.xxххх
 * xx.xxххх
 * xx.xxххх
 *
 * Если клетки start и end совпадают, вернуть 0.
 * Если любая из клеток некорректна, бросить IllegalArgumentException().
 *
 * Пример: rookMoveNumber(Square(3, 1), Square(6, 3)) = 2
 * Ладья может пройти через клетку (3, 3) или через клетку (6, 1) к клетке (6, 3).
 */
fun rookMoveNumber(start: Square, end: Square): Int {
    try {
        val compareColumn = start.column == end.column
        val compareRow = start.row == end.row
        return when {
            (compareColumn && compareRow) -> 0
            (!compareColumn && !compareRow) -> 2
            else -> 1
        }
    } catch (e: NumberFormatException) {
        throw IllegalArgumentException()
    }
}

/**
 * Средняя
 *
 * Вернуть список из клеток, по которым шахматная ладья может быстрее всего попасть из клетки start в клетку end.
 * Описание ходов ладьи см. предыдущую задачу.
 * Список всегда включает в себя клетку start. Клетка end включается, если она не совпадает со start.
 * Между ними должны находиться промежуточные клетки, по порядку от start до end.
 * Примеры: rookTrajectory(Square(3, 3), Square(3, 3)) = listOf(Square(3, 3))
 *          (здесь возможен ещё один вариант)
 *          rookTrajectory(Square(3, 1), Square(6, 3)) = listOf(Square(3, 1), Square(3, 3), Square(6, 3))
 *          (здесь возможен единственный вариант)
 *          rookTrajectory(Square(3, 5), Square(8, 5)) = listOf(Square(3, 5), Square(8, 5))
 * Если возможно несколько вариантов самой быстрой траектории, вернуть любой из них.
 */
fun rookTrajectory(start: Square, end: Square): List<Square> =
        when (rookMoveNumber(start, end)) {
            0 -> listOf(start)
            1 -> listOf(start, end)
            else -> listOf(start, Square(start.column, end.row), end)
        }

/**
 * Простая
 *
 * Определить число ходов, за которое шахматный слон пройдёт из клетки start в клетку end.
 * Шахматный слон может за один ход переместиться на любую другую клетку по диагонали.
 * Ниже точками выделены возможные ходы слона, а крестиками -- невозможные:
 *
 * .xxx.ххх
 * x.x.xххх
 * xxСxxxxx
 * x.x.xххх
 * .xxx.ххх
 * xxxxx.хх
 * xxxxxх.х
 * xxxxxхх.
 *
 * Если клетки start и end совпадают, вернуть 0.
 * Если клетка end недостижима для слона, вернуть -1.
 * Если любая из клеток некорректна, бросить IllegalArgumentException().
 *
 * Примеры: bishopMoveNumber(Square(3, 1), Square(6, 3)) = -1; bishopMoveNumber(Square(3, 1), Square(3, 7)) = 2.
 * Слон может пройти через клетку (6, 4) к клетке (3, 7).
 */
fun bishopMoveNumber(start: Square, end: Square): Int {
    try {
        val comparePosition = Math.abs(start.column - end.column) == Math.abs(start.row - end.row)
        val evenStart = (start.column + start.row) % 2 == 0
        val evenEnd = (end.column + end.row) % 2 == 0
        return when {
            (start == end) -> 0
            ((evenStart && !evenEnd) || (!evenStart && evenEnd)) -> -1
            (comparePosition) -> 1
            else -> 2
        }
    } catch (e: NumberFormatException) {
        throw IllegalArgumentException()
    }
}

/**
 * Сложная
 *
 * Вернуть список из клеток, по которым шахматный слон может быстрее всего попасть из клетки start в клетку end.
 * Описание ходов слона см. предыдущую задачу.
 *
 * Если клетка end недостижима для слона, вернуть пустой список.
 *
 * Если клетка достижима:
 * - список всегда включает в себя клетку start
 * - клетка end включается, если она не совпадает со start.
 * - между ними должны находиться промежуточные клетки, по порядку от start до end.
 *
 * Примеры: bishopTrajectory(Square(3, 3), Square(3, 3)) = listOf(Square(3, 3))
 *          bishopTrajectory(Square(3, 1), Square(3, 7)) = listOf(Square(3, 1), Square(6, 4), Square(3, 7))
 *          bishopTrajectory(Square(1, 3), Square(6, 8)) = listOf(Square(1, 3), Square(6, 8))
 * Если возможно несколько вариантов самой быстрой траектории, вернуть любой из них.
 */
fun bishopTrajectory(start: Square, end: Square): List<Square> {
    when (bishopMoveNumber(start, end)) {
        -1 -> return listOf()
        0 -> return listOf(start)
        1 -> return listOf(start, end)
    }
    var deltaY = (end.row - start.row) / 2
    var deltaX = (end.column - start.column) / 2
    return when {
        start.column == end.column -> {
            deltaX = if (start.column + deltaY in 1..8) deltaY else -deltaY
            listOf(start, Square(start.column + deltaX, start.row + deltaY), end)
        }
        start.row == end.row -> {
            deltaY = if (start.row + deltaX !in 1..8) deltaX else -deltaX
            listOf(start, Square(start.column + deltaX, start.row + deltaY), end)
        }
        else -> {
            deltaY += if (deltaY > 0) 1 else -1
            deltaX = if (start.column + deltaY in 1..8) deltaY else -deltaY
            listOf(start, Square(start.column + deltaX, start.row + deltaY), end)
        }
    }
}

/**
 * Средняя
 *
 * Определить число ходов, за которое шахматный король пройдёт из клетки start в клетку end.
 * Шахматный король одним ходом может переместиться из клетки, в которой стоит,
 * на любую соседнюю по вертикали, горизонтали или диагонали.
 * Ниже точками выделены возможные ходы короля, а крестиками -- невозможные:
 *
 * xxxxx
 * x...x
 * x.K.x
 * x...x
 * xxxxx
 *
 * Если клетки start и end совпадают, вернуть 0.
 * Если любая из клеток некорректна, бросить IllegalArgumentException().
 *
 * Пример: kingMoveNumber(Square(3, 1), Square(6, 3)) = 3.
 * Король может последовательно пройти через клетки (4, 2) и (5, 2) к клетке (6, 3).
 */
fun kingMoveNumber(start: Square, end: Square): Int {
    try {
        val distanceX = Math.abs(end.column - start.column)
        val distanceY = Math.abs(end.row - start.row)
        return if (distanceX > distanceY) distanceX else distanceY
    } catch (e: NumberFormatException) {
        throw IllegalArgumentException()
    }
}

/**
 * Сложная
 *
 * Вернуть список из клеток, по которым шахматный король может быстрее всего попасть из клетки start в клетку end.
 * Описание ходов короля см. предыдущую задачу.
 * Список всегда включает в себя клетку start. Клетка end включается, если она не совпадает со start.
 * Между ними должны находиться промежуточные клетки, по порядку от start до end.
 * Примеры: kingTrajectory(Square(3, 3), Square(3, 3)) = listOf(Square(3, 3))
 *          (здесь возможны другие варианты)
 *          kingTrajectory(Square(3, 1), Square(6, 3)) = listOf(Square(3, 1), Square(4, 2), Square(5, 2), Square(6, 3))
 *          (здесь возможен единственный вариант)
 *          kingTrajectory(Square(3, 5), Square(6, 2)) = listOf(Square(3, 5), Square(4, 4), Square(5, 3), Square(6, 2))
 * Если возможно несколько вариантов самой быстрой траектории, вернуть любой из них.
 */
fun kingTrajectory(start: Square, end: Square): List<Square> {
    val list = mutableListOf<Square>(start)
    val signX = end.column - start.column
    val signY = end.row - start.row
    var xShift = start.column
    var yShift = start.row
    while (list[list.size - 1] != end) {
        if (xShift != end.column) {
            val i = if (signX < 0) -1 else 1
            xShift += i
        }
        if (yShift != end.row) {
            val j = if (signY < 0) -1 else 1
            yShift += j
        }
        list.add(Square(xShift, yShift))
    }
    return list
}

/**
 * Сложная
 *
 * Определить число ходов, за которое шахматный конь пройдёт из клетки start в клетку end.
 * Шахматный конь одним ходом вначале передвигается ровно на 2 клетки по горизонтали или вертикали,
 * а затем ещё на 1 клетку под прямым углом, образуя букву "Г".
 * Ниже точками выделены возможные ходы коня, а крестиками -- невозможные:
 *
 * .xxx.xxx
 * xxKxxxxx
 * .xxx.xxx
 * x.x.xxxx
 * xxxxxxxx
 * xxxxxxxx
 * xxxxxxxx
 * xxxxxxxx
 *
 * Если клетки start и end совпадают, вернуть 0.
 * Если любая из клеток некорректна, бросить IllegalArgumentException().
 *
 * Пример: knightMoveNumber(Square(3, 1), Square(6, 3)) = 3.
 * Конь может последовательно пройти через клетки (5, 2) и (4, 4) к клетке (6, 3).
 */

// Граф движения шахматного коня
fun useGraph(): Graph {
    // Создаём вершины
    val g = Graph()
    for (i in 1..8) {
        for (j in 1..8) {
            g.addVertex("$j$i")
        }
    }
    // Соединяем вершины
    for (i in 1..8) {
        for (j in 1..8) {
            val edge1 = Square(j + 1, i + 2)
            val edge2 = Square(j + 1, i - 2)
            val edge3 = Square(j + 2, i + 1)
            val edge4 = Square(j + 2, i - 1)
            val edge5 = Square(j - 1, i + 2)
            val edge6 = Square(j - 1, i - 2)
            val edge7 = Square(j - 2, i + 1)
            val edge8 = Square(j - 2, i - 1)
            if (edge1.inside()) g.connect("$j$i", "${edge1.column}${edge1.row}")
            if (edge2.inside()) g.connect("$j$i", "${edge2.column}${edge2.row}")
            if (edge3.inside()) g.connect("$j$i", "${edge3.column}${edge3.row}")
            if (edge4.inside()) g.connect("$j$i", "${edge4.column}${edge4.row}")
            if (edge5.inside()) g.connect("$j$i", "${edge5.column}${edge5.row}")
            if (edge6.inside()) g.connect("$j$i", "${edge6.column}${edge6.row}")
            if (edge7.inside()) g.connect("$j$i", "${edge7.column}${edge7.row}")
            if (edge8.inside()) g.connect("$j$i", "${edge8.column}${edge8.row}")
        }
    }
    return g
}

fun knightMoveNumber(start: Square, end: Square): Int {
    try {
        return useGraph().bfs("${start.column}${start.row}", "${end.column}${end.row}")
    } catch (e: NumberFormatException) {
        throw IllegalArgumentException()
    }
}

/**
 * Очень сложная
 *
 * Вернуть список из клеток, по которым шахматный конь может быстрее всего попасть из клетки start в клетку end.
 * Описание ходов коня см. предыдущую задачу.
 * Список всегда включает в себя клетку start. Клетка end включается, если она не совпадает со start.
 * Между ними должны находиться промежуточные клетки, по порядку от start до end.
 * Примеры:
 *
 * knightTrajectory(Square(3, 3), Square(3, 3)) = listOf(Square(3, 3))
 * здесь возможны другие варианты)
 * knightTrajectory(Square(3, 1), Square(6, 3)) = listOf(Square(3, 1), Square(5, 2), Square(4, 4), Square(6, 3))
 * (здесь возможен единственный вариант)
 * knightTrajectory(Square(3, 5), Square(5, 6)) = listOf(Square(3, 5), Square(5, 6))
 * (здесь опять возможны другие варианты)
 * knightTrajectory(Square(7, 7), Square(8, 8)) =
 *     listOf(Square(7, 7), Square(5, 8), Square(4, 6), Square(6, 7), Square(8, 8))
 *
 * Если возможно несколько вариантов самой быстрой траектории, вернуть любой из них.
 */
fun knightTrajectory(start: Square, end: Square): List<Square> {
    val array = useGraph().trajectoryOfMove("${start.column}${start.row}", "${end.column}${end.row}")
    val res = mutableListOf<Square>()
    for (i in 0 until array.size) {
        res.add(Square(array[i][0].toString().toInt(), array[i][1].toString().toInt()))
    }
    return res
}
