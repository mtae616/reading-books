# N 개 동전
# 만들 수 없는 양의 정수 중 최솟값

n = int(input())
lst = list(map(int, input().split()))
result = [0 for i in range(1, 1000000)]
for l in lst:
	result[l] = 1
	for j in lst:
		result[l + j] = 1
for i in range(n + 1):
	result[sum(lst[:i])] = 1

for i in range(1, len(result)):
	if result[i] == 0:
		print(i)
		break

# 책
n = int(input())
data = list(map(int, input().split()))
data.sort()
target = 1
for x in data:
	if target < x:
		break
	target += x
print(target)