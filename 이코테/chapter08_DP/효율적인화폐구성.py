# N 가지 종류의 화폐
# M 원이 되도록
# 불가능 할 때는 -1

n, m = map(int, input().split())

dp = [10001 for _ in range(10001)]

coin_list = []
for i in range(n):
	coin_list.append(int(input()))

for i in range(len(dp)):
	for coin in coin_list:
		if i % coin == 0:
			dp[i] = i // coin

for i in range(3, len(dp)):
	dp[i] = min(dp[i], dp[i - 1] + 1)

print(dp[m])