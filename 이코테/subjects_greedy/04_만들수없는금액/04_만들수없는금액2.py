n = int(input())
lst = list(map(int, input().split()))

lst.sort()

i = 1
for l in lst:
	if l > i:
		break
	i += l

print(i)