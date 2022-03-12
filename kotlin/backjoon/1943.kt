fun main() {
    data class Coin(val money: Int, var count: Int)

    val br = System.`in`.bufferedReader()
    repeat(3) {
        val n = br.readLine().toInt()
        var total = 0
        val coin = Array(n) {
            br.readLine().split(" ").run {
                total += get(0).toInt() * get(1).toInt()
                Coin(get(0).toInt(), get(1).toInt())
            }
        }.sortedByDescending { it.money }

        val dp = Array(total / 2 + 1) { 0 }
        coin.forEach {
            for (i in 0..it.count) {
                if (it.money * i < total / 2) dp[it.money * i] = 1
            }
        }

        if (total % 2 == 1) {
            println(0)
            return@repeat
        }
        coin.forEach {
            for (i in total / 2 downTo 0) {
                if (i - it.money < 0) continue
                if (dp[i - it.money] == 0) continue
                for (j in 1..it.count) {
                    val m = i - it.money + it.money * j
                    if (m > total / 2) break
                    dp[m] = 1
                }
            }
        }
        println(dp[total / 2])
    }
}
