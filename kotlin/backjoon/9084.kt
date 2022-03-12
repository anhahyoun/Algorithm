fun main() {
    val br = System.`in`.bufferedReader()
    repeat(br.readLine().toInt()) {
        val n = br.readLine().toInt()
        val coins = br.readLine().split(" ").map { it.toInt() }
        val m = br.readLine().toInt()
        val dp = Array(m + 1) { 0 }
        dp[0] = 1
        coins.forEach {
            for (j in 1..m) {
                if (j - it < 0) continue
                dp[j] += dp[j - it]
            }
        }
        println(dp.last())
    }
}
