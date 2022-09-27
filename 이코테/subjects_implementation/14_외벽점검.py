# 동그란 모양
# 총 둘레 n
# 취약한 지점
# 공사시간 1시간
# 정북 방향 0, 떨어진 거리, 시계 방향으로 떨어진 거리 나타냄(?)
# 시계 혹은 반시계 방향으로 외벽을 따라서 이동

def clockwise(time, n, dist):
	n += dist
	if n > time:
		while n > time:
			n -= time
	return n

def anticlockwise(time, n, dist):
	n -= dist
	if n < 0:
		while n < 0:
			n += time
	return n

def cal_dist(time, a, b):
	clock_dif = 0
	anticlock_dif = 0
	if a > b:
		clock_dif = a - b
		anticlock_dif = time - a + b
	else:
		clock_dif = b - a
		anticlock_dif = time - b + a
	return clock_dif, anticlock_dif


def solution(n, weak, dist):
	answer = 0
	clock_dif = []
	anticlock_dif = []
	dif = []
	graph = [[0] * (n + 1) for _ in range(n + 1)]
	visited = [0 for _ in range(len(weak))]
	
	for i in range(len(weak)):
		for j in range(len(weak)):
			cl, an = cal_dist(n, weak[i], weak[j])
			clock_dif.append(cl)
			anticlock_dif.append(an)
			dif.append(min(cl, an))
			graph[weak[i]][weak[j]] = min(cl, an)
			
	print(clock_dif)
	print(anticlock_dif)
	dif = list(set(dif))
	print(dif)
	for i in range(n + 1):
		print(graph[i])

	return answer

solution(12, [1, 5, 6, 10], [1, 2, 3, 4])