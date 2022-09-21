 # 모험가 N
 # 공포도 x -> x명 이상으로 구성
n = int(input())

lst = list(map(int, input().split()))
lst.sort()
max_val = max(lst)
res = 0
while lst:
	if len(lst) < max_val:
		break
	elif len(lst) == max_val:
		res += 1
		break
	elif len(lst) > max_val:
		res += 1
		for i in range(max_val):
			lst.pop()
		if lst:
			max_val = max(lst)

print(res)


# 책
# n = int(input())

# lst = list(map(int, input().split()))
# lst.sort()
# result = 0
# count = 0
# for i in lst:
# 	count += 1
# 	if count >= i:
# 		result += 1
# 		count = 0

# print(result)