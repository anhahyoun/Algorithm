fun main() {
    val br = System.`in`.bufferedReader()
    val g = br.readLine().toInt()
    val p = br.readLine().toInt()
    val parents = Array(g + 1) { it }

    fun find(x: Int): Int {
        if (x == parents[x]) return parents[x]
        parents[x] = find(parents[x])
        return parents[x]
    }

    fun union(x: Int, y: Int) {
        val xx = find(x)
        val yy = find(y)
        parents[yy] = xx
    }

    var cnt = 0
    for (i in 0 until p) {
        val gi = br.readLine().toInt()
        val dock = find(gi)
        if (dock == 0) break
        union(dock - 1, dock)
        cnt++
    }
    println(cnt)
}
