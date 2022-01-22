fun main() {
    data class Road(val end: Int, val distance: Int)

    val br = System.`in`.bufferedReader()
    val (n, d) = br.readLine().split(" ").map { it.toInt() }
    val roadMap = mutableMapOf<Int, ArrayList<Road>>()
    repeat(n) {
        val (s, e, dis) = br.readLine().split(" ").map { it.toInt() }
        roadMap[s]?.add(Road(e, dis)) ?: roadMap.put(s, arrayListOf(Road(e, dis)))
    }
    val dist = Array(d + 1) { it }

    for (i in 0 .. d) {
        if (i != 0) dist[i] = minOf(dist[i], dist[i - 1] + 1)
        roadMap[i]?.forEach {
            if (it.end <= d && dist[it.end] > dist[i] + it.distance) {
                dist[it.end] = dist[i] + it.distance
            }
        }
    }
    println(dist[d])
}
