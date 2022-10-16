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

def check(temp_lst):
	answer = True
	q = deque()
	for i in range(n):
		for j in range(n):
			if temp_lst[i][j] == 'T':
				q.append((i, j, 1))
				U = True
				D = True
				L = True
				R = True
				while q:
					l, k, dist = q.popleft()
					print((l, k, dist))
					if temp_lst[l][k] == 'O':
						if l > i:
							D = False
						elif l < i:
							U = False
						elif k > j:
							R = False
						elif k < j:
							L = False
					elif temp_lst[l][k] == 'S':
						answer = False
						break
					if U and l - dist > 0:
						q.append((i - dist, j, dist + 1))
					if L and k - dist > 0:
						q.append((i, j - dist, dist + 1))
					if D and l + dist < n:
						q.append((i + dist, j, dist + 1))
					if R and k + dist < n:
						q.append((i, j + dist, dist + 1))
				print()
	return answer

ans = False

# def dfs(iter):
# 	global ans
# 	if iter == 3:
# 		if check():
# 			ans = True
# 			return
# 		else:
# 			return
# 	for i in range(n):
# 		for j in range(n):
# 			if lst[i][j] == 'X':
# 				lst[i][j] = 'O'
# 				dfs(iter + 1)
# 				lst[i][j] = 'X'
# dfs(0)

# if ans:
# 	print('YES')
# else:
# 	print('NO')

print(check([
		['X', 'S', 'O', 'X', 'T'],
		['T', 'O', 'S', 'X', 'X'],
		['X', 'X', 'O', 'X', 'X'],
		['X', 'T', 'X', 'X', 'X'],
		['X', 'X', 'T', 'X', 'X']]))