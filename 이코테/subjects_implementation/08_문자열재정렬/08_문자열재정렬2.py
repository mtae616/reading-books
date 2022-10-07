lst = list(input())
chars = ""
nums = 0
for l in lst:
	if '0' <= l <= '9':
		nums += int(l)
	else:
		chars += l

chars = list(chars)
chars.sort()

print("".join(chars) + str(nums))