import java.util.*

fun main() {
    stack()
    iterator()
}

private fun iterator() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    repeat(br.readLine().toInt()) {
        val list = LinkedList<Char>()
        val iterator = list.listIterator()
        br.readLine().trim().forEach { c ->
            when (c) {
                '<' -> if (iterator.hasPrevious()) iterator.previous()
                '>' -> if (iterator.hasNext()) iterator.next()
                '-' -> if (iterator.hasPrevious()) {
                    iterator.previous()
                    iterator.remove()
                }
                else -> iterator.add(c)
            }
        }
        bw.write("${list.joinToString("")}\n")
    }
    bw.close()
}

private fun stack() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    repeat(br.readLine().toInt()) { _ ->
        val string = br.readLine()
        val leftStack = mutableListOf<Char>()
        val rightStack = mutableListOf<Char>()
        string.forEach { char ->
            when (char) {
                '<' -> leftStack.removeLastOrNull()?.let { rightStack.add(it) }
                '>' -> rightStack.removeLastOrNull()?.let { leftStack.add(it) }
                '-' -> leftStack.removeLastOrNull()
                else -> leftStack.add(char)
            }
        }
        leftStack.addAll(rightStack.reversed())
        bw.write("${leftStack.joinToString("")}\n")
    }
    bw.close()
}
