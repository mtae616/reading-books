# N -> 00시 00분 00초 ~ N시 59분 59초, 3이 하나라도 포함되는 모든 경우의 수
# 5 -> 00시 00분 00초 ~ 5시 59분 59초

n = int(input())

cnt = 0
i = 0
while i <= n:
	j = 0
	while j < 60:
		j += 1
		k = 0
		while k < 60:
			if '3' in str(k) or '3' in str(j) or '3' in str(i):
			# if '3' in str(i) + str(j) + str(k):
				cnt += 1
			k += 1
	i += 1

print(cnt)