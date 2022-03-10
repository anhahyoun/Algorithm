fun main() {
    val br = System.`in`.bufferedReader()

    val array = List(br.readLine().toInt()) { Pair(br.readLine().toInt(), it + 1) }.sortedBy { it.first }
    println(array.mapIndexed { index, pair -> pair.second - index }.maxOrNull())

    /**
     * 문제 의도
     * 버블 소트가 몇번 진행 됐는가?
     * 자신의 인덱스에서 한번 앞으로 가면 버블소트 한번 진행
     * 인덱스를 같이 저장하고 소트
     * 현재 인덱스가 1인데 이전 인덱스가 5라면
     * 5-1 = 4번 버블 소트가 진행 됐음
     * 모든 값 중에 최대값으로 버블 소트가 진행됨
     * **/
}
