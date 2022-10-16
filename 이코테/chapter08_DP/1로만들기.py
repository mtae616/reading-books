# 5로 나눠지면 5로 나눈다
# 3으로 나눠지면 3으로 나눈다
# 2로 나눠지면 2로 나눈다
# 1 뺀다
# 연산 최솟값
n = int(input())
dp = [0 for i in range(100)]

for i in range(2, n + 1):
	dp[i] = dp[i - 1] + 1
	if i % 2 == 0:
		dp[i] = min(dp[i], dp[i // 2] + 1)
	if i % 3 == 0:
		dp[i] = min(dp[i], dp[i // 3] + 1)
	if i % 5 == 0:
		dp[i] = min(dp[i], dp[i // 5] + 1)
print(dp)