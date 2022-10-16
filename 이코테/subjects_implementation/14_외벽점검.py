# 동그란 모양
# 총 둘레 n
# 취약한 지점
# 공사시간 1시간
# 정북 방향 0, 떨어진 거리, 시계 방향으로 떨어진 거리 나타냄(?)
# 시계 혹은 반시계 방향으로 외벽을 따라서 이동

from itertools import permutations

def solution(n, weak, dist):
	length = len(weak)
	for i in range(length):
		weak.append(weak[i] + n)
	answer = len(dist) + 1

	for start in range(length):
		for friends in list(permutations(dist, len(dist))):
			count = 1
			position = weak[start] + friends[count - 1]
			for index in range(start, start + length):
				if position < weak[index]:
					count += 1
					if count > len(dist):
						break
					position = weak[index] + friends[count - 1]
			answer = min(answer, count)
	if answer > len(dist):
		return -1
	return answer

solution(12, [1, 3, 4, 9, 10], [3, 5, 7])