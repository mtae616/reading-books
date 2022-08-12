from collections import deque
# 1 ~ N 도시
# M 단방향 도로 (모든 거리는 1)
# x -> 최단거리 k 모든 도시의 번호
# N M K X

n, m, k, x = map(int, input().split())

INF = 99999

lst = [[] for _ in range(n + 1)]
visited = [[INF for i in range(n + 1)] for i in range(n + 1)]

for i in range(m):
	a, b = map(int, input().split())
	lst[a].append(b)
	visited[a][b] = 1

q = deque()
for i in range(len(lst[x])):
	q.append((lst[x][i], 1))

while q:
	j, dis = q.pop()
	if dis == k and visited[x][j] > dis:
		print(j)
	if lst[j]:
		for i in range(len(lst[j])):
			q.append((lst[j][i], dis + 1))
			if visited[x][i] > dis + 1:
				visited[x][i] = dis + 1