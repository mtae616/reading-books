n, k = map(int, input().split())
lst1 = list(map(int, input().split()))
lst2 = list(map(int, input().split()))

lst1.sort()
lst2.sort(reverse=True)

for i in range(k):
	if lst1[i] < lst2[i]:
		lst1[i], lst2[i] = lst2[i], lst1[i]

print(lst1)