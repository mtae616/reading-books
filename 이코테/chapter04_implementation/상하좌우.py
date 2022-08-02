# n x n 정사각형
# L R U D

n = int(input())
cmd = list(input().split())
x = 0
y = 0
for cor in cmd:
	if cor == 'R' and x + 1 <= n :
		x += 1
	elif cor == 'L' and x - 1 > 0:
		x -= 1
	elif cor == 'U' and y - 1 > 0:
		y = 1
	elif cor == 'D' and y + 1 <= n:
		y += 1
print(y + 1, x + 1)


# solution 2
# n = int(input())
# x, y = 1, 1
# cmd = input().split()

# dx = [0, 0, -1, 1]
# dy = [-1, 1, 0, 0]
# cmd_lst = ['L', 'R', 'U', 'D']

# for cor in cmd:
# 	for i in range(len(cmd_lst)):
# 		if cor == cmd_lst[i]:
# 			nx = x + dx[i]
# 			ny = y + dy[i]
# 	if nx < 1 or ny < 1 or nx > n or ny > n:
# 		continue
# 	x, y = nx, ny

# print(x, y)