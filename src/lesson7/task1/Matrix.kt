@file:Suppress("UNUSED_PARAMETER", "unused")
package lesson7.task1

/**
 * Ячейка матрицы: row = ряд, column = колонка
 */
data class Cell(val row: Int, val column: Int)

/**
 * Интерфейс, описывающий возможности матрицы. E = тип элемента матрицы
 */
interface Matrix<E> {
    /** Высота */
    val height: Int

    /** Ширина */
    val width: Int

    /**
     * Доступ к ячейке.
     * Методы могут бросить исключение, если ячейка не существует или пуста
     */
    operator fun get(row: Int, column: Int): E
    operator fun get(cell: Cell): E

    /**
     * Запись в ячейку.
     * Методы могут бросить исключение, если ячейка не существует
     */
    operator fun set(row: Int, column: Int, value: E)
    operator fun set(cell: Cell, value: E)
}

/**
 * Простая
 *
 * Метод для создания матрицы, должен вернуть РЕАЛИЗАЦИЮ Matrix<E>.
 * height = высота, width = ширина, e = чем заполнить элементы.
 * Бросить исключение IllegalArgumentException, если height или width <= 0.
 */
fun <E> createMatrix(height: Int, width: Int, e: E): Matrix<E> =
        if (height > 0 && width > 0) MatrixImpl<E>(height, width, e) else throw IllegalArgumentException()

/**
 * Средняя сложность
 *
 * Реализация интерфейса "матрица"
 */
class MatrixImpl<E>(override val height: Int, override val width: Int, e: E) : Matrix<E> {
    private val elementsOfMatrix = mutableListOf<MutableList<E>>()

    init {
        for (i in 0 until height) {
            elementsOfMatrix.add(mutableListOf())
            for (j in 0 until width) {
                elementsOfMatrix[i].add(e)
            }
        }
    }

    override fun get(row: Int, column: Int): E  = elementsOfMatrix[row][column]

    override fun get(cell: Cell): E  = get(cell.row, cell.column)

    override fun set(row: Int, column: Int, value: E) {
        elementsOfMatrix[row][column] = value
    }

    override fun set(cell: Cell, value: E) = set(cell.row, cell.column, value)

    override fun equals(other: Any?): Boolean {
        if (other is Matrix<*> && height == other.height && width == other.width) {
            for (i in 0 until height) {
                for (j in 0 until width) {
                    if (this[i, j] != other[i, j]) return false
                }
            }
        }
        return true
    }

    override fun toString(): String {
        val str = StringBuilder()
        str.append("[[")
        str.append(elementsOfMatrix[0].joinToString(", "))
        str.append("]")
        for (i in 1 until height) {
            str.append(", [")
            str.append(elementsOfMatrix[i].joinToString(", "))
            str.append("]")
        }
        str.append("]")
        return str.toString()
    }

    override fun hashCode(): Int {
        var result = height
        result = 31 * result + width
        result = 31 * result + elementsOfMatrix.hashCode()
        return result
    }
}

