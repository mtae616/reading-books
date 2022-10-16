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

n = int(input())
g = int(input())

parent = [i for i in range(n + 1)]

sum = 0
for i in range(g):
	a = int(input())
	par = find_parent(parent, a)
	if par != 0:
		union_parent(parent, a, par - 1)
		sum += 1
	else:
		break
print(sum)
	
