fun main() {
    val br = System.`in`.bufferedReader()
    val (r, c, k) = br.readLine().split(" ").map { it.toInt() }
    var array = List(3) { br.readLine().split(" ").map { it.toInt() } }

    for (it in 0..101) {
        if (it == 101) {
            println(-1)
            break
        }
        if (r - 1 < array.size && c - 1 < array[0].size && array[r - 1][c - 1] == k) {
            println(it)
            break
        }
        array = if (array[0].size <= array.size) sortR(array) else sortC(array)
    }
}

private fun sortR(array: List<List<Int>>) = sortList(array)

private fun sortC(array: List<List<Int>>): List<List<Int>> {
    val sorted = sortList(getSymmetricMatrix(array))
    return getSymmetricMatrix(sorted)
}

private fun sortList(array: List<List<Int>>): List<MutableList<Int>> {
    var maxSize = 0
    val sortedArr = array.map { arr ->
        val map = mutableMapOf<Int, Int>()
        arr.forEach { num -> if (num != 0) map[num] = (map[num] ?: 0) + 1 }
        val newArray = mutableListOf<Int>()
        map.toList().sortedWith(compareBy({ it.second }, { it.first }))
            .forEach { (t, u) -> newArray.addAll(listOf(t, u)) }
        maxSize = maxOf(maxSize, newArray.size)
        newArray
    }

    sortedArr.map {
        if (it.size < maxSize) while (it.size < maxSize) it.add(0)
    }
    return sortedArr
}

private fun getSymmetricMatrix(array: List<List<Int>>): List<List<Int>> {
    val symmetricMatrix = MutableList(array[0].size) { mutableListOf<Int>() }
    for (j in array[0].indices) {
        for (i in array.indices) {
            symmetricMatrix[j].add(array[i][j])
        }
    }
    return symmetricMatrix
}
