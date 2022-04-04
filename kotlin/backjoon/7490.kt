fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val ans = mutableListOf<String>()
    fun re(x: Int, n: Int, string: String, num: Int, prev: Pair<Int, String>) {
        if (x == n) {
            if (num == 0) ans.add(string)
            return
        }
        re(x + 1, n, "$string+${x + 1}", num + x + 1, x+1 to "+")
        re(x + 1, n, "$string-${x + 1}", num - (x + 1), x+1 to "-")
        val newNum = when (prev.second) {
            "-" -> (num + prev.first) - ("$x${x + 1}".toInt())
            "+" -> (num - prev.first) + ("$x${x + 1}".toInt())
            else -> "$num${x + 1}".toInt()
        }
        re(x + 1, n, "$string ${x + 1}", newNum, newNum to prev.second)
    }
    repeat(br.readLine().toInt()) {
        val n = br.readLine().toInt()
        ans.clear()
        re(1, n, "1", 1, 1 to "")
        ans.sorted().forEach { bw.write("$it\n") }
        bw.write("\n")
    }
    bw.close()
}
