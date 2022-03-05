fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val (n, r, q) = br.readLine().split(" ").map { it.toInt() }
    val tree = mutableMapOf<Int, MutableList<Int>>()

    repeat(n - 1) {
        val (u, v) = br.readLine().split(" ").map { it.toInt() }
        tree[u]?.add(v) ?: tree.put(u, mutableListOf(v))
        tree[v]?.add(u) ?: tree.put(v, mutableListOf(u))
    }
    //key를 루트로 하는 서브트리의 정점 개수
    val dp = mutableMapOf<Int, Int>()
    val visited = Array(100001) { 0 }

    fun dfs(u: Int) {
        var child = 1
        visited[u] = 1
        tree[u]?.forEach {
            if (visited[it] == 1) return@forEach
            dfs(it)
            child += dp[it] ?: 0
        }
        dp[u] = child
    }
    dfs(r)
    repeat(q) { bw.write("${dp[br.readLine().toInt()]}\n") }
    bw.close()
}
