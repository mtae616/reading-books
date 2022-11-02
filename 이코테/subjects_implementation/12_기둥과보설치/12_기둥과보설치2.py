# 0 은 기둥 1은 보
# 기둥은 바닥 위에 있거나 보의 한쪽 끝 부분이거나 기둥
# 보는 한쪽 끝이 기둥이거나 양쪽 끝이 다른 보

# build_frame
# [x, y, a, b]
# a : 0 기둥 1 보
# b : 0 삭제 1 설치

def check_pillar(x, y, ans):
	if y == 0:
		return True
	elif [x, y, 1] in ans:
		return True
	elif [x, y - 1, 0] in ans:
		return True
	elif [x - 1, y, 1] in ans:
		return True
	return False

def check_plate(x, y, ans):
	if [x + 1, y - 1, 0] in ans:
		return True
	elif [x, y - 1, 0] in ans:
		return True
	elif [x + 1, y, 1] in ans and [x - 1, y, 1] in ans:
		return True
	return False

def solution(n, build_frame):
	ans = []
	
	for x, y, a, b in build_frame:
		if b == 1:
			ans.append([x, y, a])
			for a_x, a_y, a_a in ans:
				if a_a == 0:
					if not check_pillar(a_x, a_y, ans):
						ans.remove([x, y, a])
						break
				if a_a == 1:
					if not check_plate(a_x, a_y, ans):
						ans.remove([x, y, a])
						break
		elif b == 0:
			ans.remove([x, y, a])
			for a_x, a_y, a_a in ans:
				if a_a == 0:
					if not check_pillar(a_x, a_y, ans):
						ans.append([x, y, a])
						break
				if a_a == 1:
					if not check_plate(a_x, a_y, ans):
						ans.append([x, y, a])
						break
	return sorted(ans)

solution(5, [[2, 0, 0, 1], [100, 0, 0, 1], [100, 1, 1, 1], [99, 1, 1, 1], [99, 1, 0, 1], [99, 0, 0, 1]])