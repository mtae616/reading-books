# 2명 선발 -> 3, 7 -> 10, 10
# 능력치의 합을 최소화
import heapq

def solution(ability, number):
    heapq.heapify(ability)

    i = 0
    while i < number:
        a = heapq.heappop(ability)
        b = heapq.heappop(ability)
        heapq.heappush(ability, a + b)
        heapq.heappush(ability, a + b)
        i += 1
    return sum(ability)

solution([10, 3, 7, 2], 2)

