n, x = map(int, input().split())
lst = list(map(int, input().split()))

cnt = 0
left = 0
right = len(lst) - 1
idx = 1
while left <= right:
	mid = (left + right) // 2
	if lst[mid] == x:
		idx = mid
		break
	elif lst[mid] > x:
		right = mid - 1
	elif lst[mid] < x:
		left = mid + 1

while x == lst[idx]:
	cnt += 1
	idx += 1

if cnt == 0:
	print(-1)
else:
	print(cnt)