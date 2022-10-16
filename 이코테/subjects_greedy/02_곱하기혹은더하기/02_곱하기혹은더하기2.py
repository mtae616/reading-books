sum = 1
for e in list(input()):
	e = int(e)
	if e == 0 or e == 1:
		sum += e
	else:
		sum *= e
print(sum)