import heapq
from collections import deque

def solution(s,e):
    ans = 0
    dx = [1, -1, 5]

    q = [(0, s)]
    while q:
        cost, pos = heapq.heappop(q)
        if pos == e:
            ans = cost
            break
        for i in range(3):
            new_pos = pos + dx[i]
            heapq.heappush(q, (cost + 1, new_pos))
    return ans

solution(8, 3)