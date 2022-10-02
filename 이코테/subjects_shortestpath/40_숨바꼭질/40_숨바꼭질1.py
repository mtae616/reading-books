# 1 ~ N 헛간
# M 양방향 통로
# 1번 헛간으로 가장 먼 헛간
from collections import deque
import heapq
from multiprocessing import heap
from operator import indexOf

INF = int(1e9)

# n, m = map(int, input().split())

# lst = [[INF for _ in range(n + 1)] for _ in range(n + 1)]

# for i in range(n + 1):
# 	for j in range(n + 1):
# 		if i == j:
# 			lst[i][j] = 0

# for i in range(m):
# 	a, b = map(int, input().split())
# 	lst[a][b] = 1
# 	lst[b][a] = 1

# for k in range(n + 1):
# 	for i in range(n + 1):
# 		for j in range(n + 1):
# 			lst[i][j] = min(lst[i][j], lst[i][k] + lst[k][j])

# for i in range(n + 1):
# 	if lst[1][i] == INF:
# 		lst[1][i] = 0

# max_val = max(lst[1])
# max_idx = 0

# for i in range(1, n + 1):
# 	if max_val == lst[1][i]:
# 		max_idx = i
# 		break

# cnt = 0
# for i in range(1, n + 1):
# 	if max_val == lst[1][i]:
# 		cnt += 1
# print(max_idx, max_val, cnt)

n, m = map(int, input().split())

lst = [[] * (n + 1) for _ in range(n + 1)]
distance = [INF for _ in range(n + 1)]
distance[0] = 0
distance[1] = 0

for _ in range(m):
	a, b = map(int, input().split())
	lst[a].append((b, 1))
	lst[b].append((a, 1))

q = []
heapq.heappush(q, (0, 1))
while q:
	dist, now = heapq.heappop(q)
	if distance[now] < dist:
		continue
	for i in lst[now]:
		cost = dist + i[1]
		if cost < distance[i[0]]:
			distance[i[0]] = cost
			heapq.heappush(q, (cost, i[0]))

print(lst)