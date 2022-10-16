n = list(input())
z_cnt = 0
one_cnt = 0

i = 0
while i < len(n):
	if n[i] == '0':
		z_cnt += 1
		while i < len(n) and n[i] == '0':
			i += 1
	elif n[i] == '1':
		one_cnt += 1
		while i < len(n) and  n[i] == '1':
			i += 1
print(min(z_cnt, one_cnt))
