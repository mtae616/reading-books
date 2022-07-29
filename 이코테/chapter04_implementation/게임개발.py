# n x m 직사각형
# 캐릭터 동, 서, 남, 북
# 방향 전환 왼 아래 오른 위
# 가보지 않은 칸이 있다면 전진, 없다면 후진
# 뒤로 갈 수 없다면 종료
# 북 0 동 1 남 2 서 3 

n, m = map(int, input().split())
y, x, dir = map(int, input().split())

move_dir = [(0, -1), (-1, 0), (0, 1), (1, 0)]

game_map = list()
for i in range(n):
	game_map.append(list(map(int, input().split())))

finish = 0
cnt = 1
visited = list()
while not finish:
	temp_y = y
	temp_x = x
	for i in range(4):
		ny = y + move_dir[i][0]
		nx = x + move_dir[i][1]
		if (ny, nx) not in visited and game_map[ny][nx] != 1:
			visited.append((ny, nx))
			game_map[ny][nx] = 1
			y = ny
			x = nx
			cnt += 1
			break
	if y == temp_y and x == temp_x:
		if game_map[y - 1][x] == 1:
			finish = 1
		else:
			y = -1
print(cnt)