from bisect import bisect_left, bisect_right

n, x = map(int, input().split())
lst = list(map(int, input().split()))

start = bisect_left(lst, x)
end = bisect_right(lst, x)
if end - start == 0:
	print(-1)
else:
	print(end - start)