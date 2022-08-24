# 고정점 : 값이 인덱스와 동일한 원소
# N 원소, 오름차순 정렬
# 고정점 -> 출력

n = int(input())

lst = list(map(int, input().split()))

left = 0
right = n - 1

flag = 1
while left <= right:
	mid = (left + right) // 2
	if lst[mid] == mid:
		print(lst[mid])
		flag = 0
		break
	if lst[mid] < mid:
		left = mid + 1
	else:
		right = mid - 1
if flag:
	print(-1)