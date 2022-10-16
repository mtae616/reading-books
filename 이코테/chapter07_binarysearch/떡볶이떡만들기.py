# 떡 길리 맞춘다.
# H 기준, H 보다 크면 H 위 부분 잘린다.
# e.g. 19, 14, 10, 17 -> pivot : 15, -> 15(-4), 14, 10, 15(-2) -> 갑자기 손님이 6 가져감...
# n 개수, m 길이

n, m = map(int, input().split())
lst = list(map(int, input().split()))

lst_avg = sum(lst) // n

while 1:
	res = 0
	for l in lst:
		if l > lst_avg:
			res += (l - lst_avg)
	if m <= res:
		break
	else:
		lst_avg -= 1

print(lst_avg)
