fun main() {
    val br = System.`in`.bufferedReader()
    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    val pre = Array(m) { br.readLine().split(" ").let { Pair(it[0].toInt(), it[1].toInt()) } }.sortedBy { it.first }
    val dp = Array(n + 1) { 1 }
    pre.forEach { (first, second) ->
        dp[second] = maxOf(dp[second], dp[first] + 1)
    }
    println(dp.slice(1..n).joinToString(" "))
}
