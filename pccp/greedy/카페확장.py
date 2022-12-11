# 0초에 손님 한 명
# 정확히 k 초마다 새로운 손님
# 주문 받은 순서대로 -> 한 번에 한 개
# 손님은 받으면 바로 나가고, 다 만들면 다음 음료, 모든 음료는 만드는 시간 동일
# 동시에 최대 몇명
# 먼저 퇴장 -> 입장
# 0 : 5, 1 : 12, 2 : 30

from collections import deque

def solution(menu, order, k):
    answer = 1

    q = deque()
    q.append((0, order[0]))
    t = 0
    idx = 0
    while idx < len(order):
        if not q:
            start, m = t, order[idx]
        else:
            start, m = q[0]
        if t - start == menu[m]:
            q.popleft()
        if idx + 1 < len(order) and t == (idx + 1) * k:
            idx += 1
            if not q:
                buf = t + menu[order[idx]]
            else:
                buf = q[-1][0] + menu[q[-1][1]]
            q.append((buf, order[idx]))
        answer = max(answer, len(q))
        t += 1
    return answer

solution([5, 12, 30], [1, 2, 0, 1]	, 10)