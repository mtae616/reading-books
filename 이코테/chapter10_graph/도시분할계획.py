# n 집, m 길

def find_parent(parent, x):
	if parent[x] != x:
		parent[x] = find_parent(parent, parent[x])
	return parent[x]

def union(parent, a, b):
	a = find_parent(parent, a)
	b = find_parent(parent, b)
	if a < b:
		parent[b] = a
	else:
		parent[a] = b

n, m = map(int, input().split())

edges = []
result = 0
parent = [0 for i in range(n + 1)]
for i in range(n + 1):
	parent[i] = i

for i in range(m):
	a, b, cost = map(int, input().split())
	edges.append((cost, a, b))

edges.sort()

last = 0
for edge in edges:
	cost, a, b = edge
	if find_parent(parent, a) != find_parent(parent, b):
		union(parent, a, b)
		result += cost
		last = cost

print(result - last)