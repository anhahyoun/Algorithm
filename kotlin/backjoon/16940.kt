import java.util.*

fun main() {
    val br = System.`in`.bufferedReader()
    val tree = mutableMapOf<Int, MutableList<Int>>()
    val n = br.readLine().toInt()
    repeat(n - 1) {
        val (a, b) = br.readLine().split(" ").map { it.toInt() }
        tree[a]?.add(b) ?: tree.put(a, mutableListOf(b))
        tree[b]?.add(a) ?: tree.put(b, mutableListOf(a))
    }

    val que: Queue<Int> = LinkedList()
    val visited = Array(n + 1) { false }
    que.add(1)
    visited[1] = true
    val result = br.readLine().split(" ").map { it.toInt() }
    val order = Array(n + 1) { 0 }
    result.forEachIndexed { index, i -> order[i] = index }
    val list = mutableListOf<Int>()
    while (que.isNotEmpty()) {
        val now = que.poll()
        list.add(now)
        tree[now]?.sortedBy { order[it] }?.forEach {
            if (visited[it]) return@forEach
            visited[it] = true
            que.add(it)
        }
    }

    if (list == result) println(1) else println(0)
}

/**
 * 정렬할 순서를 order에 저장
 * 1 3 2 4 가 주어졌을때
 * order[1] = 1
 * order[3] = 2 ...
 * 
 * bfs에서 자식을 탐색할 때 order에서 저장한 순서대로 정렬
 * 
 * 정렬순으로 탐색한 리스트와 다르다면 순서가 올바르지 않음
 * */
