# N + 1 퇴사... 남은 N 일 동안 최대한 많은 상담
# N
# T 일수 P value

n = int(input())

dp = []
days = []
cost = []

for i in range(n):
	k, m = map(int, input().split())
	days.append(k)
	cost.append(m)
	dp.append(m)

dp.append(0)

for i in range(n - 1, -1, -1):
	if days[i] + i > n:
		dp[i] = dp[i + 1]
	else:
		dp[i] = max(dp[i + 1], cost[i] + dp[i + days[i]])

print(dp)
