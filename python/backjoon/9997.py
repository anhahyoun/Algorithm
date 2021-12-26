import sys
from functools import reduce


def dfs(x, bit):
    global num
    if bit == allAlpha:
        num += 2 ** (n - x)
        return
    if x == n: return
    dfs(x + 1, bit | words[x])
    dfs(x + 1, bit)


if __name__ == '__main__':
    n = int(sys.stdin.readline())
    num = 0
    allAlpha = (1 << 26) - 1
    words = [0 for _ in range(n)]
    for i in range(n):
        for w in sys.stdin.readline().strip():
            words[i] |= 1 << (ord(w) - 97)
    if reduce(lambda acc, cur: acc | cur, words) == allAlpha:
        dfs(0, 0)
    print(num)
