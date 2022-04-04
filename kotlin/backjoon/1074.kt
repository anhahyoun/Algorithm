import kotlin.math.pow
import kotlin.system.exitProcess

fun main() {
    val br = System.`in`.bufferedReader()
    val (n, r, c) = br.readLine().split(" ").map { it.toInt() }
    var cnt = 0

    fun re(n: Int, x: Int, y: Int) {
        if (x == r && y == c) {
            println(cnt)
            exitProcess(0)
        }
        if (x <= r && r < x + n && y <= c && c < y + n) {
            val nn = n / 2
            re(nn, x, y)
            re(nn, x, y + nn)
            re(nn, x + nn, y)
            re(nn, x + nn, y + nn)
        } else {
            cnt += n * n
        }
    }

    re(2.0.pow(n).toInt(), 0,0)
}
