lst = list(map(int, input()))
lst.sort(reverse=True)
sum = lst[0]
for i in range(1, len(lst) - 1):
	if lst[i] != 0 or lst[i] != 1:
		sum *= lst[i]
	if lst[i] == 1:
		sum += 1
if lst[-1] == 0:
	print(sum)
else:
	print(sum * lst[-1])