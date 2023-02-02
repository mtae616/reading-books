def solution(input_string):
	answer = ''
	arr = [0] * 130

	str_len = len(input_string)
	i = 0
	while i < str_len:
		buf = input_string[i]
		if arr[ord(input_string[i])]:
			answer += input_string[i]
		arr[ord(input_string[i])] = 1
		while i + 1 < str_len and buf == input_string[i + 1]:
			i += 1
		i += 1
	if not answer:
		return 'N'
	ans = ''.join(sorted(set(list(answer))))
	return ans

print(solution("edeaaabbccd"))