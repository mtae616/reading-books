lst = []
for tc in range(int(input())):
	a = list(input().split())
	lst.append((a[0], int(a[1]), int(a[2]), int(a[3])))
lst.sort(key= lambda x:(-x[1], x[2], -x[3], x[0]))

for l in lst:
	print(l[0])