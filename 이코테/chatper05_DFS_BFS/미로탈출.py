from collections import deque

n, m = map(int, input().split())

maze = list()
for i in range(n):
	maze.append(list(map(int, input())))
marked_map = [[0 for _ in range(m)] for _ in range(n)]

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

dir = 0
steps = 1
que = deque()
que.append((0, 0))

def isValid(x, y):
	return x >= 0 and x < m and y >= 0 and y < n
x = 0
y = 0
while que:
	x, y = que.pop()
	while dir < 4:
		new_x = x + dx[dir]
		new_y = y + dy[dir]
		if new_x == m and new_y == n:
			steps += 1
			print(steps)
			break
		if isValid(new_x, new_y) and maze[new_x][new_y] and marked_map[new_x][new_y] == 0:
			steps += 1
			marked_map[new_x][new_y] = 1
			x = new_x
			y = new_y
			dir = 0
			que.append((new_x, new_y))
		else:
			dir += 1
print(steps)