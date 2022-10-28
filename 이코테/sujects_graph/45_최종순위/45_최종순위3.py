from collections import deque


for tc in range(int(input())):
    n = int(input())
    indegree = [0] * (n + 1)
    lst = list(map(int, input().split()))
    graph = [[False] * (n + 1) for _ in range(n + 1)]

    for i in range(n):
        for j in range(i + 1, n):
            graph[lst[i]][lst[j]] = True
            indegree[lst[j]] += 1
    for _ in range(int(input())):
        a, b = map(int, input().split())
        if graph[a][b]:
            indegree[a] += 1
            indegree[b] -= 1
            graph[a][b] = False
            graph[b][a] = True
        else:
            indegree[a] -= 1
            indegree[b] += 1
            graph[b][a] = False
            graph[a][b] = True


    result = []
    q = deque()
    for i in range(1, len(indegree)):
        if indegree[i] == 0:
            q.append(i)

    cycle = False
    certain = True

    for i in range(n):
        if len(q) == 0:
            cycle = True
            break
        if len(q) >= 2:
            certain = False
            break
        now = q.popleft()
        result.append(now)
        for j in range(1, n + 1):
            if graph[now][j]:
                indegree[j] -= 1
                if indegree[j] == 0:
                    q.append(j)

    if cycle:
        print('IMPOSSIBLE')
    elif not certain:
        print('?')
    else:
        for i in result:
            print(i, end=' ')
        print()