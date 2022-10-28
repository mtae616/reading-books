# from collections import deque
# n = int(input())
# lst = list(map(int, input().split()))
#
# lst.sort(reverse=True)
#
# lst = deque(lst)
# count = 0
# while lst:
#     many = lst[0]
#     for i in range(many):
#         lst.popleft()
#     count += 1
# print(count)

n = int(input())

lst = list(map(int, input().split()))
lst.sort()
result = 0
count = 0
for i in lst:
	count += 1
	if count >= i:
		result += 1
		count = 0

print(result)