fun main() {
    val br = System.`in`.bufferedReader()
    val (n, h) = br.readLine().split(" ").map { it.toInt() }
    val top = Array(h + 1) { 0 }
    val bottom = Array(h + 1) { 0 }

    repeat(n) {
        val num = br.readLine().toInt()
        if (it % 2 == 0) bottom[num]++ else top[num]++
    }

    for (i in 1..h) {
        top[i] += top[i - 1]
        bottom[i] += bottom[i - 1]
    }

    val map = mutableMapOf<Int, Int>()

    for (i in 1..h) {
        val destroy = bottom[h] - bottom[i - 1] + top[h] - top[h - i]
        map[destroy] = (map[destroy] ?: 0) + 1
    }

    val minKey = map.minOf { it.key }
    println("$minKey ${map[minKey]}")

    // 메모리 초과
//    val imos = Array(h + 1) { Array(n + 1) { 0 } }
//    repeat(n) {
//        val num = br.readLine().toInt()
//        val i1 = if (it % 2 == 1) 0 else h - num
//        val i2 = if (it % 2 == 1) num else h
//
//        imos[i1][it] += 1
//        imos[i1][it + 1] -= 1
//        imos[i2][it] -= 1
//        imos[i2][it + 1] += 1
//    }
//
//    for (i in 0..h) {
//        for (j in 1..n) {
//            imos[i][j] += imos[i][j - 1]
//        }
//    }
//
//    for (i in 1..h) {
//        for (j in 0..n) {
//            imos[i][j] += imos[i - 1][j]
//        }
//    }
//
//    val map = mutableMapOf<Int, Int>()
//    for (i in 0 until h) {
//        val array = imos[i]
//        val destroy = array.count { num -> num == 1 }
//        map[destroy] = (map[destroy] ?: 0) + 1
//    }
//
//    val minKey = map.minOf { it.key }
//    println("$minKey ${map[minKey]}")
}
