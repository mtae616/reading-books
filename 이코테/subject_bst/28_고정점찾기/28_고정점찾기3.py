n = int(input())
lst = list(map(int, input().split()))

i = 0
left = 0
right = n - 1
flag = True
while left <= right:
	mid = (left + right) // 2
	if mid == lst[mid]:
		print(mid)
		flag = False
		break
	if mid > lst[mid]:
		left = mid + 1
	else:
		right = mid - 1
if flag:
	print(-1)