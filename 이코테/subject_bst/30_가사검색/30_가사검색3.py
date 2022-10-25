from bisect import bisect_left, bisect_right

def find_words(lst, q_a, q_z):
	start = bisect_left(lst, q_a)
	end = bisect_right(lst, q_z)

	return end - start

def solution(words, queries):
	ans = []
	temp = [[] for _ in range(100001)]
	temp_reverse = [[] for _ in range(100001)]
	for w in words:
		temp[len(w)].append(w)
		temp_reverse[len(w)].append(w[::-1])
	for i in range(100001):
		temp[i].sort()
		temp_reverse[i].sort()

	for q in queries:
		q_a = q.replace('?', 'a')
		q_z = q.replace('?', 'z')
		if q.startswith('?'):
			ans.append(find_words(temp_reverse[len(q)], q_a[::-1], q_z[::-1]))
		else:
			ans.append(find_words(temp[len(q)], q_a, q_z))
	return ans

solution(["frodo", "front", "frost", "frozen", "frame", "kakao"],
		["fro??", "????o", "fr???", "fro???", "pro?"])