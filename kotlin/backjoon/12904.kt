fun main() {
    val br = System.`in`.bufferedReader()
    val s = br.readLine().trim().toMutableList()
    var t = br.readLine().trim().toMutableList()

    while (t.size >= s.size) {
        if (t == s) break
        if (t.removeAt(t.lastIndex) == 'B') t = t.reversed().toMutableList()
    }
    println(if (t == s) 1 else 0)
}
