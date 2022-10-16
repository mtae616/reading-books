a = list(input())

i = 0
zero_cnt = 0
one_cnt = 0
while i < len(a) and a[i]:
	if a[i] == '0':
		zero_cnt += 1
		while i < len(a) and a[i] == '0':
			i += 1
	elif a[i] == '1':
		one_cnt += 1
		while i < len(a) and a[i] == '1':
			i += 1

print(min(zero_cnt, one_cnt))