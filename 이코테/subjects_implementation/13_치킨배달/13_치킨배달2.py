from itertools import combinations

# 0 빈칸, 1 집, 2 치킨집
n, m = map(int, input().split())

lst = []
home_lst = []
chicken_lst = []

for i in range(n):
	lst.append(list(map(int, input().split())))
	for j in range(len(lst[i])):
		if lst[i][j] == 1:
			home_lst.append((i, j))
		elif lst[i][j] == 2:
			chicken_lst.append((i, j))
combi = list(combinations(chicken_lst, m))

real_answer = int(1e9)
for com in combi:
	sum = 0
	for ho in home_lst:
		l, k = ho
		ans = int(1e9)
		for ch in com:
			i, j = ch
			ans = min(ans, abs(i - l) + abs(j - k))
		sum += ans
	real_answer = min(real_answer, sum)
print(real_answer)