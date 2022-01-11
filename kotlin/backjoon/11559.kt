fun main() {
    val br = System.`in`.bufferedReader()

    val puyo = MutableList(12) { br.readLine().toCharArray() }
    val color = listOf('R', 'G', 'B', 'P', 'Y')
    val dx = listOf(1, 0, -1, 0)
    val dy = listOf(0, 1, 0, -1)
    var ans = 0

    fun dfs(color: Char): Int {
        val stack = arrayListOf<Pair<Int, Int>>()
        val visited = Array(12) { Array(6) { 0 } }
        val pop = mutableListOf<Pair<Int, Int>>()
        var flag = 0
        for (i in 0..11) {
            for (j in 0..5) {
                if (!(stack.isEmpty() && visited[i][j] == 0 && puyo[i][j] == color)) continue
                stack.add(i to j)
                visited[i][j] = 1
                pop.add(i to j)
                while (stack.isNotEmpty()) {
                    val (nowX, nowY) = stack.removeLast()
                    for (d in 0..3) {
                        val nextX = nowX + dx[d]
                        val nextY = nowY + dy[d]
                        if (nextX !in 0..11 || nextY !in 0..5) continue
                        if (puyo[nextX][nextY] == color && visited[nextX][nextY] == 0) {
                            stack.add(nextX to nextY)
                            visited[nextX][nextY] = 1
                            pop.add(nextX to nextY)
                        }
                    }
                }

                if (pop.size > 3) {
                    pop.forEach {
                        puyo[it.first][it.second] = '.'
                    }
                    flag = 1
                }
                pop.clear()
            }
        }
        return flag
    }

    fun down() {
        for (i in 10 downTo 0) {
            for (j in 0..5) {
                if (puyo[i][j] == '.') continue
                if (puyo[i + 1][j] != '.') continue
                var nowX = i
                while (nowX + 1 < 12 && puyo[nowX + 1][j] == '.') nowX++
                puyo[nowX][j] = puyo[i][j]
                puyo[i][j] = '.'
            }
        }
    }

    while (true) {
        var f = 0
        color.forEach {
            f = f or dfs(it)
        }
        if (f == 0) break
        ans++
        down()
    }
    println(ans)
}
