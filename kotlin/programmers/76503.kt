import kotlin.math.abs

class Solution {
    fun solution(a: IntArray, edges: Array<IntArray>): Long {
        var answer: Long = 0
        val visited = Array(a.size) { 0 }
        val graph = mutableMapOf<Int, MutableList<Int>>()
        val a = a.map { it.toLong() }.toMutableList()
        edges.forEach {
            val a = it[0]
            val b = it[1]
            graph[a]?.add(b) ?: graph.put(a, mutableListOf(b))
            graph[b]?.add(a) ?: graph.put(b, mutableListOf(a))
        }

        fun dfs(x: Int) {
            graph[x] ?: return
            for (i in 0 until graph[x]!!.size) {
                val it = graph[x]!!.get(i)
                if (visited[it] == 1) continue
                visited[it] = 1
                dfs(it)
                answer += abs(a[it])
                a[x] += a[it]
            }
        }
        visited[0] = 1
        dfs(0)
        
        return  if (a[0] == 0L) answer else -1
    }
}

/**
* 6, 7, 8 런타임 에러, 어떻게 풀어야 할지..?
*
*
*/
