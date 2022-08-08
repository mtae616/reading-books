# 팀 합치기 연산
# 같은 팀 여부 확인 연산
# 학생 0 ~ n, 팀 -> n + 1
# 선생님 m 개의 연산 수행
# 0 -> 합친다.
# 1 -> 확인한다.

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
parent = [0 for i in range(n + 1)]

for i in range(n):
	parent[i] = i

for i in range(m):
	cmd, a, b = map(int, input().split())
	if cmd == 0:
		union(parent, a, b)
	elif cmd == 1:
		p_a = find_parent(parent, a)
		p_b = find_parent(parent, b)
		if p_a == p_b:
			print("YES")
		else:
			print("NO")
