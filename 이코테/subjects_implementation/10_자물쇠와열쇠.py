from collections import deque
import copy
# N x N 자물쇠
# M X M 열쇠
# 열쇠의 돌기(1) -> 자물쇠의 홈(0)

def move_right(key, lock):
	lst = deque()
	for i in range(len(lock)):
		for j in range(len(lock)):
			if key[i][j] == 1:
				lst.append((i, j))
				key[i][j] = 0
	for i in range(len(lst)):
		y, x = lst.popleft()
		lst.append((y, x + 1))
	for l in lst:
		y, x = l
		if y < len(lock) and x < len(lock):
			key[y][x] = 1
	return key

def move_left(key, lock):
	lst = deque()
	for i in range(len(lock)):
		for j in range(len(lock)):
			if key[i][j] == 1:
				lst.append((i, j))
				key[i][j] = 0
	for i in range(len(lst)):
		y, x = lst.popleft()
		lst.append((y, x - 1))
	for l in lst:
		y, x = l
		if y >= 0 and x >= 0:
			key[y][x] = 1
	return key

def move_up(key, lock):
	lst = deque()
	for i in range(len(lock)):
		for j in range(len(lock)):
			if key[i][j] == 1:
				lst.append((i, j))
				key[i][j] = 0
	for i in range(len(lst)):
		y, x = lst.popleft()
		lst.append((y - 1, x))
	for l in lst:
		y, x = l
		if y >= 0 and x >= 0:
			key[y][x] = 1
	return key

def move_down(key, lock):
	lst = deque()
	for i in range(len(lock)):
		for j in range(len(lock)):
			if key[i][j] == 1:
				lst.append((i, j))
				key[i][j] = 0
	for i in range(len(lst)):
		y, x = lst.popleft()
		lst.append((y + 1, x))
	for l in lst:
		y, x = l
		if y < len(lock) and x < len(lock):
			key[y][x] = 1
	return key

def rotate_90(matrix):
	n = len(matrix)
	res = [[0] * n for _ in range(n)]

	for row in range(n):
		for col in range(n):
			res[col][n - 1 - row] = matrix[row][col]
	return res

def match_key(board, M, N):
	for i in range(N):
		for j in range(N):
			if board[M + i][M + j] != 1:
				return False
	return True

def attach(x, y, M, key, board):
    for i in range(M):
        for j in range(M):
            board[x+i][y+j] += key[i][j]

def detach(x, y, M, key, board):
    for i in range(M):
        for j in range(M):
            board[x+i][y+j] -= key[i][j]

def	solution(key, lock):
	M, N = len(key), len(lock)

	board =[[0] * (M * 2 + N) for _ in range(M * 2 + N)]
	
	for i in range(N):
		for j in range(N):
			board[M + i][M + j] = lock[i][j]
	
	rotated = key
	for _ in range(4):
		rotated = rotate_90(rotated)
		for x in range(1, M + N):
			for y in range(1, M + N):
				attach(x, y, M, rotated, board)
				if (match_key(board, M, N)):
					return True
				detach(x, y, M, rotated, board)
	return False

print(solution([[0, 0, 0], [1, 0, 0], [0, 1, 1]], [[1, 1, 1], [1, 1, 0], [1, 0, 1]]))