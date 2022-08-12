# 캐릭터의 점수 N
# 점수 N을 반으로
# 왼쪽 부분의 각 자릿수의 합
# 오른쪽 부분의 각 자릿수의 합
# 동일하면 특정 조건

lst = list(map(int,input()))
mid = len(lst) // 2
if sum(lst[:mid]) == sum(lst[mid:]):
	print("LUCKY")
else:
	print("READY")
