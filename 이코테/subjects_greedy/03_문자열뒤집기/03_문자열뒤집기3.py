lst = list(map(int, input()))
cnt_lst = [0, 0]
buf = lst[0]
cnt_lst[buf] += 1
for l in lst:
	if buf != l:
		buf = l
		cnt_lst[buf] += 1
print(min(cnt_lst[0], cnt_lst[1]))