fun main() {
    val br = System.`in`.bufferedReader()
    val dx = listOf(0, 1, 0, -1)
    val dy = listOf(1, 0, -1, 0)

    val (m, n) = br.readLine().split(" ").map { it.toInt() }
    val graph = Array(m) { br.readLine().split(" ").map { it.toInt() } }
    val dp = Array(m) { Array(n) { -1 } }

    fun dfs(nowX: Int, nowY: Int, nowH: Int): Int {
        if (nowX == m - 1 && nowY == n - 1) return 1
        if (dp[nowX][nowY] == -1) {
            dp[nowX][nowY] = 0

            for (i in 0..3) {
                val nextX = nowX + dx[i]
                val nextY = nowY + dy[i]
                if (nextX !in 0 until m || nextY !in 0 until n) continue
                if (graph[nextX][nextY] >= nowH) continue
                dp[nowX][nowY] += dfs(nextX, nextY, graph[nextX][nextY])
            }
        }
        return dp[nowX][nowY]
    }
    dfs(0,0,graph[0][0])
    println(dfs(0,0,graph[0][0]))
}
