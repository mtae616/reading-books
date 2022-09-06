# 1 ~ N
# 순위가 바뀐 팀의 목록만 발표
# 13 > 6 에서 13 < 6, 발표 -> (6, 13)

n = int(input())

for i in range(n):
	t = int(input())
	indegree = [0 for _ in range(t + 1)]
	t_lst = list(map(int, input().split())) # 1등 부터 출력
	for i in range(len(t_lst)):
		indegree[t_lst[i]] += len(t_lst[i:])
	for j in range(int(input())):
		a, b = map(int, input().split()) # a > b
		if indegree[a] > indegree[b]:
			indegree[a] -= 1
			indegree[b] += 1
		else:
			indegree[a] += 1
			indegree[b] -= 1
	test = set(indegree)
	if len(test) - 1 != t:
		print("IMPOSSIBLE")
	else:
		temp = t
		while temp != 0:
			for j in range(1, t + 1):
				if indegree[j] == temp:
					print(j, end=' ')
					break
			temp -= 1
		print()