fun main() {
    val br = System.`in`.bufferedReader()
    br.readLine().toInt()
    val weights = br.readLine().split(" ").map { it.toInt() }.sorted()
    var num = 1
    for (weight in weights) {
        if (num < weight) break
        num += weight
    }
    println(num)
}
