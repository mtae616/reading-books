# N x N 복도
# 1 x 1 크기의 칸
# 선생님 T, 학생 S, 장애물 O
# 학생들은 선생님 감시에 들키지 않고 나가야 한다.
# 선생님 감시 -> 상하좌우, 장애물 뒤편에 있는 학생 볼 수 없음

from collections import deque

n = int(input())

lst = []
for _ in range(n):
	lst.append(list(input().split()))

def check():
	answer = True
	q = deque()
	for i in range(n):
		for j in range(n):
			if lst[i][j] == 'S':
				q.append((i, j))
				width = True
				vertical = True
				while q:
					l, k = q.popleft()
					if lst[l][k] == 'T':
						answer = False
						break
					if lst[l][k] == 'O':
						if l > i:
							vertical = False
						if k > j:
							width = False
					if vertical and l + 1 < n:
						q.append((l + 1, j))
					if width and  k + 1 < n:
						q.append((l, k + 1))
	return answer

ans = False

def dfs(iter):
	global ans
	if iter == 3:
		if check():
			ans = True
			return
		else:
			return
	for i in range(n):
		for j in range(n):
			if lst[i][j] == 'X':
				lst[i][j] = 'O'
				dfs(iter + 1)
				lst[i][j] = 'X'
dfs(0)

if ans:
	print('YES')
else:
	print('NO')