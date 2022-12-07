# 대표 1명씩
# 종목당 1명의 대표
# 각 종목 대표의 해당 종목에 대한 능력치의 합을 최대화

ans = 0
arr_len = 0
selected_people = []

def dfs(iter, ability, sum):
	global ans
	if iter == len(ability[0]):
		ans = max(ans, sum)
		return
	for i in range(len(ability)):
		if not selected_people[i]:
			selected_people[i] = 1
			dfs(iter + 1, ability, sum + ability[i][iter])
			selected_people[i] = 0

def solution(ability):
	global selected_people
	global arr_len

	arr_len = len(ability)
	selected_people = [0] * len(ability)

	dfs(0, ability, 0)
	return ans

solution([
	[10, 100, 100],
	[20, 5, 0],
	[30, 0, 0],
	[70, 0, 5],
	[10, 10, 10],
])