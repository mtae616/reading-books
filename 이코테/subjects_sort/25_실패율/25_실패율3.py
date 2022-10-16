def solution(N, stages):
	temp = [0 for _ in range(N + 2)]
	ans = []
	stages.sort()
	buf = 0
	for i in range(len(stages)):
		if buf == stages[i]:
			continue
		temp[stages[i]] = (stages[i], stages.count(stages[i]) / (len(stages) - i))
		buf = stages[i]
	for i in range(N + 2):
		if temp[i] == 0:
			temp[i] = (i, 0)
	temp.sort(key= lambda x: -x[1])
	for t in temp:
		if t[0] > N or t[0] == 0:
			continue
		ans.append(t[0])
	return ans

solution(4, [4,4,4,4,4])