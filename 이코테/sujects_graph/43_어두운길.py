# N 개의 집(0 ~ n - 1), M 도로
# 특정한 도로의 가로등을 켜기 위한 비용, 해당 도로의 길이와 동일
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

graph = []
parent = [i for i in range(n)]

sum = 0

for i in range(m):
	a, b, dist = map(int, input().split())
	graph.append((dist, a, b))
graph.sort()
total = 0

sum = 0
for g in graph:
	dist, x, y = g
	total += dist
	if find_parent(parent, x) != find_parent(parent, y):
		union_parent(parent, x, y)
		print((x, y))
		sum += dist
print(total - sum)
	
	
