import heapq

INF = int(1e9)
dx = [-1, 0, 1, 0]
dy = [0, -1, 0, 1]
for tc in range(int(input())):
    lst = []
    n = int(input())
    for map_input in range(n):
        lst.append(list(map(int, input().split())))
    distance = [[INF] * n for _ in range(n)]
    q = []
    heapq.heappush(q, (0, 0, lst[0][0]))
    distance[0][0] = lst[0][0]
    while q:
        i, j, dist = heapq.heappop(q)
        print(i, j, dist)
        if distance[i][j] < dist:
            continue
        for k in range(4):
            nx = i + dx[k]
            ny = j + dy[k]
            if nx < 0 or ny < 0 or nx >= n or ny >= n:
                continue
            cost = dist + lst[nx][ny]
            if cost < distance[nx][ny]:
                distance[nx][ny] = cost
                heapq.heappush(q, (nx, ny, cost))
    print(distance)
