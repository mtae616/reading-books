import sys

input = sys.stdin.readline

ans = False

dx = [1, 0, -1, 0]
dy = [0, 1, 0, -1]

def dfs(iter, color, x, y, from_x, from_y):
    global ans
    if ans is True:
        return
    for i in range(4):
        nx, ny = x + dx[i], y + dy[i]
        if nx < 0 or ny < 0 or nx >= n or ny >= m:
            continue
        if iter >= 4 and nx == from_x and ny == from_y:
            ans = True
            return
        if lst[nx][ny] == color and visited[nx][ny] == 0:
            visited[nx][ny] = 1
            dfs(iter + 1, color, nx, ny, from_x, from_y)
            visited[nx][ny] = 0

n, m = map(int, input().split())
lst = []
for _ in range(n):
    lst.append(list(input().rstrip()))

visited = [[0] * m for _ in range(n)]

for i in range(n):
    for j in range(m):
        visited[i][j] = 1
        dfs(1, lst[i][j], i, j, i, j)
if ans:
    print('Yes')
else:
    print('No')