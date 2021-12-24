fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    val arr = Array(m) { 0 }

    fun dfs(x: Int, start: Int) {
        if (x == m) {
            bw.write("${arr.joinToString(" ")}\n")
            return
        } else {
            for (i in start..n) {
                arr[x] = i
                dfs(x + 1, i + 1)
            }
        }
    }

    dfs(0, 1)
    bw.close()
}
