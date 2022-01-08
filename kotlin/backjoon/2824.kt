fun main() {
    val br = System.`in`.bufferedReader()
    val INF = 1000000000
    val n = br.readLine().toInt()
    val aList = br.readLine().split(" ").map { it.toLong() }.toMutableList()
    val m = br.readLine().toInt()
    val bList = br.readLine().split(" ").map { it.toLong() }.toMutableList()

    var answer = 1L
    var flag = false
    repeat(n) { i ->
        repeat(m) { j ->
            val g = gcd(aList[i], bList[j])
            answer *= g
            aList[i] /= g
            bList[j] /= g
            if (answer >= INF) {
                answer %= INF
                flag = true
            }
        }
    }
    if (flag) println("$answer".padStart(9, '0')) else println(answer)
}

private fun gcd(a: Long, b: Long): Long {
    var tmp: Long
    var va = a
    var vb = b
    while (vb > 0) {
        tmp = va
        va = vb
        vb = tmp % vb
    }
    return va
}
