# TODO : 다음에 다시 풀어보기

from collections import deque

def solution(menu, order, k):
    answer = 0
    lst = [0] * len(order)
            # enque, deque, start
    lst[0] = (0, menu[order[0]], 0)
    for i in range(1, len(order)):
        lst[i] = (k * i, menu[order[i]], lst[i - 1][2] + lst[i - 1][1])

    t = 0
    q = deque()
    q.append(lst[0])
    i = 1
    while i < len(lst):
        if q and t - q[0][2] >= q[0][1]:
            q.popleft()
        if lst[i][0] == t:
            q.append(lst[i])
            i += 1
        t += 1
        answer = max(answer, len(q))
    return answer

solution([5, 12, 30], [2, 1, 0, 0, 0, 1, 0], 100)