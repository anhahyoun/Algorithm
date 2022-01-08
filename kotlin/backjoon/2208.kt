fun main() {
    val br = System.`in`.bufferedReader()
    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    val pSum = MutableList(n + 1) { 0 }
    val jewel = MutableList(n + 1) {
        if (it == 0) {
            0
        } else {
            val num = br.readLine().toInt()
            pSum[it] = pSum[it - 1] + num
            num
        }
    }

    val dp = MutableList(n + 1) { 0 }
    dp[m] = pSum[m]
    (m + 1..n).forEach {
        dp[it] = maxOf(dp[it - 1] + jewel[it], pSum[it] - pSum[it - m])
    }
    val maxValue = dp.maxOrNull() ?: 0
    println(if (maxValue < 0) 0 else maxValue)
}
