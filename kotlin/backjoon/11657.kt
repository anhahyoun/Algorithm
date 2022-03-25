fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    val edges = Array(m) { br.readLine().split(" ").map { it.toInt() } }
    val distance = Array(n + 1) { Long.MAX_VALUE }
    distance[1] = 0

    fun bellmanFord(): Boolean {
        for (i in 1..n) {
            edges.forEach { (a, b, cost) ->
                if (distance[a] == Long.MAX_VALUE) return@forEach
                if (distance[b] > distance[a] + cost) {
                    distance[b] = distance[a] + cost
                    if (i == n) return true
                }
            }
        }
        return false
    }

    if (bellmanFord()) {
        bw.write("-1\n")
    } else {
        for (i in 2..n) {
            bw.write("${if (distance[i] == Long.MAX_VALUE) -1 else distance[i]}\n")
        }
    }
    bw.close()
}
