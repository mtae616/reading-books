# 회사 1 ~ N , X에 판매한다
# 회사끼리 연결된 도로 이용
# a -> 소개팅 k -> 판매 x

# 회사의 개수 N, 경로의 개수 M
n, m = map(int, input().split())
INF = int(1e9)
distance = [[INF] * (n + 1) for i in range(n + 1)]

for a in range(1, n + 1):
	for b in range(1, n + 1):
		if a == b:
			distance[a][b] = 0

for i in range(m):
	a, b = map(int, input().split())
	distance[a][b] = 1
	distance[b][a] = 1

x, k = map(int, input().split())

for k in range(1, n + 1):
	for j in range(1, n + 1):
		for i in range(1, n + 1):
			distance[i][j] = min(distance[i][j], distance[i][k] + distance[k][j])

print(distance[1][k] + distance[k][x])