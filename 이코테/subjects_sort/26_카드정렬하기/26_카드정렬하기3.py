import heapq


q = []

for tc in range(int(input())):
	heapq.heappush(q, int(input()))

sum = 0
while len(q) > 1:
	a = heapq.heappop(q)
	b = heapq.heappop(q)
	temp = a + b
	sum += temp
	heapq.heappush(q, temp)
print(sum)