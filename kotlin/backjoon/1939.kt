import java.util.*

fun main() {
    val br = System.`in`.bufferedReader()
    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    val bridge = mutableMapOf<Int, MutableList<Pair<Int, Int>>>()
    var maxCost = 0
    var minCost = 0

    repeat(m) {
        val (a, b, c) = br.readLine().split(" ").map { it.toInt() }
        bridge[a]?.add(b to c) ?: bridge.put(a, mutableListOf(b to c))
        bridge[b]?.add(a to c) ?: bridge.put(b, mutableListOf(a to c))
        maxCost = maxOf(maxCost, c)
    }
    val (start, end) = br.readLine().split(" ").map { it.toInt() }

    fun bfs(cost: Int): Boolean {
        val visited = Array(n + 1) { 0 }
        val queue = LinkedList<Int>()
        queue.add(start)
        visited[start] = 1
        while (queue.isNotEmpty()) {
            val now = queue.poll()
            bridge[now]?.forEach { (next, nextCost) ->
                if (visited[next] == 1 || nextCost < cost) return@forEach
                if (next == end) return true
                visited[next] = 1
                queue.add(next)
            }
        }
        return false
    }
    while (minCost <= maxCost) {
        val mid = (minCost + maxCost) / 2
        if (bfs(mid)) minCost = mid + 1 else maxCost = mid - 1
    }
    println(maxCost)
}
