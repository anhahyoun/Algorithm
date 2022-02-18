import java.util.*

fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    val adj = hashMapOf<Int, MutableList<Pair<Int, Int>>>()
    repeat(m) {
        val (a, b, c) = br.readLine().split(" ").map { it.toInt() }
        adj[a]?.add(b to c) ?: adj.put(a, mutableListOf(b to c))
        adj[b]?.add(a to c) ?: adj.put(b, mutableListOf(a to c))
    }
    val answer = Array(n + 1) { 0 }

    fun dijkstra(start: Int) {
        val que = PriorityQueue<Pair<Int, Int>> { a, b -> a.second - b.second }
        val dist = Array(n + 1) { Int.MAX_VALUE }
        que.add(start to 0)
        dist[start] = 0

        while (que.isNotEmpty()) {
            val (nowNode, nowCost) = que.poll()
            if (dist[nowNode] < nowCost) continue
            adj[nowNode]?.forEach { (nextNode, nextCost) ->
                val cost = nowCost + nextCost
                if (dist[nextNode] > cost) {
                    dist[nextNode] = cost
                    answer[nextNode] = nowNode
                    que.add(nextNode to cost)
                }
            }
        }
    }

    fun print(start: Int) {
        var string = ""
        (1..n).forEach { i ->
            if (i == start) {
                string = "$string- "
                return@forEach
            }
            var node = i
            while (answer[node] != start) node = answer[node]
            string = "$string$node "
        }
        bw.write("${string}\n")
    }

    (1..n).forEach {
        dijkstra(it)
        print(it)
    }
    bw.close()
}
