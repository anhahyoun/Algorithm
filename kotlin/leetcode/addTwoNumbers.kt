/**
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */
class Solution {
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        val newList = add(l1, l2, 0, ListNode(0))
        return newList.next
    }
    
    fun add(l1next: ListNode?, l2next: ListNode?, remain:Int, newList: ListNode) : ListNode {
        if (l1next == null && l2next == null && remain == 0)  return newList
        val addNum = (l1next?.`val`?:0) + (l2next?.`val`?:0) + remain
        val re = addNum / 10
        val num = addNum % 10
        newList.next = ListNode(num)
        add(l1next?.next, l2next?.next, re, newList.next)
        
        return newList
    }
}
