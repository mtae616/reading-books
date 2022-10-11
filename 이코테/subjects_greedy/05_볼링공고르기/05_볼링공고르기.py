# 볼링공 개수 N 최대 무게 M

n, m = map(int, input().split())
n_lst = list(map(int, input().split()))

cnt = 0
for i in range(n):
	for j in range(i + 1, n):
		if n_lst[i] != n_lst[j]:
			cnt += 1	
print(cnt)
