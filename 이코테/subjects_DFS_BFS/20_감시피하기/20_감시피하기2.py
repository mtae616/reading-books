n = int(input())

dx = [1, 0, -1, 0]
dy = [0, 1, 0, -1]

map_lst = []
teacher_lst = []
for i in range(n):
	map_lst.append(list(input().split()))
	for j in range(n):
		if map_lst[i][j] == 'T':
			teacher_lst.append((i, j))
ans = False

def check():
	for t in teacher_lst:
		d_flag = False
		u_flag = False
		l_flag = False
		r_flag = False
		for k in range(n):
			d = t[0] + k
			u = t[0] - k
			l = t[1] - k
			r = t[1] + k
			if d < n:
				if map_lst[d][t[1]] == 'O':
					d_flag = True
				elif not d_flag and map_lst[d][t[1]] == 'S':
					return False
			if 0 <= u:
				if map_lst[u][t[1]] == 'O':
					u_flag = True
				elif not u_flag and map_lst[u][t[1]] == 'S':
					return False
			if 0 <= l:
				if map_lst[t[0]][l] == 'O':
					l_flag = True
				elif not l_flag and map_lst[t[0]][l] == 'S':
					return False
			if r < n:
				if map_lst[t[0]][r] == 'O':
					r_flag = True
				elif not r_flag and map_lst[t[0]][r] == 'S':
					return False
	return True
check()

def dfs(iter):
	global ans
	if iter == 3:
		if check():
			ans = True
		return
	for i in range(n):
		for j in range(n):
			if map_lst[i][j] == 'X':
				map_lst[i][j] = 'O'
				dfs(iter + 1)
				map_lst[i][j] = 'X'
dfs(0)

if ans:
	print('YES')
else:
	print('NO')