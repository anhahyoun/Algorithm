class Solution {
    fun solution(board: Array<IntArray>, skill: Array<IntArray>): Int {
        var answer: Int = 0
        val imos = Array(board.size + 1) { Array(board[0].size + 1) { 0 } }

        skill.forEach { 
            val (type, r1, c1, r2, c2) = it
            val degree = it.last()
            imos[r1][c1] += if (type == 1) -degree else degree
            imos[r1][c2 + 1] += if (type == 1) degree else -degree
            imos[r2 + 1][c1] += if (type == 1) degree else -degree
            imos[r2 + 1][c2 + 1] += if (type == 1) -degree else degree
        }

        for (i in 0 .. board.size) {
            for (j in 1 .. board[0].size) {
                imos[i][j] += imos[i][j - 1]
            }
        }

        for (i in 1 .. board.size) {
            for (j in 0 .. board[0].size) {
                imos[i][j] += imos[i - 1][j]
            }
        }

        for (i in 0 until board.size) {
            for (j in 0 until board[0].size) {
                if (board[i][j] + imos[i][j] > 0) answer++
            }
        }

        return answer
    }
}
