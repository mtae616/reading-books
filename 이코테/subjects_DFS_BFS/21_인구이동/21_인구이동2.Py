# 두 인구 차이 l 이상 r 이하 람면 국경 열음
# 인구 수는 총 인구 / 칸 개수

from collections import deque

dx = [1, 0, -1, 0]
dy = [0, 1, 0, -1]

n, l, r = map(int, input().split())

graph = []
for _ in range(n):
	graph.append(list(map(int, input().split())))

result = 0

def resolve(x, y, index):
	united = []
	united.append((x, y))
	q = deque()
	q.append((x, y))
	union[x][y] = index
	summary = graph[x][y]
	count = 1
	while q:
		x, y = q.popleft()
		for i in range(4):
			nx = x + dx[i]
			ny = y + dy[i]
			if 0 <= nx < n and 0 <= ny < n and union[nx][ny] == -1:
				if l <= abs(graph[nx][ny] - graph[x][y]) <= r:
					q.append((nx, ny))
					union[nx][ny] = index
					summary += graph[nx][ny]
					count += 1
					united.append((nx, ny))
	for i, j in united:
		graph[i][j] = summary // count
	return count

total_cnt = 0

while True:
	union = [[-1] * n for _ in range(n)]
	index = 0
	for i in range(n):
		for j in range(n):
			if union[i][j] == -1:
				resolve(i, j, index)
				index += 1
	if index == n * n:
		break
	total_cnt += 1
print(total_cnt)
