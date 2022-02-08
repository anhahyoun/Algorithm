fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val (r, c, q) = br.readLine().split(" ").map { it.toInt() }
    val pSum = Array(r + 1) { Array(c + 1) { 0 } }
    repeat(r) { i ->
        br.readLine().split(" ")
            .forEachIndexed { index, s -> pSum[i + 1][index + 1] = pSum[i + 1][index] + s.toInt() }
        (1..c).forEach { j -> pSum[i+1][j] += pSum[i][j] }
    }
    val part = Array(q) {
        br.readLine().split(" ").map { it.toInt() }
    }
    part.forEach { (r1, c1, r2, c2) ->
        val sum = pSum[r2][c2] - pSum[r1 - 1][c2] - pSum[r2][c1 - 1] + pSum[r1 - 1][c1 - 1]
        bw.write("${sum / ((r2 - r1 + 1) * (c2 - c1 + 1))}\n")
    }
    bw.close()
}
