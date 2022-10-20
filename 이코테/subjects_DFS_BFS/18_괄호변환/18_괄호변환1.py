def isValid(str):
	if len(str) == 0:
		return False
	stack = []
	i = 0
	answer = True
	while i < len(str):
		if str[i] == '(':
			stack.append(str[i])
		elif str[i] == ')':
			if len(stack) == 0:
				return False
			if stack.pop() != '(':
				return False
		i += 1
	return answer

def divide(p):
	start = 0
	end = 0
	for i in range(len(p)):
		if p[i] == '(':
			start += 1
		else:
			end += 1
		if start == end:
			return p[:i + 1], p[i + 1:]

def solution(p):
	if len(p) == 0:
		return ""
	answer = ''
	u, v = divide(p)
	if isValid(u):
		return u + solution(v)
	else:
		answer = '('
		answer += solution(v)
		answer += ')'
		for i in u[1:len(u) - 1]:
			if i == '(':
				answer += ')'
			else:
				answer += '('
	return answer
solution("(()())()")