# RR = RR
# rr = rr
# RR + rr = Rr
# Rr = RR, Rr, Rr, rr

def impl(n, p):
	stack = []

	p -= 1
	while n > 1:
		stack.append(p%4)
		n -= 1
		p //= 4

	while len(stack) > 0:
		num = stack.pop()
		if num == 0: return "RR"
		if num == 3: return "rr"
	return "Rr"
	
def solution(queries):
	ans = []
	for q in queries:
		ans.append(impl(q[0], q[1]))
	return ans

print(solution([[4, 26]]))