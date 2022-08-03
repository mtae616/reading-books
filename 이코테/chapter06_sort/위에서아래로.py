n = int(input())
lst = [int(input()) for _ in range(n)]
print(sorted(lst, reverse=True))
# print(lst.sort(reverse=True)) 얘는 왜 안되지??