fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    var num = 0
    val output = mutableListOf<String>()
    val stack = mutableListOf<Int>()

    for (i in 0 until n) {
        val nowNum = br.readLine().toInt()
        while (num < nowNum) {
            stack.add(++num)
            output.add("+")
        }
        if (stack.last() == nowNum) {
            stack.removeLast()
            output.add("-")
        } else {
            output.clear()
            output.add("NO")
            break
        }
    }
    bw.write(output.joinToString("\n"))
    bw.close()
}
