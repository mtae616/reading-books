# N x M
# 바이러스 상하좌우로 퍼져나갈 수 있음
# 새로 세울 수 있는 벽 3개, 꼭 3개를 세워야 함
# 0 은 빈칸, 1은 벽, 2는 바이러스
from collections import deque
import copy

n, m = map(int, input().split())
lst = list()
max_val = 0

for i in range(n):
	temp = list(map(int, input().split()))
	lst.append(temp)

def is_max():
	global max_val
	copy_map = copy.deepcopy(lst)
	q = deque()
	for i in range(n):
		for j in range(m):
			if copy_map[i][j] == 2:
				q.append((i, j))

	while q:
		i, j = q.pop()
		if i > 0 and copy_map[i - 1][j] == 0:
			copy_map[i - 1][j] = 2
			q.append((i - 1, j))
		if i + 1 < n and copy_map[i + 1][j] == 0:
			copy_map[i + 1][j] = 2
			q.append((i + 1, j))
		if j > 0 and copy_map[i][j - 1] == 0:
			copy_map[i][j - 1] = 2
			q.append((i, j - 1))
		if j + 1 < m and copy_map[i][j + 1] == 0:
			copy_map[i][j + 1] = 2
			q.append((i, j + 1))

	sum = 0
	for i in range(n):
		for j in range(m):	
			if copy_map[i][j] == 0:
				sum += 1
	max_val = max(max_val, sum)

def dfs(cnt):
	if cnt == 3:
		is_max()
		return
	for i in range(n):
		for j in range(m):
			if lst[i][j] == 0:
				lst[i][j] = 1
				dfs(cnt + 1)
				lst[i][j] = 0
dfs(0)
print(max_val)