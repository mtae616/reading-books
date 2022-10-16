# 부품 N, 종류 M
# M 개 종류 모두 확인해서 견적서

n = int(input())
n_list = list(map(int, input().split()))
n_list.sort()

m = int(input())
m_list = list(map(int, input().split()))
m_list.sort()

for m_el in m_list:
	start = 0
	end = n - 1
	flag = 0
	while start <= end:
		mid = (start + end) // 2
		if n_list[mid] == m_el:
			flag = 1
			break
		if n_list[mid] > m_el:
			end = mid - 1
		else:
			start = mid + 1
	if flag:
		print("yes", end=' ')
	else:
		print("no", end=' ')