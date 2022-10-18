from xml.sax.handler import property_interning_dict


n, c = map(int, input().split())

lst = []

for i in range(n):
	lst.append(int(input()))

lst.sort()

left = lst[0]
right = lst[-1]

ans = int(1e9)

ans = 0
left = 1
right = lst[-1] - lst[0]
while left <= right:
	mid = (left + right) // 2
	cnt = 1
	curr = lst[0]
	for i in range(1, len(lst)):
		if lst[i] >= curr + mid:
			curr = lst[i]
			cnt += 1
	if cnt >= c:
		left = mid + 1
		ans = mid
	else:
		right = mid - 1
print(ans)
