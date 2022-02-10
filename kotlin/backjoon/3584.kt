fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    var n: Int; var parent: Array<Int>

    repeat(br.readLine().toInt()) {
        n = br.readLine().toInt()
        parent = Array(n + 1) { 0 }
        repeat(n - 1) {
            val (p, c) = br.readLine().split(" ").map { it.toInt() }
            parent[c] = p
        }
        val (node1, node2) = br.readLine().split(" ").map { it.toInt() }
        val visited = Array(n + 1) { 0 }
        findRoot(node1, parent, visited)
        bw.write("${findParent(node2, parent, visited)}\n")
    }
    bw.close()
}

private fun findRoot(child: Int, parent: Array<Int>, visited: Array<Int>) {
    visited[child] = 1
    if (parent[child] == 0) return
    findRoot(parent[child], parent, visited)
}

private fun findParent(child: Int, parent: Array<Int>, visited: Array<Int>): Int {
    if (parent[child] == 0 || visited[child] == 1) return child
    return findParent(parent[child], parent, visited)
}
