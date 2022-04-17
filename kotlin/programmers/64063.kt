class Solution {
    fun solution(k: Long, room_number: LongArray): LongArray {
        val parent = mutableMapOf<Long, Long>()
        val room = LongArray(room_number.size) { 0L }
        
        fun find(x: Long) : Long {
            parent[x] = if (x !in parent) x + 1 else find(parent[x]!!)
            return parent[x]!!
        }
        
        room_number.forEachIndexed { index, num ->
            val roomNum = find(num)
            room[index] = roomNum - 1
        }
        
        return room
    }
}
