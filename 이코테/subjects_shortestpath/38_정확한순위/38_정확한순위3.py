n,m = map(int, input().split())

INF = int(1e9)

graph = [[INF] * (n + 1) for _ in range(n + 1)]

for i in range(n):
	a, b = map(int, input().split())
	graph[a][b] = 1

for k in range(1, n + 1):
	for i in range(1, n + 1):
		for j in range(1, n + 1):
			graph[i][j] = min(graph[i][j], graph[i][k] + graph[k][j])

for i in range(1, n + 1):
	for j in range(1, n + 1):
		if graph[i][j] == INF:
			print(0, end=' ')
		else:
			print(graph[i][j], end=' ')
	print()

ans = 0
for i in range(1, n + 1):
	cnt = 0
	for j in range(1, n + 1):
		if graph[i][j] != INF or graph[j][i] != INF:
			cnt += 1
	if cnt >= (n - 1):
		ans += 1
print(ans)
