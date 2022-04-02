fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    repeat(br.readLine().toInt()) {
        val parent = mutableMapOf<String, String>()
        val networkSize = mutableMapOf<String, Int>()

        fun find(n: String): String {
            if (n == parent[n]) return n
            parent[n] = find(parent[n]!!)
            return parent[n]!!
        }

        fun union(x: String, y: String) {
            val xx = find(x)
            val yy = find(y)
            if (xx != yy) {
                parent[yy] = xx
                networkSize[xx] = (networkSize[xx] ?: 0) + (networkSize[yy] ?: 0)
            }
        }

        repeat(br.readLine().toInt()) {
            val (f1, f2) = br.readLine().split(" ")
            if (f1 !in parent) {
                parent[f1] = f1
                networkSize[f1] = 1
            }
            if (f2 !in parent) {
                parent[f2] = f2
                networkSize[f2] = 1
            }
            union(f1, f2)
            bw.write("${networkSize[find(f1)]}\n")
        }
    }
    bw.close()
}
