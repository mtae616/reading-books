# 실행 순서 = 우선순위 + 호출 시각
import heapq

def solution(program):
	answer = [0] * 11
	t = 0
	program.sort(key=lambda x:(max(x[1]-t,0),x[0]))

	idx = 0
	start_time = 0
	while idx < len(program):
		priority, fetch, excution = program[idx]
		if start_time + excution == t:
			idx += 1
			if idx == len(program):
				continue
			answer[program[idx][0]] += t - program[idx][1]
			start_time = t
		t += 1
	answer[0] = t
	print(answer)
	return answer

solution(
	#priority, fetch, excution
	[[2, 0, 10], [1, 5, 5], [3, 5, 3], [3, 12, 2]]	
)

# [1, 0, 5]
# [4, 2, 5]
# [3, 6, 4]
# [5, 0, 5]