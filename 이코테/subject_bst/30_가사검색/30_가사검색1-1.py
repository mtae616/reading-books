def solution(words, queries):
	answer = []
	wordsLenList = [[] for _ in range(10001)]
	words.sort(key=lambda x:len(x))
	for word in words:
		wordLen = len(word)
		wordsLenList[wordLen].append(word)
	for q in queries:
		qLen = len(q)
		start_idx = 0
		end_idx = 0
		i = 0
		while q[i] == '?':
			i += 1
			start_idx += 1
		while i < len(q):
			if q[i] != '?':
				end_idx = i
			i += 1
		end_idx += 1

		temp = q.split('?')
		for t in temp:
			if t != '':
				temp = t
		cnt = 0
		for word in wordsLenList[qLen]:
			if temp == word[start_idx:end_idx]:
				cnt += 1
		answer.append(cnt)
	return answer

solution(["frodo", "front", "frost", "frozen", "frame", "kakao"]
			, ["fro??", "????o", "fr???", "fro???", "pro?"])