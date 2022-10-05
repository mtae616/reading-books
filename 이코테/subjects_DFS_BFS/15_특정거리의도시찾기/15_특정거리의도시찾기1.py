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


# n, m, k, x = map(int, input().split())

# graph = [[] for _ in range(n + 1)]
# for _ in range(m):
# 	a,b = map(int, input().split())
# 	graph[a].append(b)

# distance = [-1] * (n + 1)
# distance[x] = 0

# q = deque([x])
# while q:
# 	now = q.popleft()
# 	for next_node in graph[now]:
# 		if distance[next_node] == -1:
# 			distance[next_node] = distance[now] + 1
# 			q.append(next_node)

# check = False
# for i in range(1, n + 1):
# 	if distance[i] == k:
# 		print(i)
# 		check = True

# if check == False:
# 	print(-1)