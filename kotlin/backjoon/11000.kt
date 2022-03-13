import java.util.*

fun main() {
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val lectures = Array(n) { br.readLine().split(" ").map { it.toInt() } }.sortedBy { it[0] }
    val pq = PriorityQueue<Pair<Int, Int>> { a, b -> a.second - b.second }
    lectures.forEach { (start, end) ->
        if (pq.isEmpty()) {
            pq.add(start to end)
            return@forEach
        }
        if (pq.peek().second<=start) pq.poll()
        pq.add(start to end)
    }
    println(pq.size)
}
