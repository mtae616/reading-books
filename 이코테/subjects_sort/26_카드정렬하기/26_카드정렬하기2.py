import heapq

q = []

for tc in range(int(input())):
	heapq.heappush(q, int(input()))

sum = 0
while q:
	if len(q) > 1:
		a = heapq.heappop(q)
		b = heapq.heappop(q)
		sum += (a + b)
		heapq.heappush(q, a + b)
	else:
		heapq.heappop(q)
print(sum)	
