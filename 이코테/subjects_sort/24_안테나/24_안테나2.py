n = int(input())
lst = list(map(int, input().split()))
lst.sort()
print(lst[(len(lst) - 1) // 2])