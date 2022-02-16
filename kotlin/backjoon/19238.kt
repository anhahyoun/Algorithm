import java.util.*

fun main() {
    data class Point(val x: Int, val y: Int)

    val br = System.`in`.bufferedReader()
    val dx = listOf(0, 1, 0, -1)
    val dy = listOf(1, 0, -1, 0)
    var (n, m, fuel) = br.readLine().split(" ").map { it.toInt() }
    val area = Array(n) { br.readLine().split(" ").map { it.toInt() } }
    var start = br.readLine().split(" ").run { Point(get(0).toInt() - 1, get(1).toInt() - 1) }
    val passengers = mutableMapOf<Point, Point>()
    repeat(m) {
        br.readLine().split(" ").map { it.toInt() }
            .run { passengers[Point(get(0) - 1, get(1) - 1)] = Point(get(2) - 1, get(3) - 1) }
    }

    fun getPassenger(startPoint: Point): Pair<Point?, Int?> {
        if (startPoint in passengers) return startPoint to 0
        val queue: Queue<Pair<Point, Int>> = LinkedList()
        val visited = Array(n) { Array(n) { 0 } }
        visited[startPoint.x][startPoint.y] = 1
        queue.add(startPoint to 0)
        val minDisPassengers = PriorityQueue<Pair<Point, Int>> { new, old ->
            if (new.second == old.second) {
                if (new.first.x == old.first.x) new.first.y - old.first.y else new.first.x - old.first.x
            } else {
                new.second - old.second
            }
        }
        while (queue.isNotEmpty()) {
            val (now, dis) = queue.poll()
            for (i in 0..3) {
                val next = Point(now.x + dx[i], now.y + dy[i])
                if (next.x !in 0 until n || next.y !in 0 until n || area[next.x][next.y] == 1) continue
                if (visited[next.x][next.y] == 1) continue
                if (next in passengers) minDisPassengers.add(next to dis + 1)
                queue.add(next to dis + 1)
                visited[next.x][next.y] = 1
            }
        }
        return if (minDisPassengers.isEmpty()) null to null else minDisPassengers.peek()
    }

    fun toEndPoint(startPoint: Point): Int? {
        val queue: Queue<Pair<Point, Int>> = LinkedList()
        val endPoint = passengers[startPoint]
        val visited = Array(n) { Array(n) { 0 } }
        queue.add(startPoint to 0)
        while (queue.isNotEmpty()) {
            val (now, dis) = queue.poll()
            for (i in 0..3) {
                val next = Point(now.x + dx[i], now.y + dy[i])
                if (next.x !in 0 until n || next.y !in 0 until n || area[next.x][next.y] == 1) continue
                if (visited[next.x][next.y] == 1) continue
                if (next == endPoint) return dis + 1
                queue.add(next to dis + 1)
                visited[next.x][next.y] = 1
            }
        }
        return null
    }

    var flag = true
    while (passengers.isNotEmpty()) {
        val (passenger, dist) = getPassenger(start)
        if (passenger == null || dist == null || fuel - dist < 0) {
            flag = false
            break
        }
        fuel -= dist
        val consume = toEndPoint(passenger)
        if (consume == null || fuel - consume < 0) {
            flag = false
            break
        }
        fuel -= consume
        fuel += consume * 2
        start = passengers[passenger]!!
        passengers.remove(passenger)
    }

    if (flag) println(fuel) else println(-1)
}
