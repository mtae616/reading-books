from bisect import bisect_left, bisect_right

def find_idx(array, target):
	idx_l = bisect_left(array, target)
	idx_r = bisect_right(array, target)
	if idx_r - idx_l == 0:
		print(-1)
	else:
		print(idx_r - idx_l)

n, x = map(int, input().split())
lst = list(map(int, input().split()))

find_idx(lst, x)

left = 0
right = n - 1
idx = -1
while left <= right:
	mid = (left + right) // 2
	if lst[mid] == x:
		idx = mid
		break
	elif lst[mid] > x:
		right = mid - 1
	else:
		left = mid + 1

if idx != -1:
	cnt = 1
	while lst[idx] == x:
		cnt += 1
		idx += 1
	print(cnt)
else:
	print(idx)
