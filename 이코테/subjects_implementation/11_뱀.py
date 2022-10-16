# N x N
# 맨위, 뱀 길이 1
# 오른쪽으로 머리 늘림
# 이동칸 사과 O, 사과 없어지고 꼬리 안 움직임
# 이동 칸 사과 X
# L 왼 D 오른

from collections import deque

n = int(input())

snake = deque()
snake.append((0, 0))

#    오  아  왼쪽 위
dx = [1, 0, -1, 0]
dy = [0, 1, 0, -1]

apple_lst = [[0 for _ in range(n)] for _ in range(n)]
for i in range(int(input())):
	y, x = map(int, input().split())
	apple_lst[y - 1][x - 1] = 1

l_lst = deque()

for i in range(int(input())):
	x, d = input().split()
	l_lst.append((int(x), d))

t = 0
d = 0
while 1:
	if len(l_lst) and t == l_lst[0][0]:
		if l_lst[0][1] == 'D':
			d += 1
			if d > 3:
				d = 0
		if l_lst[0][1] == 'L':
			d -= 1
			if d < 0:
				d = 3
		l_lst.popleft()
	if (snake[0][0] + dy[d], snake[0][1] + dx[d]) in snake:
		break
	temp_y = 0
	temp_x = 0
	for i in range(len(snake)):
		y, x = snake.popleft()
		if i == 0:
			temp_y = y
			temp_x = x
			snake.append((y + dy[d], x + dx[d]))
		else:
			snake.append((temp_y, temp_x))
			temp_y = y
			temp_x = x
	if snake[0][0] >= n or snake[0][1] >= n or snake[0][0] < 0 or snake[0][1] < 0:
		break
	if apple_lst[snake[0][0]][snake[0][1]] == 1:
		apple_lst[snake[0][0]][snake[0][1]] = 0
		snake.append((temp_y, temp_x))
	
	t += 1
	
print(t + 1)