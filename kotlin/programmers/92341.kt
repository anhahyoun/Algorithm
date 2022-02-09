package programmers

import java.util.*

fun main() {
    val solution = Solution()
    solution.solution(intArrayOf(180, 5000, 10, 600),
        arrayOf("05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"))
    solution.solution(intArrayOf(120, 0, 60, 591),
        arrayOf("16:00 3961 IN", "16:00 0202 IN", "18:00 3961 OUT", "18:00 0202 OUT", "23:58 3961 IN"))
    solution.solution(intArrayOf(1, 461, 1, 10), arrayOf("00:00 1234 IN"))
}

class Solution {
    fun solution(fees: IntArray, records: Array<String>): IntArray {
        data class Time(val hour: Int, val minute: Int)

        val inRecord = mutableMapOf<String, Time>()
        val sumTime = TreeMap<Int, Int>()
        records.forEach { record ->
            val (time, number, inout) = record.split(" ")
            if (inout == "IN") {
                inRecord[number] = time.split(":").run { Time(get(0).toInt(), get(1).toInt()) }
                return@forEach
            }
            val (outHH, outMM) = time.split(":").map { it.toInt() }
            val totalTime = (outHH - inRecord[number]!!.hour) * 60 + outMM - inRecord[number]!!.minute
            sumTime[number.toInt()] = (sumTime[number.toInt()] ?: 0) + totalTime
            inRecord.remove(number)
        }
        inRecord.forEach { (number, time) ->
            val totalTime = (23 - time.hour) * 60 + 59 - time.minute
            sumTime[number.toInt()] = (sumTime[number.toInt()] ?: 0) + totalTime
        }
        return sumTime.values.map { calculateFee(it, fees) }.toIntArray()
    }

    private fun calculateFee(time: Int, fees: IntArray): Int {
        if (time <= fees[0]) return fees[1]
        val plus = if ((time - fees[0]) % fees[2] == 0) fees[1] else fees[1] + fees[3]
        return ((time - fees[0]) / fees[2]) * fees[3] + plus
    }
}
