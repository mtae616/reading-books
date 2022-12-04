# 각 던전마다 -> 최소 피로도, 소모 피로도
# 최소 피로도 : 탐험하기 위해 가지고 있어야 하는 피로도
# 소모 피로도 : 탐험 후 소모되는 피로도
# 최대한 많이 탐험

ans = 0
visited = []

def dfs(k, dungeons, iter):
	global ans
	ans = max(ans, iter)
	
	for i in range(len(dungeons)):
		if not visited[i] and k >= dungeons[i][0]:
			visited[i] = 1
			dfs(k - dungeons[i][1], dungeons, iter + 1)
			visited[i] = 0

def solution(k, dungeons):
    global visited
    visited = [0] * len(dungeons)
    dfs(k, dungeons, 0)
    return ans

solution(80, [
	# 최소, 소모
	[80, 20],
	[50, 40],
	[30, 10]
])
