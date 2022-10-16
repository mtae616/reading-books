from bisect import bisect_left, bisect_right

def find_words(words, a, z):
	start = bisect_left(words, a)
	end = bisect_right(words, z)
	return end - start

def solution(words, queries):
	ans = []
	temp = [[] for _ in range(100001)]
	temp_reversed = [[] for _ in range(100001)]
	for w in words:
		temp[len(w)].append(w)
		temp_reversed[len(w)].append(w[::-1])

	for i in range(100001):
		temp[i].sort()
		temp_reversed[i].sort()
	
	for q in queries:
		a = q.replace('?', 'a')
		z = q.replace('?', 'z')
		if q.startswith('?'):
			ans.append(find_words(temp_reversed[len(q)], a[::-1], z[::-1]))
		else:
			ans.append(find_words(temp[len(q)], a, z))
	return ans

solution(
	["frodo", "front", "frost", "frozen", "frame", "kakao"],
	["fro??", "????o", "fr???", "fro???", "pro?"]
)