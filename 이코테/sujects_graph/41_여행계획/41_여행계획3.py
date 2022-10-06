# 1 ~ N 여행지

def find_parent(parent, x):
	if parent[x] != x:
		parent[x] = find_parent(parent, parent[x])
	return parent[x]

def union_parent(parent, a, b):
	a = find_parent(parent, a)
	b = find_parent(parent, b)

	if b > a:
		parent[b] = a
	else:
		parent[a] = b

n, m = map(int, input().split())

parent = [i for i in range(n)]

lst = []
for i in range(n):
	lst.append(list(map(int, input().split())))
	for j in range(len(lst)):
		if lst[i][j] != 0:
			union_parent(parent, i, j)
flag = True
assert_lst = list(map(int, input().split()))
for i in range(1, m):
	if find_parent(parent, assert_lst[i - 1]) != find_parent(parent, assert_lst[i]):
		print('NO')
		flag = False
		break
if flag:
	print('YES')
