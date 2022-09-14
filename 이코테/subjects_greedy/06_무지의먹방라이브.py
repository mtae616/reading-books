# 1 ~ N 개의 음식
# 1번부터, 회전판 번호 증가하는 대로
# 마지막 번호 -> 다시 1번
# 음식 하나 1초 동안 -> 음식 남아있어도, 다음 음식 섭취
# 먹방 시작 K 초 후 네트워크 장애
# 다시 방송을 이어갈 때 몇 번 음식부터?

import heapq

def solution(food_times, k):
	if sum(food_times) <= k:
		return -1
	
	q = []
	for i in range(len(food_times)):
		heapq.heappush(q, (food_times[i], i + 1))
	sum_val = 0
	previous = 0
	length = len(food_times)

	while sum_val + ((q[0][0] - previous) * length) <= k:
		now = heapq.heappop(q)[0]
		sum_val += (now - previous) * length
		length -= 1
		previous = now
	result = sorted(q, key = lambda x: x[1])
	return result[(k - sum_val) % length][1]

print(solution([7,8,3,3,2,2,2,2,2,2,2,2], 35))