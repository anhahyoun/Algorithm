fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    var n: Int
    val minDP = mutableListOf<Long>(0, 0, 1, 7, 4, 2, 6, 8, 10).apply {
        (9..100).forEach { i ->
            add(Long.MAX_VALUE)
            (2..7).forEach { j -> this[i] = minOf(get(i - j) * 10 + if (j == 6) 0 else get(j), get(i)) }
        }
    }

    fun getMaxNum(matchsticks: Int): String {
        val quotient = if (matchsticks % 2 == 1) matchsticks / 2 - 1 else matchsticks / 2
        var num = ""
        repeat(quotient) { num = "1$num" }
        return if (matchsticks % 2 == 1) "7$num" else num
    }

    repeat(br.readLine().toInt()) {
        n = br.readLine().toInt()
        bw.write("${minDP[n]} ${getMaxNum(n)}\n")
    }
    bw.close()
}
