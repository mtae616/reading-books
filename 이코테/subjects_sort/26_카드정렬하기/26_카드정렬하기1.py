# A B
# 두 묶음 합쳐 하나로 -> A + B
import heapq
import sys

input = sys.stdin.readline

n = int(input())
lst = []

for i in range(n):
	heapq.heappush(lst, int(input()))

sum_val = 0
while len(lst) > 1:
	temp = 0
	if len(lst) > 2:
		a = heapq.heappop(lst)
		b = heapq.heappop(lst)
		temp = a + b
		sum_val += temp
		heapq.heappush(lst, temp)
	elif len(lst) == 2:
		a = heapq.heappop(lst)
		b = heapq.heappop(lst)
		temp = a + b
		sum_val += temp
	else:
		temp = heapq.heappop(lst)
		sum_val += temp
print(sum_val)
