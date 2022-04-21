fun main() {
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val people = br.readLine().split(" ").map { it.toInt() }.toMutableList()
    val num = br.readLine().toInt()

    for (i in 1 until n) {
        people[i] += people[i - 1]
    }

    people.add(0, 0)
    val dp = Array(4) { Array(n + 1) { 0 } }

    for (i in 1..3) {
        for (j in num..n) {
            dp[i][j] = maxOf(dp[i][j - 1], dp[i - 1][j - num] + people[j] - people[j - num])
        }
    }
    println(dp.last().last())
}
