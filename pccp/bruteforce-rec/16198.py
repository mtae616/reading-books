n = int(input())
lst = list(map(int, input().split()))

ans = 0

def dfs(sum):
    global ans
    if len(lst) == 2:
        ans = max(ans, sum)

    for i in range(1, len(lst) - 1):
        eng = lst[i - 1] * lst[i + 1]
        temp = lst[i]
        del lst[i]
        dfs(sum + eng)
        lst.insert(i, temp)
dfs(0)

print(ans)