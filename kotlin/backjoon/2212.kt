import java.util.*

fun main() {
    solved1()
    solved2()
}

fun solved1() {
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val k = br.readLine().toInt()
    val sensor = br.readLine().split(" ").map { it.toInt() }.sorted()
    val pq = PriorityQueue<Int> { a, b -> b - a }
    for (i in 1 until n) {
        if (sensor[i] - sensor[i - 1] != 0) pq.add(sensor[i] - sensor[i - 1])
    }
    repeat(k - 1) { pq.poll() }
    println(pq.sum())
}

fun solved2() {
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val k = br.readLine().toInt()
    val sensor = br.readLine().split(" ").map { it.toInt() }.sorted()
    val temp = mutableListOf<Int>()
    for (i in 1 until n) {
        if (sensor[i] - sensor[i - 1] != 0) temp.add(sensor[i] - sensor[i - 1])
    }
    temp.sort()
    var ans = 0
    for (i in 0..temp.size - k) ans += temp[i]
    println(ans)
}
