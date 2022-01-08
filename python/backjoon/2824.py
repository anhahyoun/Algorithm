import sys


def gcd(a, b):
    while b > 0:
        tmp = a
        a = b
        b = tmp % b
    return a


n = int(sys.stdin.readline())
a = 1
for i in map(int, sys.stdin.readline().split(" ")):
    a *= i
m = int(sys.stdin.readline())
b = 1
for i in map(int, sys.stdin.readline().split(" ")):
    b *= i
answer = gcd(a, b)

print(str(answer)[-9:])
