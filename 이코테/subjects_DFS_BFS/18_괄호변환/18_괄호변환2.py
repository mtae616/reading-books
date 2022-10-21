def check(p):
	stack = []

	i = 0
	while i < len(p):
		if p[i] == '(':
			stack.append('(')
		elif p[i] == ')':
			if len(stack) == 0:
				return False
			temp = stack.pop()
			if temp != '(':
				return False
		i += 1
	return True

def split(p):
	left_cnt = 0
	right_cnt = 0

	i = 0
	while i < len(p):
		if left_cnt != 0 and right_cnt != 0:
			if left_cnt == right_cnt:
				break
		if p[i] == '(':
			left_cnt += 1
		elif p[i] == ')':
			right_cnt += 1
		i += 1
	return p[:i], p[i:]

def solution(u):
	if not u:
		return ""
	temp = ""
	u, v = split(u)
	if check(u):
		return u + solution(v)
	elif not check(u):
		temp += "("
		temp += solution(v)
		temp += ")"
		for i in u[1:len(u) - 1]:
			if i == '(':
				temp += ')'
			else:
				temp += '('
	return temp


print(solution("()))((()"))