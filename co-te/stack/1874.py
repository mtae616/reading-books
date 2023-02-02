# https://www.acmicpc.net/problem/1874

from collections import deque

stack = []
ans = []
lst = deque()
n = int(input())

flag = 0
current = 1
for i in range(n):
    num = int(input())
    while current <= num:
        stack.append(current)
        ans.append("+")
        current += 1

    if stack[-1] == num:
        stack.pop()
        ans.append("-")
    else:
        print("NO")
        flag = 1
        break
if flag == 0:
    for a in ans:
        print(a)

