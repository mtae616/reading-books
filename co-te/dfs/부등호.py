visited = [0] * 10

ans_lst = []

def dfs(iter, buf):
    if iter == n + 1:
        flag = True
        for i in range(len(lst)):
            if lst[i] == '<':
                if not buf[i] < buf[i + 1]:
                    flag = False
            elif lst[i] == '>':
                if not buf[i] > buf[i + 1]:
                    flag = False
        if flag:
            ans_lst.append("".join(map(str, buf)))
        return
    for i in range(10):
        if not visited[i]:
            visited[i] = 1
            buf.append(i)
            dfs(iter + 1, buf)
            buf.pop()
            visited[i] = 0

n = int(input())
lst = list(input().split())

dfs(0, [])

print(ans_lst[-1])
print(ans_lst[0])
