# 매초 번호가 낮은 종류의 바이러스 부터 증식
# 바이러스가 있다면 다른 바이러스 들어갈 수 없음
# s 초가 지난 후에 x, y 에 존재하는 바이러스 출력

from collections import deque

dx = [1, 0, -1, 0]
dy = [0, 1, 0, -1]

n, m = map(int, input().split())

lst = []
q = deque()

for i in range(n):
	lst.append(list(map(int, input().split())))
	for j in range(len(lst[i])):
		if lst[i][j] != 0:
			q.append((lst[i][j], i, j, 0))

S, X, Y = map(int, input().split())

q = list(q)
q.sort()
q = deque(q)

while q:
	virus, x, y, time = q.popleft()
	if time == S:
		break
	for direction in range(4):
		nx = x + dx[direction]
		ny = y + dy[direction]
		if nx < 0 or ny < 0 or nx >= n or ny >= n:
			continue
		if lst[nx][ny] == 0:
			lst[nx][ny] = virus
			q.append((virus, nx, ny, time + 1))

print(lst[X - 1][Y - 1])
