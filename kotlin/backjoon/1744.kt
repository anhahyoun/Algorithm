fun main() {
    val br = System.`in`.bufferedReader()

    val n = br.readLine().toInt()
    val arr = Array(n) { br.readLine().toInt() }.sortedArrayDescending()
    var answer = 0
    var left = 0
    var right = n - 1

    while (left < n - 1 && arr[left + 1] > 0) {
        answer += if (arr[left] == 1 || arr[left + 1] == 1) {
            arr[left] + arr[left + 1]
        } else if (arr[left + 1] == 0) {
            arr[left]
        } else {
            arr[left] * arr[left + 1]
        }
        left += 2
    }

    while (right > left && arr[right] <= 0) {
        answer += if (arr[right - 1] <= 0) {
            arr[right] * arr[right - 1]
        } else {
            arr[right] + arr[right - 1]
        }
        right -= 2
    }

    if (left == right) answer += arr[left]

    println(answer)
}
