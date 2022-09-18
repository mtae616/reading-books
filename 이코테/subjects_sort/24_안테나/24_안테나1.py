# 한 개의 안테나
# 집의 수 N
# 집의 위치

n = int(input())
lst = list(map(int, input().split()))
lst.sort()
sum = 0
print(lst[(n - 1) // 2])