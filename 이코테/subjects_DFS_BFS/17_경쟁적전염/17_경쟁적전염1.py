# 바이러스 상 하 좌 우
# 번호가 낮은 종류의 바이러스부터 먼저 증식
# 바이러스가 있다면 다른 바이러스는 들어갈 수 없음
# s 초가 지난 후 (x, y) 의 바이러스 출력

from collections import deque

n, k = map(int, input().split())

lst = []
q = []

for i in range(n):
	lst.append(list(map(int, input().split())))
	for j in range(n):
		if lst[i][j] != 0:
			q.append((lst[i][j], 0, i, j))

s, x, y = map(int, input().split())
q.sort()
q = deque(q)

while q:
	val, t, x_pos, y_pos = q.popleft()
	if t == s:
		break
	if x_pos > 0 and lst[x_pos - 1][y_pos] == 0:
		lst[x_pos - 1][y_pos] = val
		q.append((val, t + 1, x_pos - 1, y_pos))
	if x_pos + 1 < n and lst[x_pos + 1][y_pos] == 0:
		lst[x_pos + 1][y_pos] = val
		q.append((val, t + 1, x_pos + 1, y_pos))
	if y_pos > 0 and lst[x_pos][y_pos - 1] == 0:
		lst[x_pos][y_pos - 1] = val
		q.append((val, t + 1, x_pos, y_pos - 1))
	if y_pos + 1 < n and lst[x_pos][y_pos + 1] == 0:
		lst[x_pos][y_pos + 1] = val
		q.append((val, t + 1, x_pos, y_pos + 1))

print(lst[x - 1][y - 1])
