# https://www.acmicpc.net/problem/6603

buf = [0 for i in range(13)]

def dfs(start, iter):
    if iter == 6:
        for i in range(6):
            print(buf[i], end=' ')
        print()
        return
    for i in range(start, len(lst)):
        buf[iter] = lst[i]
        dfs(i + 1, iter + 1)

while 1:
    lst = list(map(int, input().split()))
    if lst[0] == 0:
        break
    del lst[0]
    dfs(0, 0)
    print()