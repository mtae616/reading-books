# 1 ~ G 개의 탑승구
# P 개 비행기 차례로
# 비행기 최대 몇개

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

g = int(input())
p = int(input())

parent = [i for i in range(g + 1)]

sum = 0
for i in range(p):
	n = find_parent(parent, int(input()))
	if n == 0:
		break
	union_parent(parent, n, n - 1)
	sum += 1
print(sum)
	


