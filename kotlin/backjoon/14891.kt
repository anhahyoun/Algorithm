fun main() {
    val br = System.`in`.bufferedReader()

    val wheels = mutableMapOf(1 to br.readLine().toMutableList(),
        2 to br.readLine().toMutableList(),
        3 to br.readLine().toMutableList(),
        4 to br.readLine().toMutableList())

    val num = br.readLine().toInt()

    fun rotate(way: Int, num: Int) {
        when (way) {
            1 -> wheels[num]!!.add(0, wheels[num]!!.removeLast())
            -1 -> wheels[num]!!.add(wheels[num]!!.removeFirst())
        }
    }

    repeat(num) {
        var (wheel, rotation) = br.readLine().split(" ").map { it.toInt() }
        val check = List(3) { wheels[it + 1]!![2] != wheels[it + 2]!![6] }
        rotate(rotation, wheel)
        when (wheel) {
            1 -> {
                for (i in 0..2) {
                    if (check[i]) {
                        rotation = -rotation
                        rotate(rotation, i + 2)
                    } else break
                }
            }
            2 -> {
                if (check[0]) rotate(-rotation, 1)
                for (i in 1..2) {
                    if (check[i]) {
                        rotation = -rotation
                        rotate(rotation, i + 2)
                    } else break
                }
            }
            3 -> {
                if (check[2]) rotate(-rotation, 4)
                for (i in 1 downTo 0) {
                    if (check[i]) {
                        rotation = -rotation
                        rotate(rotation, i + 1)
                    } else break
                }
            }
            4 -> {
                for (i in 2 downTo 0) {
                    if (check[i]) {
                        rotation = -rotation
                        rotate(rotation, i + 1)
                    } else break
                }
            }
        }
    }

    var answer = 0
    val score = mapOf(1 to 1, 2 to 2, 3 to 4, 4 to 8)
    wheels.forEach { (key, value) ->
        if (value[0] == '1') answer += score[key]!!
    }
    println(answer)
}
