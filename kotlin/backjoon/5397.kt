fun main() {
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
