fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    val m = br.readLine().toInt()
    val graph = Array(n + 1) { i -> Array(n + 1) { j -> if (i == j) 0 else 100000000 } }
    repeat(m) {
        val (x, y, w) = br.readLine().split(" ").map { it.toInt() }
        graph[x][y] = minOf(w, graph[x][y])
    }
    val (start, end) = br.readLine().split(" ").map { it.toInt() }
    val visited = Array(n + 1) { 0 }
    val dis = Array(n + 1) { d -> graph[start][d] }

    fun getMinDisIndex(): Int {
        var min = 100000000
        var index = -1
        for (i in 1..n) {
            if (dis[i] < min && visited[i] == 0) {
                min = dis[i]
                index = i
            }
        }
        return index
    }

    fun dijkstra(start: Int) {
        visited[start] = 1
        repeat(n - 1) {
            val i = getMinDisIndex()
            if (i == -1) return@repeat
            visited[i] = 1
            (1..n).forEach { w ->
                dis[w] = minOf(dis[i] + graph[i][w], dis[w])
            }
        }
    }

    dijkstra(start)

    bw.write(dis[end].toString())
    bw.close()
}
