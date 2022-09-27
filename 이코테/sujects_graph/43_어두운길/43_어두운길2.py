import heapq


def find_parent(parent, x):
	if x != parent[x]:
		parent[x] = find_parent(parent, parent[x])
	return parent[x]

def union_parent(parent, a, b):
	a = find_parent(parent, a)
	b = find_parent(parent, b)

	if b > a:
		parent[b] = a
	else:
		parent[a] = b

n, m = map(int,input().split())

parent = [i for i in range(n)]

total = 0
q = []
for i in range(m):
	a, b, cost = map(int, input().split())
	heapq.heappush(q, (cost, a, b))
	total += cost 
sum = 0
for i in range(len(q)):
	cost, a, b = heapq.heappop(q)
	if find_parent(parent, a) != find_parent(parent, b):
		union_parent(parent, a, b)
		sum += cost
print(total - sum)
