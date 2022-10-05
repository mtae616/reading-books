# 1 ~ N, 도시
# M 단방향 도로
# 최단거리 k, 출발 도시 x
import heapq

INF = int(1e9)
n, m, k, x = map(int, input().split())

distance = [INF] * (n + 1)

graph = [[] * (n + 1) for _ in range(n + 1)]
for i in range(m):
	a, b = map(int, input().split())
	graph[a].append(b)

q = []
q.append((0, x))
distance[x] = 0
while q:
	dist, a = heapq.heappop(q)
	if distance[a] < dist:
		continue
	for g in graph[a]:
		cost = dist + 1
		if distance[g] > cost:
			distance[g] = cost
			heapq.heappush(q, (cost, g))

flag = True
for i in range(len(distance)):
	if distance[i] == k:
		print(i)
		flag = False
if flag:
	print(-1)