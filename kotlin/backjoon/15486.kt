fun main() {
    data class Consult(val period: Int, val cost: Int)

    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val consults = Array(n) { br.readLine().split(" ").let { Consult(it[0].toInt(), it[1].toInt()) } }
    val dp = MutableList(n + 1) { 0 }
    var max = 0
    consults.forEachIndexed { index, (period, cost) ->
        max = maxOf(max, dp[index])
        val end = index + period
        if (end > n) return@forEachIndexed
        dp[end] = maxOf(dp[end], max + cost)
    }
    println(maxOf(max, dp.last()))
}
