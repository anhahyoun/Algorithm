fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val t = br.readLine().toInt()
    repeat(t) {
        val (n, m) = br.readLine().split(" ").map { it.toInt() }
        val dynamite = mutableMapOf<Int, MutableList<Pair<Int, Int>>>()
        repeat(m) {
            val (i, j, d) = br.readLine().split(" ").map { it.toInt() }
            dynamite[i]?.add(j to d) ?: dynamite.put(i, mutableListOf(j to d))
            dynamite[j]?.add(i to d) ?: dynamite.put(j, mutableListOf(i to d))
        }
        val visited = Array(n + 1) { 0 }

        fun dfs(island: Int): Int {
            visited[island] = 1
            var childCost = 0
            dynamite[island]?.forEach { (nextIsland, d) ->
                if (visited[nextIsland] == 1) return@forEach
                childCost += minOf(dfs(nextIsland), d)
            }
            return if (childCost == 0) Int.MAX_VALUE else childCost
        }
        val answer = dfs(1)
        bw.write("${if (answer== Int.MAX_VALUE) 0 else answer}\n")
    }
    bw.close()
}
