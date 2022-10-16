from bisect import bisect_left, bisect_right

def resolve(lst, x):
	idx = bisect_left(lst, x)
	return idx

n = int(input())
lst = list(map(int, input().split()))

flag = True
for l in lst:
	if l == resolve(lst, l):
		print(l)
		flag = False
		break

if flag:
	print(-1)