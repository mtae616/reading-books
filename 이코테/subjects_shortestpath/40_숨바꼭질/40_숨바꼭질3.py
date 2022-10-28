import heapq

n, m = map(int, input().split())

INF = int(1e9)
graph = [[INF] * (n + 1) for _ in range(n + 1)]

distance = [INF] * (n + 1)

for i in range(n + 1):
    for j in range(n + 1):
        if i == j:
            graph[i][j] = 0

for i in range(m):
    a, b = map(int, input().split())
    graph[a][b] = 1
    graph[b][a] = 1

q = []

heapq.heappush(q, (0, 1))
while q:
    dist, i = heapq.heappop(q)
    if dist > distance[i]:
        continue
    for k in range(len(graph[i])):
        if graph[i][k] != INF:
            cost = dist + graph[i][k]
            if cost < distance[k]:
                heapq.heappush(q, (cost, k))
                distance[k] = cost
print(distance)

