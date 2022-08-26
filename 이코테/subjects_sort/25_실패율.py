# 난도 조절
# 실패율 : 스테이지에 도달했으나 아직 클리어하지 못한 플레이어의 수 / 스테이지에 도달한 플레이어 수
# 전체 스테이지 N
# 현재 도달한 스테이지 배열
# 실패율이 높은 스테이지 부터 내림차순 리턴

def solution(N, stages):
	answer = []
	temp = [0 for _ in range(N + 2)]
	for s in stages:
		temp[s] += 1
	
	for i in range(1, N + 1):
		if sum(temp[i:]) == 0:
			answer.append((i, 0))
		else:
			answer.append((i, temp[i] / sum(temp[i:])))
	answer.sort(key=lambda x: -x[1])
	real_ans = []
	for a in answer:
		real_ans.append(a[0])
	return real_ans

solution(5, [3, 3, 3, 3])