def find_parent(parent, x):
	if x != parent[x]:
		parent[x] = find_parent(parent, parent[x])
	return parent[x]

def union_parent(parent, a, b):
	a = find_parent(parent, a)
	b = find_parent(parent, b)

	if a < b:
		parent[b] = a
	else:
		parent[a] = b

n, m = map(int, input().split())

parent = [i for i in range(n)]

n_lst = []
for i in range(n):
	n_lst.append(list(map(int, input().split())))
	for j in range(len(n_lst[i])):
		if n_lst[i][j]:
			union_parent(parent, i, j)

print(parent)

m_lst = list(map(int, input().split()))

flag = 1
for i in range(1, len(m_lst)):
	if find_parent(parent, i) != find_parent(parent, i - 1):
		print('NO')
		flag = 0
		break

if flag:
	print('YES')
	