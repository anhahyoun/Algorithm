fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val n = br.readLine().toInt()
    var bit = 0

    repeat(n) {
        val input = br.readLine().split(" ")
        val cmd = input[0]
        val num = if (input.size > 1) input[1].toInt() else 0
        when {
            cmd.startsWith("add") -> {
                /*
                * 111010
                * 000100
                * 111110
                * */
                bit = bit or (1 shl num)
            }
            cmd.startsWith("check") -> {
                /*
                * 111010
                * 000100
                * 000000 -> 없음
                * */
                if (bit and (1 shl num) == 0) bw.write("0\n") else bw.write("1\n")
            }
            cmd.startsWith("remove") -> {
                /*
                * 111010
                * 111101
                * 111000
                * */
                bit = bit and (1 shl num).inv()
            }
            cmd.startsWith("toggle") -> {
                /*
                * bit  =        111010
                * ~bit =   11111000101
                * (1 shl num) = 000100
                *          11111000001
                *               111110
                * */
                bit = (bit.inv() xor (1 shl num)).inv()
            }
            cmd.startsWith("all") -> bit = (1 shl 21) - 1
            cmd.startsWith("empty") -> bit = 0
        }
    }
    bw.close()
}
