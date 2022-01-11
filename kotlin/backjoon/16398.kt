import java.util.*

fun main() {
    class UnionFind(val parent: Array<Int>) {

        fun union(ux: Int, uy: Int) {
            if (ux > uy) parent[ux] = uy else parent[uy] = ux
        }

        fun find(x: Int): Int {
            if (parent[x] != x) parent[x] = find(parent[x])
            return parent[x]
        }
    }

    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val pq = PriorityQueue<Triple<Int, Int, Int>> { a, b -> a.first - b.first }
    val uf = UnionFind(Array(n) { it })

    repeat(n) { i ->
        val lst = br.readLine().split(" ").map { it.toInt() }
        for (j in i + 1 until n) {
            pq.add(Triple(lst[j], i, j))
        }
    }

    var answer = 0L
    while (pq.isNotEmpty()) {
        val (cost, x, y) = pq.poll()
        val ux = uf.find(x)
        val uy = uf.find(y)
        if (ux == uy) continue
        uf.union(ux, uy)
        answer += cost
    }
    println(answer)
}
