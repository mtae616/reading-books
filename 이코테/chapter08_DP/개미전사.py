# 식량창고 일직선
# 각 식량창고는 정해진 수의 식량
# 최소한 한 칸 이상 떨어진 식량창고
# n 식량창고

n = int(input())
n_list = list(map(int, input().split()))

dp = [0 for _ in range(100)]

dp[0] = n_list[0]
dp[1] = max(n_list[0], n_list[1])

for i in range(2, n):
	dp[i] = max(dp[i - 1], dp[i - 2] + n_list[i])

print(dp[n - 1])