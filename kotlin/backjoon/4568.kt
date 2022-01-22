import java.util.*

fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    class LRUCache {
        private val list = ArrayList<Char>()
        private val hit = mutableMapOf<Char, Int>()
        private var size = 0

        init {
            ('A'..'Z').forEach { hit[it] = 0 }
        }

        fun clear() {
            list.clear()
            hit.keys.forEach { hit[it] = 0 }
        }

        fun setMaxSize(num: Int) {
            size = num
        }

        fun add(c: Char, num: Int) {
            hit[c] = num
            if (list.size == size) {
                if (c !in list) {
                    removeMin()
                    list.add(c)
                } else {
                    list.remove(c)
                    list.add(c)
                }
            } else {
                if (c !in list) {
                    list.add(c)
                } else {
                    list.remove(c)
                    list.add(c)
                }
            }
        }

        private fun removeMin() {
            val key = hit.filter { it.value != 0 }.minByOrNull { it.value }!!.key
            list.remove(key)
            hit[key] = 0
        }

        fun print() {
            bw.write("${list.joinToString("")}\n")
        }
    }

    val cache = LRUCache()
    var cnt = 0
    while (true) {
        val cmd = br.readLine().split(" ")
        if (cmd[0] == "0") break
        bw.write("Simulation ${++cnt}\n")
        cache.clear()
        cache.setMaxSize(cmd[0].toInt())
        cmd[1].forEachIndexed { index, c ->
            if (c == '!') {
                cache.print()
            } else {
                cache.add(c, index + 1)
            }
        }
    }

    bw.close()
}
