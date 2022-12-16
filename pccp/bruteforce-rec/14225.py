import sys

input = sys.stdin.readline

n = int(input())
lst = list(map(int, input().split()))

sum = 1

lst.sort()

for l in lst:
    if l > sum:
        break
    sum += l
print(sum)