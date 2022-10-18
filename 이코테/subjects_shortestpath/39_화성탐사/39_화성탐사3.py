import heapq

dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]

INF = int(1e9)

for tc in (range(int(input()))):
	n = int(input())
	lst = []
	distance = [[INF] * n for _ in range(n)]
	for i in range(n):
		lst.append(list(map(int, input().split())))
	distance[0][0] = lst[0][0]
	q = []
	heapq.heappush(q, (lst[0][0], 0, 0))
	while q:
		dist, i, j, = heapq.heappop(q)
		if distance[i][j] < dist:
			continue
		for k in range(4):
			nx = i + dx[k]
			ny = j + dy[k]
			if nx < 0 or ny < 0 or nx >= n or ny >= n:
				continue
			cost = dist + lst[nx][ny]
			if cost < distance[nx][ny]:
				distance[nx][ny] = cost
				heapq.heappush(q, (cost, nx, ny))
	print(distance[n - 1][n - 1])


	