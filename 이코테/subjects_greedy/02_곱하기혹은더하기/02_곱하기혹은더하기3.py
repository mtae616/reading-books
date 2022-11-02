lst = list(map(int, input()))
sum = lst[0]
for i in range(1, len(lst)):
	if lst[i] == 0 or lst[i] == 1 or sum == 0 or sum == 1:
		sum += lst[i]
	else:
		sum *= lst[i]
print(sum)