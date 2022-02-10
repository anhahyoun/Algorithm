fun main() {
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val prevMax = MutableList(3) { 0 }
    val prevMin = MutableList(3) { 0 }
    val dy = listOf(-1, 0, 1)
    repeat(n) {
        val nowMax = MutableList(3) { 0 }
        val nowMin = MutableList(3) { 987654321 }
        val now = br.readLine().split(" ").map { it.toInt() }
        now.forEachIndexed { index, i ->
            dy.forEach {
                if (index+it !in 0..2) return@forEach
                nowMax[index] = maxOf(nowMax[index], prevMax[index+it] + i)
                nowMin[index] = minOf(nowMin[index], prevMin[index+it] + i)
            }
        }
        prevMax.clear()
        prevMax.addAll(nowMax)
        prevMin.clear()
        prevMin.addAll(nowMin)
    }
    println("${prevMax.maxOrNull()} ${prevMin.minOrNull()}")
}
