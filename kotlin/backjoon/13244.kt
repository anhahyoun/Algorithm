fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val t = br.readLine().toInt()

    repeat(t) {
        val n = br.readLine().toInt()
        val m = br.readLine().toInt()
        val unionFind = UnionFind(Array(n + 1) { it })
        if (n - m != 1) {
            bw.write("graph\n")
            repeat(m) { br.readLine() }
            return@repeat
        }
        var flag = true
        for (j in 0 until m) {
            val (x, y) = br.readLine().split(" ").map { it.toInt() }
            if (!flag) continue
            val u = unionFind.union(x, y)
            if (u == State.Graph.type) {
                bw.write("$u\n")
                flag = false
            }
        }
        if (flag) {
            bw.write("${State.Tree.type}\n")
        }
    }

    bw.close()
}

class UnionFind(private val parent: Array<Int>) {

    fun union(x: Int, y: Int): String {
        val ux = find(x)
        val uy = find(y)
        if (ux == uy) {
            return State.Graph.type
        }
        if (ux < uy) parent[uy] = ux else parent[ux] = uy
        return State.Tree.type
    }

    private fun find(x: Int): Int {
        return if (x == parent[x]) {
            x
        } else {
            parent[x] = find(parent[x])
            parent[x]
        }
    }
}

enum class State(val type: String) {
    Tree("tree"), Graph("graph")
}
