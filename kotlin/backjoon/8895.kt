fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val dp = Array(21) { Array(21) { Array(21) { 0L } } }

    dp[1][1][1] = 1
    for (n in 2..20) {
        for (left in 1..20) {
            for (right in 1..20) {
                dp[n][left][right] =
                    dp[n - 1][left - 1][right] + dp[n - 1][left][right - 1] + (n - 2) * dp[n - 1][left][right]
            }
        }
    }
    repeat(br.readLine().toInt()) {
        val (n, l, r) = br.readLine().split(" ").map { it.toInt() }
        bw.write("${dp[n][l][r]}\n")
    }
    bw.close()
}
