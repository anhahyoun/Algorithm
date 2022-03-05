fun main() {
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val line = List(n) { br.readLine().split(" ").map { it.toInt() } }.sortedWith(compareBy { it[0] })

    var start = line.first()[0]
    var end = line.first()[1]
    var num = end - start
    line.subList(1, n).forEach { (x, y) ->
        if (end in x..y) {
            num += y - end
            end = y
        } else if (end < x) {
            num += y - x
            start = x
            end = y
        }
    }
    println(num)
}
