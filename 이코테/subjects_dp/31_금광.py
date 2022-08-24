from turtle import left


t = int(input())
for i in range(t):
	n, m = map(int, input().split())
	lst = list(map(int, input().split()))
	max_val = 0
	idx = 0
	for i in range(0, n):
		s = i * m
		max_val = max(max_val, lst[s])
		if lst[s] == max_val:
			idx = s
	result = max_val
	for i in range(1, m):
		if idx - (m - 1) < 0:
			right_up = 0
		else:
			right_up = idx - (m - 1)
		if idx + (m + 1) > (m * n):
			right_down = 0
		else:
			right_down = idx + (m + 1)
		right = idx + 1

		max_val = max(lst[right_up], lst[right], lst[right_down])
		if max_val == lst[right_up]:
			idx = right_up
		elif max_val == lst[right]:
			idx = right
		elif max_val == lst[right_down]:
			idx = right_down
		result += max_val
	print(result)

# 책
for tc in range(int(input())):
	n, m = map(int, input().split())
	array = list(map(int, input().split()))
	
	dp = []
	index = 0
	for i in range(n):
		dp.append(array[index:index + m])
		index += m
	for j in range(1, m):
		for i in range(n):
			# 왼쪽 위에서 오는 경우
			if i == 0:
				left_up = 0
			else:
				left_up = dp[i - 1][j - 1]
			# 왼쪽 아래에서 오는 경우
			if i == n - 1:
				left_down = 0
			else:
				left_down = dp[i + 1][j - 1]
			# 왼쪽에서 오는 경우
			left = dp[i][j - 1]
			dp[i][j] = dp[i][j] + max(left_up, left_down, left)

	result = 0
	for i in range(n):
		result = max(result, dp[i][m - 1])
	print(result)
