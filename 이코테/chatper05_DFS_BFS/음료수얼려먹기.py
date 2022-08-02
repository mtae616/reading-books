from collections import deque

n, m = map(int, input().split())

plate = list()

for i in range(n):
	plate.append(list(input()))

visited = [[0 for _ in range(m)] for _ in range(n)]

que = deque()
cnt = 0
for i in range(n):
	for j in range(m):
		if plate[i][j] == '0' and visited[i][j] == 0:
			visited[i][j] = 1
			que.append((i, j))
			while que:
				k, l = que.popleft()
				if (k + 1) < n and visited[k + 1][l] == 0 and plate[k + 1][l] == '0':
					visited[k + 1][l] = 1
					que.append((k + 1, l))
				if (l + 1) < m and visited[k][l + 1] == 0 and plate[k][l + 1] == '0':
					visited[k][l + 1] = 1
					que.append((k, l + 1))
			cnt += 1
			
print(cnt)