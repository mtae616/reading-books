from collections import deque
import heapq

n = int(input())
map_lst = [[0] * (n) for _ in range(n)]

dx = [1, 0, -1, 0]
dy = [0, 1, 0, -1]

for apple in range(int(input())):
	y, x = map(int, input().split())
	map_lst[y - 1][x - 1] = 1

dir_lst = []

for L in range(int(input())):
	time, direc = input().split()
	heapq.heappush(dir_lst, (int(time), direc))

snake_lst = deque()
snake_lst.append((0, 0))

t = 0
i = 0
map_lst[0][0] = 2
while 1:
	y = snake_lst[0][0]
	x = snake_lst[0][1]
	if dir_lst and t == dir_lst[0][0]:
		time, direc = heapq.heappop(dir_lst)
		if direc == 'L':
			i -= 1
			if i < 0:
				i = 3
		if direc == 'D':
			i += 1
			if i > 3:
				i = 0
	nx = dx[i] + x
	ny = dy[i] + y
	if nx < 0 or ny < 0 or nx >= n or ny >= n:
		break
	if (ny, nx) in snake_lst:
		break
	if map_lst[ny][nx] != 1:
		snake_lst.pop()
	else:
		map_lst[ny][nx] = 0
	snake_lst.appendleft((ny, nx))
	t += 1
print(t + 1)