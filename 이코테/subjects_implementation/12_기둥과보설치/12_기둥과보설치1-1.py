# 기둥과 보 -> 벽면 설치
# 기둥, 바닥 위 or 보의 한쪽 끝 or 다른 기둥 위
# 보, 한쪽 끝이 기둥 위 or 양쪽 끝 다른 보와 연결

# 벽면 크기 n, 작업 순서 build_frame
# build_frame : x, y, a, b
# a 설치 또는 삭제할 구조물 종류 : 0 기둥, 1 보
# b 설치여부 : 0 삭제 1 설치

# 맞는 조건
# if a == 0: # 기둥
# 			if y == 0: # 바닥
# 			if temp[y - 1][x] == 0: # 아래가 기둥
# 			if temp[y - 1][x] == 1: # 아래가 보
# 		else: # 보
# 			if temp[y - 1][x] == 0: # 아래가 기둥
# 			if temp[y][x - 1] == 1 and temp[y][x + 1] == 1: # 양쪽 끝 보

def valid_pillar(temp, x, y):
	if y == 0:
		return True
	if temp[y - 1][x] == 0:
		return True
	if temp[y][x - 1] == 1:
		return True
	if temp[y][x] == 1:
		return True
	return False

def valid_plate(temp, x, y):
	if temp[y - 1][x] == 0:
		return True
	if temp[y - 1][x + 1] == 0:
		return True
	if temp[y][x - 1] == 1 and temp[y][x + 1] == 1:
		return True
	return False

INF = int(1e9)

def solution(n, build_frame):
	answer = []
	temp = [[INF] * (n + 1) for _ in range(n + 1)]

	for b in build_frame:
		x, y, a, b = b
		if b == 1:
			failFlag = True
			if a == 0:
				if [x, y, a] not in answer:
					temp[y][x] = 0
					answer.append([x, y, a])
					for temp_x, temp_y, temp_a in answer:
						if temp_a == 0 and not valid_pillar(temp, temp_x, temp_y):
							failFlag = False
						if temp_a == 1 and not valid_plate(temp, temp_x, temp_y):
							failFlag = False
					if not failFlag:
						answer.remove([x, y, a])
						temp[y][x] = INF
			else:
				if [x, y, a] not in answer:
					temp[y][x] = 1
					answer.append([x, y, a])
					for temp_x, temp_y, temp_a in answer:
						if temp_a == 0 and not valid_pillar(temp, temp_x, temp_y):
							failFlag = False
						if temp_a == 1 and not valid_plate(temp, temp_x, temp_y):
							failFlag = False
					if not failFlag:
						answer.remove([x, y, a])
						temp[y][x] = INF
		if b == 0:
			temp[y][x] = INF
			failFlag = True
			if a == 0:
				answer.remove([x, y, a])
				for temp_x, temp_y, temp_a in answer:
					if temp_a == 0 and not valid_pillar(temp, temp_x, temp_y):
						failFlag = False
					if temp_a == 1 and not valid_plate(temp, temp_x, temp_y):
						failFlag = False
				if not failFlag:
					answer.append([x, y, a])
					temp[y][x] = 0
			else:
				answer.remove([x, y, a])
				for temp_x, temp_y, temp_a in answer:
					if temp_a == 0 and not valid_pillar(temp, temp_x, temp_y):
						failFlag = False
					if temp_a == 1 and not valid_plate(temp, temp_x, temp_y):
						failFlag = False
				if not failFlag:
					answer.append([x, y, a])
					temp[y][x] = 1

	answer.sort(key=lambda x: (x[0], x[1], x[2]))
	return answer

solution(5, [[0,0,0,1],[2,0,0,1],[4,0,0,1],[0,1,1,1],[1,1,1,1],[2,1,1,1],[3,1,1,1],[2,0,0,0],[1,1,1,0],[2,2,0,1]])