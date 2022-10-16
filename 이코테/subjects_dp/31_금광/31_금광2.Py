for i in range(int(input())):
    n, m = map(int, input().split())
    map_lst = [[0] * m for _ in range(n)]

    lst = list(map(int, input().split()))
    idx = 0
    for i in range(n):
        for j in range(m):
            map_lst[i][j] = lst[idx]
            idx += 1
    sum = 0
    for i in range(m):
        for j in range(n):
            left_up = 0
            left = 0
            left_down = 0
            if i > 0 and j > 0:
                left_up = map_lst[j - 1][i - 1]
            if i > 0:
                left = map_lst[j][i - 1]
            if j + 1 < n and i > 0:
                left_down = map_lst[j + 1][i - 1]
            map_lst[j][i] += max(left_up, left, left_down)
    max_val = 0
    for i in range(n):
        if map_lst[i][-1] > max_val:
            max_val = map_lst[i][-1]
    print(max_val)

