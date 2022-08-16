# n 국 영 수
from traceback import print_last


n = int(input())

lst = list()

for i in range(n):
	name, kook, young, soo = input().split()
	lst.append([name, int(kook), int(young), int(soo)])

lst.sort(key = lambda x: (-x[1], -x[2], -x[3], x[0]))

print(lst)