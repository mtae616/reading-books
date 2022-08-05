# M번 더하여 가장 큰 수
# K 번을 초과하여 더해질 수는 없음

res = 0
n, m, k = map(int, input().split())
lst = list(map(int, input().split()))
lst.sort(reverse=True)

j = 0
cnt = 0
i = 0
while i < m:
	res += lst[0]
	cnt += 1
	if cnt == k:
		cnt = 0
		res += lst[1]
		i += 1
	i += 1
print(res)