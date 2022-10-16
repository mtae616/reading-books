# 삼각형 크기 N
# 아래로 내려오면서 대각선 왼쪽, 대각선 오른쪽
# 최대 합

n = int(input())
lst = []
for i in range(n):
	lst.append(list(map(int, input().split())))

for i in range(1, n):
	for j in range(i + 1):
		if j == 0:
			up_left = 0
		else:
			up_left = lst[i - 1][j - 1]
		if j == i:
			up = 0
		else:
			up = lst[i - 1][j]
		lst[i][j] = lst[i][j] + max(up_left, up)
print(max(lst[n - 1]))