class Solution {
    
    private fun ryanScore(ryan: IntArray, apeach: IntArray): Int {
        var apeachScore = 0
        var ryanScore = 0
        ryan.forEachIndexed { index, i ->
            if (apeach[index] >= i) {
                if (apeach[index] != 0) apeachScore += 10 - index
            } else {
                ryanScore += 10 - index
            }
        }
        return ryanScore - apeachScore
    }

    private val maxArray: (MutableList<Int>, IntArray) -> MutableList<Int> = { old, new ->
        val oldScore = old.reduceIndexed { index, acc, i -> acc + (index) * i }
        val newScore = new.reduceIndexed { index, acc, i -> acc + (index) * i }
        if (oldScore < newScore) new.toMutableList() else old.toMutableList()
    }


    fun solution(n: Int, info: IntArray): IntArray {
        val answer = IntArray(11) { 0 }
        var gap = Pair(0, MutableList(11) { 0 })

        fun fillArrow(arrow: Int, ryan: IntArray): IntArray {
            var remain = arrow
            for (i in 10 downTo 0) {
                if (remain == 0) break
                if (info[i] == 0) continue
                while (remain != 0 && ryan[i] < info[i]) {
                    ryan[i] += 1
                    remain -= 1
                }
            }
            return ryan
        }

        fun dfs(x: Int, sum: Int) {
            if (x == 11) {
                if (sum > n) return
                val score = if (sum < n) ryanScore(fillArrow(n - sum, answer), info) else ryanScore(answer, info)
                if (gap.first <= score) {
                    gap = if (score == gap.first) score to maxArray(gap.second,
                        answer) else score to answer.toMutableList()
                }
                return
            }
            answer[x] = info[x] + 1
            dfs(x + 1, sum + info[x] + 1)
            answer[x] = 0
            dfs(x + 1, sum)
        }
        dfs(0, 0)
        return if (gap.first == 0) intArrayOf(-1) else gap.second.toIntArray()
    }
}
