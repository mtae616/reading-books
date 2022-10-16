# n 헛간
# m 통로

from bisect import bisect_left
INF = int(1e9)

n, m = map(int, input().split())

graph = [[INF] * (n + 1) for _ in range(n + 1)]

for i in range(m):
	a, b = map(int, input().split())
	graph[a][b] = 1
	graph[b][a] = 1

for i in range(n + 1):
	for j in range(n + 1):
		if i == j:
			graph[i][j] = 0

for k in range(n + 1):
	for i in range(n + 1):
		for j in range(n + 1):
			graph[i][j] = min(graph[i][j], graph[i][k] + graph[k][j])

max_val = max(graph[1][1:])
max_idx = bisect_left(graph[1], max_val)
cnt = 0
for i in range(1, n + 1):
	if max_val == graph[1][i]:
		cnt += 1
print(max_idx, max_val, cnt)