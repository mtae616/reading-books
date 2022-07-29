# n 행, m 열
# 뽑고자 하는 카드의 행 선택
# 선택된 행의 가장 낮은 카드

n, m = map(int, input().split())
lst = list()
res = list()
for i in range(0, n):
	lst.append(list(map(int, input().split())))
	res.append(min(lst[i]))

print(max(res))