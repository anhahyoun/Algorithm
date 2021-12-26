def solution(n, words):
    answer = []
    dic = { words[0] : 1 }

    for i in range(1, len(words)):
        if words[i] in dic: return [(i % n) + 1, (i // n) + 1]
        if words[i][0] == words[i-1][-1]:
            dic[words[i]] = 1
        else:
            return [(i % n) + 1, (i // n) + 1]
    else:
        return [0, 0]
