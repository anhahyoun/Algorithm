import java.util.*

fun main() {
    val br = System.`in`.bufferedReader()
    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    val lab = Array(n) { br.readLine().split(" ").map { it.toInt() }.toIntArray() }
    val dx = listOf(0, 1, 0, -1)
    val dy = listOf(1, 0, -1, 0)

    fun bfs(newLab: MutableList<Array<Int>>): Int {
        val que: Queue<Pair<Int, Int>> = LinkedList()

        for (i in newLab.indices) {
            for (j in 0 until m) {
                if (newLab[i][j] == 2) que.add(i to j)
            }
        }

        while (que.isNotEmpty()) {
            val (nowX, nowY) = que.poll()
            for (i in 0..3) {
                val nextX = nowX + dx[i]
                val nextY = nowY + dy[i]
                if (nextX !in 0 until n || nextY !in 0 until m) continue
                if (newLab[nextX][nextY] == 0) {
                    que.add(nextX to nextY)
                    newLab[nextX][nextY] = 2
                }
            }
        }

        return newLab.sumOf { it.count { int -> int == 0 } }
    }

    var max = 0

    fun wall(x: Int) {
        if (x == 3) {
            val newLab = mutableListOf<Array<Int>>()
            lab.forEach { newLab.add(it.toTypedArray()) }
            max = maxOf(bfs(newLab), max)
            return
        }

        for (i in 0 until n) {
            for (j in 0 until m) {
                if (lab[i][j] == 0) {
                    lab[i][j] = 1
                    wall(x + 1)
                    lab[i][j] = 0
                }
            }
        }
    }

    wall(0)
    println(max)
}
