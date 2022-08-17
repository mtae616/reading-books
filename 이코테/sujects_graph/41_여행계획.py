# n 개의 여행지
# 1 ~ N
# 도로 존재, 양방향 이동
from collections import deque

n, m = map(int, input().split())
lst = list()
for i in range(n):
	lst.append(list(map(int, input().split())))

cmd_lst = list(map(int, input().split()))

for i in range(n):
	for j in range(n):
		if lst[i][j] == 1:
			for k in range(n):
				if lst[j][k]:
					if lst[i][k] == 0 and i != k:
						 lst[i][k] = lst[j][k] + 1
flag = 1
for i in range(len(cmd_lst) - 1):
	if lst[cmd_lst[i] - 1][cmd_lst[i + 1] - 1] == 0:
		flag = 0
if flag == 0:
	print('NO')
else:
	print('YES')

# 책
def find_parent(parent, x):
	if parent[x] != x:
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
parent = [0] * (n + 1)

for i in range(1, n + 1):
	parent[i] = i

for i in range(n):
	data = list(map(int, input().split()))
	for j in range(n):
		if data[j] == 1:
			union_parent(parent, i + 1, j + 1)

plan = list(map(int, input().split()))
result = True
for i in range(m - 1):
	if find_parent(parent, plan[i]) != find_parent(parent, plan[i + 1]):
		result = False

if result:
	print("YES")
else:
	print("NO")