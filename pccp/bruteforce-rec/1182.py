import sys

input = sys.stdin.readline

n, s = map(int, input().split())
lst = list(map(int, input().split()))

visited = [0] * len(lst)

ans = 0
# ans_lst = []
# def dfs(iter):
#     global ans
#
#     if iter != 0:
#         sum = 0
#         for k in range(len(visited)):
#             if visited[k]:
#                 sum += lst[k]
#         if sum == s and not visited in ans_lst:
#             ans += 1
#             ans_lst.append(visited)
#     for i in range(len(lst)):
#         if not visited[i]:
#             visited[i] = 1
#             dfs(iter + 1)
#             visited[i] = 0

def dfs(idx, res):
    global ans
    if idx >= n:
        return
    res += lst[idx]
    if res == s:
        ans += 1
    dfs(idx + 1, res)
    dfs(idx + 1, res - lst[idx])


dfs(0, 0)

print(ans)