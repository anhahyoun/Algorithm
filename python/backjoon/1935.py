import sys

if __name__ == '__main__':
    n = int(sys.stdin.readline())
    equation = sys.stdin.readline().strip()
    nums = [sys.stdin.readline().strip() for i in range(n)]
    equation = map(lambda x: nums[ord(x) - 65] if x.isalpha() else x, equation)

    stack = []
    for i in equation:
        if i.isdigit():
            stack.append(i)
        else:
            b = stack.pop()
            a = stack.pop()
            stack.append(str(eval(" ".join([a, i, b]))))

    print("{:.2f}".format(float(stack[0])))
