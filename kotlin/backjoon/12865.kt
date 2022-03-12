fun main() {
    data class Bag(val w: Int, val v: Int)

    val br = System.`in`.bufferedReader()
    val (n, k) = br.readLine().split(" ").map { it.toInt() }
    val bags = Array(n + 1) {
        if (it == 0) Bag(0, 0) else br.readLine().split(" ").run { Bag(get(0).toInt(), get(1).toInt()) }
    }

    val dp = Array(k + 1) { 0 }
    bags.forEach { (w, v) ->
        for (i in k downTo 0) {
            if (i - w < 0) continue
            dp[i] = maxOf(dp[i], dp[i - w] + v)
        }
    }
    println(dp.last())
}

/*
 * 배낭 문제 (냅색 알고리즘)
 * 물품이 무제한 -> 0부터 시작
 * 물품이 1개 -> k부터 시작
 * */
