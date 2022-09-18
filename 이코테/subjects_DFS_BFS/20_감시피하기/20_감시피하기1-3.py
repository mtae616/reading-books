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
	for i in range(n):
		for j in range(n):
			if lst[i][j] == 'T':
				U = True
				D = True
				L = True
				R = True
				for k in range(n):
					if i - k >= 0 and U:
						if lst[i - k][j] == 'O':
							U = False
						elif lst[i - k][j] == 'S':
							return False
					if i + k < n and D:
						if lst[i + k][j] == 'O':
							D = False
						elif lst[i + k][j] == 'S':
							return False
					if j - k >= 0 and L:
						if lst[i][j - k] == 'O':
							L = False
						elif lst[i][j - k] == 'S':
							return False
					if j + k < n and R:
						if lst[i][j + k] == 'O':
							R = False
						elif lst[i][j + k] == 'S':
							return False
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