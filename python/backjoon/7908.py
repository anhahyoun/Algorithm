import sys

if __name__ == '__main__':
    n = int(sys.stdin.readline())
    print("TAK") if str(bin(n)).count("1") == 1 else print("NIE")
