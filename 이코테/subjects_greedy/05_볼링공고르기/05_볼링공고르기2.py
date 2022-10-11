import itertools

n, m = map(int,input().split())
lst = list(map(int, input().split()))
ans = []
for i in range(len(lst)):
	for j in range(i, len(lst)):
		if lst[i] != lst[j]:
			ans.append((lst[i], lst[j]))
print(len(ans))

