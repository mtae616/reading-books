# N 도시
# x -> y, 통로?
# x -> y 통로 O, 통신 O
# y -> x 통로 X, 통신 X
# 도시 개수 n, 통로 개수 m, 보내고자 하는 도시 c
INF = int(1e9)

n, m, c = map(int, input().split())
distance = [[INF] * (n + 1) for _ in range(n + 1)]
# # x y 통로  z 메세지 전달 시간
for i in range(m):
	x, y, z = map(int, input().split())
	distance[x][y] = z

cnt = 0
max_val = 0
# for i in range(1, n + 1):
# 	for j in range(1, n + 1):
# 		if distance[i][j] != INF:
# 			cnt += 1
# 			max_val = max(distance[i][j], max)

for check in distance[c]:
	if check != INF:
		cnt += 1
		max_val = max(check, max_val)


print(cnt, max_val)