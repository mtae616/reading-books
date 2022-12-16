n = int(input())
lst = list(map(int, input().split()))
cnt_lst = list(map(int, input().split()))

ans_max = int(-1e9)
ans_min = int(1e9)

def dfs(iter, sum):
    global ans_max, ans_min
    if iter == n:
        ans_min = min(ans_min, sum)
        ans_max = max(ans_max, sum)
        return
    if cnt_lst[0] > 0:
        cnt_lst[0] -= 1
        dfs(iter + 1, sum + lst[iter])
        cnt_lst[0] += 1
    if cnt_lst[1] > 0:
        cnt_lst[1] -= 1
        dfs(iter + 1, sum - lst[iter])
        cnt_lst[1] += 1
    if cnt_lst[2] > 0:
        cnt_lst[2] -= 1
        dfs(iter + 1, sum * lst[iter])
        cnt_lst[2] += 1
    if cnt_lst[3] > 0:
        cnt_lst[3] -= 1
        dfs(iter + 1, int(sum / lst[iter]))
        cnt_lst[3] += 1

dfs(1, lst[0])

print(ans_max)
print(ans_min)
