import sys

if __name__ == '__main__':
    s = sys.stdin.readline().strip()
    p = sys.stdin.readline().strip()
    fail = [0] * len(p)
    j = 0

    for i in range(1, len(p)):
        while j > 0 and p[i] != p[j]:
            j = fail[j - 1]
        if p[i] == p[j]:
            j += 1
            fail[i] = j

    j = 0
    for i in range(len(s)):
        while j > 0 and s[i] != p[j]: j = fail[j - 1]
        if s[i] == p[j]:
            if j == len(p) - 1:
                print(1)
                break
            else:
                j += 1
    else:
        print(0)
