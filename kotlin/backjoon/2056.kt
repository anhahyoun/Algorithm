import java.util.*

fun main() {
    solved1()
    solved2()
}

private fun solved1() {
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val spend = Array(n + 1) { 0 }

    for (i in 1..n) {
        val list = br.readLine().split(" ").map { it.toInt() }
        for (j in 2 until list.size) {
            val parent = list[j]
            spend[i] = maxOf(spend[i], spend[parent])
        }
        spend[i] += list[0]
    }

    println(spend.maxOrNull())
}

// 위상 정렬
private fun solved2() {
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val child = Array(n + 1) { mutableListOf<Int>() }
    val time = Array(n + 1) { 0 }
    val dp = Array(n + 1) { 0 }
    val indegree = Array(n + 1) { 0 }
    repeat(n) {
        val list = br.readLine().split(" ").map { it.toInt() }
        time[it + 1] = list[0]
        for (i in 2 until list.size) {
            child[list[i]].add(it + 1)
            indegree[it + 1]++
        }
    }
    val que: Queue<Int> = LinkedList()
    indegree.forEachIndexed { index, i ->
        if (index != 0 && i == 0) {
            que.add(index)
            dp[index] = time[index]
        }
    }

    while (que.isNotEmpty()) {
        val now = que.poll()
        child[now].forEach { next ->
            dp[next] = maxOf(dp[next], dp[now] + time[next])
            if (--indegree[next] == 0) que.add(next)
        }
    }
    println(dp.maxOrNull())
}
