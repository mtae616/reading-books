n = int(input())
lst = list(map(int, input().split()))

lst.sort(reverse=True)

i = 0
cnt = 0
breakFlag = False
while i < len(lst):
	if breakFlag:
		break
	temp = lst[i]
	sum = 0
	while sum != lst[i]:
		if temp < lst[i]:
			breakFlag = True
			break
		sum += 1
		i += 1
	cnt += 1
	i += 1
print(cnt)