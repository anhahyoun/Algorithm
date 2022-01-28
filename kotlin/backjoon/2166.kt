import kotlin.math.abs

fun main() {
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val lst = MutableList(n) { br.readLine().split(" ").map { it.toLong() } }.apply { add(this[0]) }
    var area = 0L
    for (i in 0 until n) {
        area += lst[i][0] * lst[i + 1][1]
        area -= lst[i][1] * lst[i + 1][0]
    }

    println(String.format("%.1f", abs(area)/2.0))
}
