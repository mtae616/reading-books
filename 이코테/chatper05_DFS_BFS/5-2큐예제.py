from collections import deque

# 큐 (Queue) 구현을 위해 deque 라이브러리 사용
que = deque()

que.append(5)
que.append(2)
que.append(3)
que.append(7)
que.popleft()
que.append(1)
que.append(4)
que.popleft()

print(que)
que.reverse()
print(list(que))