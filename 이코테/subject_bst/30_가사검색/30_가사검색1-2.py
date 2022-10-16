def forward(q):
	left = 0
	right = len(q) - 1
	while left <= right:
		mid = (left + right) // 2
		if q[mid] == '?' and q[mid - 1] != '?':
			return mid
		elif '?' in q[:mid]:
			right = mid - 1
		else:
			left = mid + 1

def reverse(q):
	left = 0
	right = len(q) - 1
	while left <= right:
		mid = (left + right) // 2
		if q[mid] == '?' and q[mid + 1] != '?':
			return mid
		elif '?' in q[mid + 1]:
			left = mid + 1
		else:
			right = mid - 1

def solution(words, queries):
	answer = []
	wordsLenList = [[] for _ in range(10001)]
	for word in words:
		wordLen = len(word)
		wordsLenList[wordLen].append(word)
	for i in range(10001):
		wordsLenList[i].sort()
	for q in queries:
		qLen = len(q)
		cnt = 0
		if q[-1] == '?':
			endIdx = forward(q)
			for word in wordsLenList[qLen]:
				if word[:endIdx] == q[:endIdx]:
					cnt += 1
		else:
			startIdx = reverse(q)
			for word in wordsLenList[qLen]:
				if word[startIdx + 1 :] == q[startIdx + 1 :]:
					cnt += 1
		answer.append(cnt)
	return answer

solution(["frodo", "front", "frost", "frozen", "frame", "kakao"]
			, ["fro??", "????o", "fr???", "fro???", "pro?"])