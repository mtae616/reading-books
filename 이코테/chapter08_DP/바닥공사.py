# 가로 N 세로 2
# 1 x 2, 2 x 1, 2 x 2
# 모든 경우의 수

n = int(input())
d = [0] * 1001
d[1] = 1
d[2] = 3
for i in range(3, n + 1):
	d[i] = (d[i - 1] + 2 * d[i - 2])
print(d[n])
