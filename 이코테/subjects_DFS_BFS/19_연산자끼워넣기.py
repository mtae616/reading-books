# https://www.acmicpc.net/problem/14888
# 덧셈 뺼셈 곱셈 나눗셈
#   0   1   2   3

N = int(input())
n_lst = list(map(int, input().split()))
o_lst = list(map(int, input().split()))

visited = [0 for _ in range(4)]

max_val = -int(1e9)
min_val = int(1e9)

sum = n_lst[0]

def dfs(idx):
    global sum
    global max_val
    global min_val

    if idx == N:
        max_val = max(max_val, sum)
        min_val = min(min_val, sum)
        return

    for i in range(4):
        temp = sum
        if o_lst[i] > 0:
            if i == 0:
                sum += n_lst[idx]
            elif i == 1:
                sum -= n_lst[idx]
            elif i == 2:
                sum *= n_lst[idx]
            else:
                if sum >= 0:
                    sum //= n_lst[idx]
                else:
                    sum = -(-sum // n_lst[idx])
            o_lst[i] -= 1
            dfs(idx + 1)
            sum = temp
            o_lst[i] += 1
dfs(1)
print(max_val)
print(min_val)