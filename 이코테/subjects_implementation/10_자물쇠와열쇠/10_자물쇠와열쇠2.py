def rotate_90(lst):
	n = len(lst)
	m = len(lst[0])

	result = [[0] * n for _ in range(m)]
	for i in range(n):
		for j in range(m):
			result[j][n - i - 1] = lst[i][j]
	return result

def check(extendMap, m, n):
	for i in range(n):
		for j in range(n):
			if extendMap[m + i][m + j] != 1:
				return False
	return True

def init_map(lock, key):
	n = len(lock)
	m = len(key)
	extendMap = [[0] * (m * 2 + n) for _ in range(m * 2 + n)]
	for i in range(n):
		for j in range(n):
			extendMap[m + i][m + j] = lock[i][j]
	return extendMap

def match_key(extendMap, e_i, e_j, key, m):
	m = len(key)
	for i in range(m):
		for j in range(m):
			extendMap[e_i + i][e_j + j] += key[i][j]

def pull_key(extendMap, e_i, e_j, key, m):
	m = len(key)
	for i in range(m):
		for j in range(m):
			extendMap[e_i + i][e_j + j] -= key[i][j]

def	solution(key, lock):
	N = len(lock)
	M = len(key)
	extendMap = init_map(lock, key)
	rotated = key
	for k in range(4):
		rotated = rotate_90(rotated)
		for i in range(1, N + M):
			for j in range(1, N + M):
				match_key(extendMap, i, j, rotated)
				if check(extendMap, N, M):
					return True
				pull_key(extendMap, i, j, rotated)
	return False

print(solution([[0, 0, 0], [1, 0, 0], [0, 1, 1]],
[[1, 1, 1], [1, 1, 0], [1, 0, 1]]))