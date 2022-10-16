# https://www.acmicpc.net/problem/10825
lst = []

for i in range(int(input())):
    name, ko, en, ma = input().split()
    lst.append((name, int(ko), int(en), int(ma)))

lst.sort(key=lambda x: (-x[1], x[2], -x[3], x[0]))
for l in lst:
    print(l[0])