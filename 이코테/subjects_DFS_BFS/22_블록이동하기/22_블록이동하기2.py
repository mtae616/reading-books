ans = int(1e9)

pos = []

def dfs(iter, first, second, board):
	global ans
	global pos
	board_len = len(board)

	print(first, second)
	print()

	if (first[0] == board_len - 1 and first[1] == board_len - 1) or (second[0] == board_len - 1 and second[1] == board_len - 1):
		ans = min(ans, iter)
		return
	# 가로
	if first[0] == second[0]:
		# 전진
		if second[1] + 1 < board_len:
			if {second, (second[0], second[1] + 1)} not in pos and board[second[0]][second[1] + 1] == 0:
				pos.append({second, (second[0], second[1] + 1)})
				dfs(iter + 1, second, (second[0], second[1] + 1), board)
		# 아래 회전
		if second[0] + 1 < board_len and second[1] > 0:
			if {second, (first[0] + 1, first[1] + 1)} not in pos and board[second[0] + 1][second[1] - 1] == 0 and board[second[0] + 1][second[1]] == 0:
				pos.append({second, (first[0] + 1, first[1] + 1)})
				dfs(iter + 1, second, (first[0] + 1, first[1] + 1), board)
		# 위 회전
		if first[0] > 0 and second[0] > 0 and second[1] > 0:
			if {(first[0] - 1, first[1] + 1), second} not in pos and board[second[0] - 1][second[1] - 1] == 0 and board[second[0] - 1][second[1]] == 0:
				pos.append({(first[0] - 1, first[1] + 1), second})
				dfs(iter + 1, (first[0] - 1, first[1] + 1), second, board)

	#세로
	elif first[1] == second[1]:
		# 전진
		if second[0] + 1 < board_len:
			if {second, (second[0] + 1, second[1])} not in pos and board[second[0] + 1][second[1]] == 0:
				pos.append({second, (second[0] + 1, second[1])})
				dfs(iter + 1, second, (second[0] + 1, second[1]), board)
		# 반시계 회전
		if first[1] > 0 and second[1] > 0:
			if {(first[0] + 1, first[1] - 1), second} not in pos and board[first[0]][first[1] - 1] == 0 and board[second[0]][second[1] - 1] == 0:
				pos.append({(first[0] + 1, first[1] - 1), second})
				dfs(iter + 1, (first[0] + 1, first[1] - 1), second, board)
		# 시계 회전
		if first[1] + 1 < board_len and second[1] + 1 < board_len:
			if {second, (first[0] + 1, first[1] + 1)} not in pos and board[first[0]][first[1] + 1] == 0 and board[second[0]][second[1] + 1] == 0:
				pos.append({second, (first[0] + 1, first[1] + 1)})
				dfs(iter + 1, second, (first[0] + 1, first[1] + 1), board)

def solution(board):
	global ans
	pos.append({(0, 0), (0, 1)})

	dfs(0, (0, 0), (0, 1), board)
	print(ans)
	return ans

solution([
	[0, 0, 1, 0, 0, 0, 0, 0, 0, 0],
	[0, 0, 0, 0, 0, 1, 0, 0, 0, 0], 
	[1, 0, 0, 0, 1, 0, 0, 0, 0, 0],
	[1, 0, 0, 0, 0, 0, 0, 0, 0, 0], 
	[1, 0, 0, 0, 0, 0, 0, 0, 0, 0], 
	[0, 0, 1, 1, 1, 0, 0, 0, 0, 0], 
	[0, 0, 0, 0, 1, 0, 0, 0, 0, 0], 
	[0, 1, 0, 0, 0, 0, 0, 0, 0, 0], 
	[0, 0, 0, 0, 0, 0, 1, 1, 0, 1], 
	[0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
])