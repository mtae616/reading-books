# 2 x 1 로봇
# N x N 지도

# need to check for first block
from collections import deque
from enum import Enum

class robot_status(Enum):
	V = 0,
	H = 1

move_dx = [1, 0, -1, 0]
move_dy = [0, 1, 0, -1]

def solution(board):
	visited = []
	board_size = len(board)
	q = deque()

	answer = 0
	q.append(((0, 0), (0, 1), robot_status.H))
	visited.append(((0, 0), (0, 1)))
	while q:
		a, b, status = q.pop()
		if b[0] == board_size and b[1] == board_size:
			break
		moved_flag = False
		if status == robot_status.H:
			for i in range(4):
				nx = b[0] + move_dx[i]
				mx = a[0] + move_dx[i]
				ny = b[1] + move_dy[i]
				my = a[1] + move_dy[i]
				if nx < 0 or ny < 0 or nx >= board_size or ny >= board_size:
					continue
				if mx < 0 or my < 0 or mx >= board_size or my >= board_size:
					continue
				if ((nx, ny), (mx, my)) not in visited:
					q.append(((nx, ny), (mx, my), robot_status.H))
					visited.append(((nx, ny), (mx, my)))
					moved_flag = True
			if not moved_flag:
				nx = b[0] - 1
				ny = b[1] - 1
				if nx < 0 or ny < 0 or nx >= board_size or ny >= board_size:
					continue
				if board[nx][ny] == 0 and board[b[0] - 1][b[1]] == 0:
					q.append(((b[0] - 1, b[1]), b, robot_status.V))
					visited.append(((b[0] - 1, b[1]), b))
				mx = b[0] + 1
				my = b[1] - 1
				if mx < 0 or my < 0 or mx >= board_size or my >= board_size:
					continue
				if board[mx][my] == 0 and board[b[0] + 1][b[1] == 0]:
					q.append(((b[0] + 1, b[1]), b, robot_status.V))

		elif status == robot_status.V:
			for i in range(4):
				nx = b[0] + move_dx[i]
				mx = a[0] + move_dx[i]
				ny = b[1] + move_dy[i]
				my = a[1] + move_dy[i]
				if nx < 0 or ny < 0 or nx >= board_size or ny >= board_size:
					continue
				if mx < 0 or my < 0 or mx >= board_size or my >= board_size:
					continue
				if ((nx, ny), (mx, my)) not in visited:
					q.append(((nx, ny), (mx, my), robot_status.H))
					visited.append(((nx, ny), (mx, my)))
					moved_flag = True
				if not moved_flag:
					nx = b[0] - 1
					ny = b[1] - 1
					if nx < 0 or ny < 0 or nx >= board_size or ny >= board_size:
						continue
					if board[nx][ny] == 0 and board[b[0]][b[1] - 1] == 0:
						q.append(((b[0], b[1] - 1), b, robot_status.H))
						visited.append(((b[0], b[1] - 1), (b[0], b[1])))
					mx = b[0] - 1
					my = b[1] + 1
					if mx < 0 or my < 0 or mx >= board_size or my >= board_size:
						continue
					if board[mx][my] == 0 and board[b[0] - 1][b[1] + 1] == 0:
						q.append(((b[0] - 1, b[1] + 1), b, robot_status.H))
						visited.append(((b[0] - 1, b[1] + 1), (b[0], b[1])))
	return answer

solution([
	[0, 0, 0, 1, 1],
	[0, 0, 0, 1, 0],
	[0, 1, 0, 1, 1],
	[1, 1, 0, 0, 1],
	[0, 0, 0, 0, 0]])