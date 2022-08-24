# 학생 수 N 비교한 횟수 M
# i < k

n, m = map(int, input().split())

lst = [[0 for _ in range(n + 1)] for _ in range(n + 1)]

INF = int(1e9)

for i in range(n + 1):
	for j in range(n + 1):
		if i == j :
			lst[i][j] = 0
		else:
			lst[i][j] = INF

for i in range(m):
	j, k = map(int, input().split())
	lst[j][k] = 1

for k in range(n + 1):
	for i in range(n + 1):
		for j in range(n + 1):
				lst[i][j] = min(lst[i][k] + lst[k][j], lst[i][j])

result = 0
for i in range(1, n + 1):
	count = 0
	for j in range(1, n + 1):
		if lst[i][j] != INF or lst[j][i] != INF:
			count += 1
	if count == n:
		result += 1
		
for i in range(1, n + 1):
	for j in range(1, n + 1):
		if lst[i][j] == INF:
			print(0, end='\t')
		else:
			print(lst[i][j], end='\t')
	print()