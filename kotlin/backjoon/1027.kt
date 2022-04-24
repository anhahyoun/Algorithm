fun main() {
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val building = br.readLine().split(" ").map { it.toLong() }
    var max = 0

    for (i in 0 until n) {
        var leftM = Double.MAX_VALUE
        var rightM = -Double.MAX_VALUE
        var cnt = 0
        // 왼쪽
        for (j in i - 1 downTo 0) {
            val m = (building[i] - building[j]) / (i - j).toDouble()
            if (leftM > m) {
                leftM = m
                cnt++
            }
        }
        // 오른쪽
        for (j in i + 1 until n) {
            val m = (building[j] - building[i]) / (j - i).toDouble()
            if (rightM < m) {
                cnt++
                rightM = m
            }
        }
        max = maxOf(max, cnt)
    }
    println(max)
}
