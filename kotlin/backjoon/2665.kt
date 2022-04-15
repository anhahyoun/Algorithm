import java.util.*

fun main() {
    val br = System.`in`.bufferedReader()

    val n = br.readLine().toInt()
    val room = Array(n) { br.readLine().toList().map { it.toString().toInt() } }

    val black = Array(n) { Array(n) { Int.MAX_VALUE } }
    val que: Queue<Pair<Int, Int>> = LinkedList()
    val dx = listOf(0, 1, -1, 0)
    val dy = listOf(1, 0, 0, -1)

    que.add(0 to 0)
    black[0][0] = 0

    while (que.isNotEmpty()) {
        val (nowX, nowY) = que.poll()
        for (i in 0..3) {
            val nextX = nowX + dx[i]
            val nextY = nowY + dy[i]

            if (nextX !in 0 until n || nextY !in 0 until n || black[nextX][nextY] == 0) continue

            if (room[nextX][nextY] == 0) {
                if (black[nowX][nowY] == 0) {
                    black[nextX][nextY] = black[nowX][nowY] + 1
                    que.add(nextX to nextY)
                } else if (black[nowX][nowY] + 1 < black[nextX][nextY]) {
                    black[nextX][nextY] = black[nowX][nowY] + 1
                    que.add(nextX to nextY)
                }
            } else {
                if (black[nextX][nextY] > black[nowX][nowY]) {
                    black[nextX][nextY] = black[nowX][nowY]
                    que.add(nextX to nextY)
                }
            }
        }
    }

    println(black.last().last())
}
