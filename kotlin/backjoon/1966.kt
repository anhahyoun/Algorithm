import java.util.*

fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    repeat(br.readLine().toInt()) {
        val (n, m) = br.readLine().split(" ").map { it.toInt() }
        val queue: Queue<Pair<Int, Int>> =
            LinkedList(br.readLine().split(" ").mapIndexed { index, s -> index to s.toInt() })

        var cnt = 0
        while (queue.isNotEmpty()) {
            val (index, priority) = queue.poll()
            if (queue.find { it.second > priority } != null) {
                queue.add(index to priority)
            } else if (index == m) {
                bw.write("${++cnt}\n")
                break
            } else {
                cnt++
            }
        }
    }
    bw.close()
}
