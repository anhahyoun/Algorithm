fun main() {
    val br = System.`in`.bufferedReader()
    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    val (num, trueList) = br.readLine().split(" ").run { get(0) to subList(1, lastIndex + 1).map { it.toInt() } }
    val parent = Array(n + 1) { it }
    trueList.forEach { parent[it] = 0 }

    fun find(x: Int): Int {
        if (parent[x] != x) parent[x] = find(parent[x])
        return parent[x]
    }

    fun union(x: Int, y: Int) {
        val xx = find(x)
        val yy = find(y)
        if (xx > yy) parent[xx] = yy else parent[yy] = xx
    }

    var cnt = 0
    val party = Array(m) { br.readLine().split(" ").map { it.toInt() } }

    party.forEach { list ->
        for (i in 2 until list.size) {
            union(list[i - 1], list[i])
        }
    }
    party.forEach { list -> if (parent[find(list[1])] != 0) cnt++ }
    println(cnt)
}
