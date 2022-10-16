from collections import deque
from copy import copy, deepcopy


n, m = map(int, input().split())

lst = []

dx = [1, 0, -1, 0]
dy = [0, 1, 0, -1]

ans = 0

for i in range(n):
	lst.append(list(map(int, input().split())))

def check():
	global ans
	copied_map = deepcopy(lst)
	q = deque()
	for i in range(n):
		for j in range(m):
			if copied_map[i][j] == 2:
				q.append((i, j))
	while q:
		l, k = q.pop()
		for i in range(4):
			nx = l + dx[i]
			ny = k + dy[i]
			if nx < 0 or ny < 0 or nx >= n or ny >= m:
				continue
			if copied_map[nx][ny] == 0:
				copied_map[nx][ny] = 2
				q.append((nx, ny))
	sum = 0
	for i in range(n):
		for j in range(m):
			if copied_map[i][j] == 0:
				sum += 1
	ans = max(sum, ans)

def dfs(iter):
	if iter == 3:
		check()
		return 
	for i in range(n):
		for j in range(m):
			if lst[i][j] == 0:
				lst[i][j] = 1
				dfs(iter + 1)
				lst[i][j] = 0
dfs(0)

print(ans)

