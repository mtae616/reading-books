# aabbaccc
# 2a2ba3c

def solution(s):
	answer = int(1e9)

	for i in range(1, len(s) + 1):
		temp = ""
		prev = ""
		cnt = 1
		j = 0
		while j < len(s):
			if prev == s[j:j + i]:
				cnt += 1
			else:
				if cnt == 1:
					temp += prev
				else:
					temp += str(cnt)
					temp += prev
				cnt = 1
			prev = s[j:j + i]
			
			j += i
		if cnt == 1:
			temp += prev
		else:
			temp += str(cnt)
			temp += prev
		answer = min(len(temp), answer)
	print(answer)
	return answer

solution("xababcdcdababcdcd")