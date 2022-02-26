fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val equation = br.readLine().replace("ZERO", "0").replace("ONE", "1").replace("TWO", "2")
        .replace("THREE", "3").replace("FOUR", "4").replace("FIVE", "5").replace("SIX", "6")
        .replace("SEVEN", "7").replace("EIGHT", "8").replace("NINE", "9")
    val stringNum = mapOf('0' to "ZERO", '1' to "ONE", '2' to "TWO", '3' to "THREE", '4' to "FOUR",
        '5' to "FIVE", '6' to "SIX", '7' to "SEVEN", '8' to "EIGHT", '9' to "NINE")
    var num = ""
    var op = ""
    val calNum = mutableListOf<Long>()
    var answer = ""
    var flag = true

    for (it in equation) {
        if (!it.isDigit()) {
            if (it !in setOf('+', '-', '/', 'x', '=') || num == "") {
                flag = false
                bw.write("Madness!")
                break
            }
            if (calNum.size == 1) {
                answer = "$answer$op$num"
                val result = when (op) {
                    "+" -> calNum[0] + num.toLong()
                    "-" -> calNum[0] - num.toLong()
                    "x" -> calNum[0] * num.toLong()
                    else -> calNum[0] / num.toLong()
                }
                op = it.toString()
                calNum.clear()
                calNum.add(result)
                num = ""
            } else {
                calNum.add(num.toLong())
                answer = "$answer$num"
                num = ""
                op = it.toString()
            }
        } else {
            num = "$num$it"
        }
    }
    if (flag) {
        bw.write("$answer=\n")
        calNum[0].toString().forEach {
            bw.write("${stringNum[it] ?: it}")
        }
    }
    bw.close()
}
