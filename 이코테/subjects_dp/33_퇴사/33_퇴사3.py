n = int(input())
days = []
cost = []
dp = []
for i in range(n):
    a, b = map(int, input().split())
    days.append(a)
    cost.append(b)
    dp.append(b)
dp.append(0)

for i in range(n - 1, -1, -1):
    if days[i] + i > n:
        dp[i] = dp[i + 1]
    else:
        dp[i] = max(dp[i + 1], dp[i + days[i]] + cost[i])
        print(i)
        print(dp)
        print()

print(dp[0])