# N x N 도시
# 치킨 집 개수 M
# 빈 칸 0 , 치킨집 2 , 집 1

n, m = map(int, input().split())

lst = []
chicken_lst = []
houst_lst = []

for i in range(n):
	map_lst = list(map(int, input().split()))
	lst.append(map_lst)
	for j in range(len(map_lst)):
		if lst[i][j] == 2:
			chicken_lst.append((i, j))
		if lst[i][j] == 1:
			houst_lst.append((i, j))

for chicken in range(len(chicken_lst)):
	l = chicken_lst[chicken][0]
	k = chicken_lst[chicken][1]
	temp = 0
	for h in houst_lst:
		i, j = h
		temp += abs(i - l) + abs(j - k)
	chicken_lst[chicken] += (temp, )

chicken_lst.sort(key = lambda x:x[2])

while len(chicken_lst) > m:
	chicken_lst.pop()

sum = 0
for i in range(n):
	for j in range(n):
		if lst[i][j] == 1:
			min_val = int(1e9)
			for c in chicken_lst:
				l, k, dist = c
				min_val = min(abs(i - l) + abs(j - k), min_val)
			sum += min_val
print(sum)