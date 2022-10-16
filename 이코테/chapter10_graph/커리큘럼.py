# 듣고자 하는 강의의 수 N

n = int(input())
graph = [[] for _ in range(n + 1)]
dp = [0 for _ in range(n + 1)]

for i in range(1, n + 1):
	graph[i] = list(map(int, input().split()))
	dp[i] += graph[i][0]
	for g in range(1, len(graph[i])):
		if graph[i][g] != -1:
			dp[i] += dp[graph[i][g]]
	print(dp[i])