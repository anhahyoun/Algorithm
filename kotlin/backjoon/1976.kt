fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    val m = br.readLine().toInt()
    val city = Array(n) { br.readLine().split(" ").map { it.toInt() }.toIntArray() }
    val plan = br.readLine().split(" ").map { it.toInt() - 1 }
    for (i in 0 until n) {
        city[i][i] = 1
    }
    for (k in 0 until n) {
        for (i in 0 until n) {
            for (j in 0 until n) {
                if (city[i][k] == 1 && city[k][j] == 1) {
                    city[i][j] = 1
                }
            }
        }
    }

    bw.write(checkPlan(m, plan, city))
    bw.close()
}

fun checkPlan(m: Int, plan: List<Int>, city: Array<IntArray>): String {
    for (i in 1 until m) {
        if (city[plan[i - 1]][plan[i]] == 0) return "NO"
    }
    return "YES"
}
