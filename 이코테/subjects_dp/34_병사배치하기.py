# LIS
# N 명의 병사
# 특정한 전추력, 항상 뒤 쪽에 있는 병사보다 높다.
n = int(input())
lst = list(map(int, input().split()))
temp = [1 for i in range(n)]

for i in range(1, n):
	for j in range(0, i):
		if lst[j] > lst[i]:
			temp[i] = max(temp[i], temp[j] + 1)
print(n - max(temp))