def solution(s):
	answer = len(s)
	for i in range(1, len(s) // 2 + 1):
		b = ''
		cnt = 1
		tmp = s[:i]
		for j in range(i, len(s), i):
			if tmp == s[j:i+j]:
				cnt += 1
			else:
				b += str(cnt) + tmp if cnt >= 2 else tmp
				tmp = s[j:j+i]
				cnt = 1
		b += str(cnt) + tmp if cnt >= 2 else tmp
		answer = min(answer, len(b))
	return answer