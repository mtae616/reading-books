n = int(input())


lst = list()
for i in range(n):
	name, score = input().split()
	lst.append((name, int(score)))

def setting(data):
	return data[1]

result = sorted(lst, key=setting)
for student in result:
	print(student[0])