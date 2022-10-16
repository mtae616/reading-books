from itertools import combinations

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
comb_chicken = list(combinations(chicken_lst, m))

def getSum(comb):
	result = 0
	for hx, hy in houst_lst:
		temp = int(1e9)
		for cx, cy in comb:
			temp = min(temp, abs(hx - cx) + abs(hy - cy))
		result += temp
	return result

result = int(1e9)
for comb in comb_chicken:
	result = min(result, getSum(comb))
print(result)
