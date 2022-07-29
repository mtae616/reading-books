 # n 이 1이 될 때까지
 # n 에서 1을 뺀다, n을 k 로 나눈다.
n, k = map(int, input().split())
res = 0
while n != 1:
	if n % k == 0:
		n //= k
	else:
		n -= 1
	res += 1
print(res)