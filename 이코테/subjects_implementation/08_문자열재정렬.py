lst = list(input())
lst.sort()
sum = 0
idx = 0
for i in range(len(lst)):
	if '0' <= lst[i] and lst[i] <= '9':
		sum += int(lst[i])
		idx = i

for i in range(idx + 1):
	lst.pop(0)

string = "".join(lst)
string += str(sum)

print(string)