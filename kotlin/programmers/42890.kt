fun solution(relation: Array<Array<String>>): Int {
        var cnt = 0
        val uniqueness = mutableListOf<List<Int>>()
        val combinations =  getCombination(relation[0].size)
        combinations.forEach {
            if(checkUnique(it, relation)) uniqueness.add(it)
        }
        uniqueness.sortByDescending { it.size }

        for (i in uniqueness.indices) {
            var flag = true
            for (j in i + 1 until uniqueness.size) {
                if ((uniqueness[i].toSet() intersect uniqueness[j].toSet()).size == uniqueness[j].size) {
                    flag = false
                    break
                }
            }
            if (flag) cnt++
        }

        return cnt
    }

    private fun getCombination(n: Int) : List<List<Int>> {
        val combinations = mutableListOf<List<Int>>()
        for (i in 1 until (1 shl n)) {
            val com = mutableListOf<Int>()
            for (j in 0 until n) {
                if (i and (1 shl j) != 0 ) com.add(j)
            }
            combinations.add(com)
        }
        return combinations
    }

    private fun checkUnique(key: List<Int>, relation: Array<Array<String>>): Boolean {
        val new = relation.map { re -> List(key.size) { re[key[it]] } }.toSet()
        return new.size == relation.size
    }
