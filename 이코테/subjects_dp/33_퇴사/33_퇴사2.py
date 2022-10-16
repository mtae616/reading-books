lst = []
n = int(input())
for tc in range(n):
	a, b = map(int, input().split())
	lst.append((a, b))

dp = [0] * (n + 1)

for i in range(n - 1, -1, -1):
	if lst[i][0] + i > n:
		dp[i] = dp[i + 1]
	else:
		dp[i] += max(dp[i + 1], lst[i][1] + dp[i + lst[i][0]])
print(dp[0])
