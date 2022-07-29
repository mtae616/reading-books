# 8 x 8
# L자 형태
# 수평 2칸 -> 수직 1칸
# 수직 2칸 -> 수평 1칸
# 나이트의 위치, 이동할 수 있는 경우의 수
# 행 1 ~ 8
# 열 a ~ h

pos = list(input())
# y 수직, x 수평
# 열     행
cmd = [(1, 2), (1, -2), (-1, 2), (-1, -2), (2, 1), (2, -1), (-2, 1), (-2, -1)]
pos[0] = int(ord(pos[0])) - int(ord('a')) + 1
pos[1] = int(pos[1])
cnt = 0
for cor in cmd:
	if pos[0] + cor[0] > 0 and pos[0] + cor[0] <= 8 and pos[1] + cor[1] > 0 and pos[0] + cor[0] <= 8:
		cnt += 1
print(cnt)