# 학생 N
# 정확한 순위

n, m = map(int, input().split())

INF = int(1e9)

lst = [[INF] * (n + 1) for _ in range(n + 1)]

for i in range(n + 1):
	for j in range(n + 1):
		if i == j:
			lst[i][j] = 0

for i in range(m):
	a, b = map(int, input().split())
	lst[a][b] = 1
	lst[b][a] = 1

for i in range(1, n + 1):
	for j in range(1, n + 1):
		for k in range(1, n + 1):
			lst[i][j] = min(lst[i][j], lst[i][k] + lst[k][j])
result = 0
for i in range(1, n + 1):
	count = 0
	for j in range(1, n + 1):
		if lst[i][j] != INF or lst[j][i] != INF:
			count += 1
	if count == n:
		print(i)
