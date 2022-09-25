# 스테이지에 도달했으나 클리어하지 못한 플레이어 수 / 스테이지에 도달한 플레이어 수
def solution(N, stages):
	ans = []
	temp = [0 for i in range(N + 2)]
	for s in stages:
		temp[s] += 1

	for i in range(1, N + 1):
		if sum(temp[i:]) == 0:
			ans.append((i, 0))
		else:
			ans.append((i, temp[i] / sum(temp[i:])))
		
	ans.sort(key= lambda x: -x[1])
	for i in range(len(ans)):
		ans[i] = ans[i][0]
	return ans

solution(5, [2, 1, 2, 6, 2, 4, 3, 3])