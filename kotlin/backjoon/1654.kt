fun main() {
    val br = System.`in`.bufferedReader()
    val (k, n) = br.readLine().split(" ").map { it.toInt() }
    val list = Array(k) { br.readLine().toInt() }

    val count :(Long) -> Long = { mid -> list.sumOf { it/mid } }
    var right = list.maxOrNull()?.toLong() ?: 0L
    var left = 1L
    var answer = 0L
    while (left <= right) {
        val mid = (left + right) / 2
        if (count(mid) < n) {
            right = mid - 1
        } else {
            answer = mid
            left = mid + 1
        }
    }
    println(answer)
}
