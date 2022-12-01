# 기본 요금 + (단위 시간 * 단위 요금)
# 입차 후 출차 없으면 -> 23:59 출차
# 00:00 ~ 23:59 누적 주차
# 기본 시간 이하라면 기본 요금
# 기본 시간 초과 -> 기본요금 + 초과 시간 단위 요금
import datetime
import math

from collections import deque
import math

def solution(fees, records):
    answer = []
    temp = []
    my_dict = {}
    for r in records:
        time, car_num, status = r.split(' ')
        temp.append((time, car_num, status))
        my_dict[car_num] = 0
    temp.sort(key=lambda x: x[1])
    temp = deque(temp)

    for i in range(len(records)):
        if temp:
            t, c, s = temp.popleft()
            H1, M1 = t.split(':')
            if s == 'IN':
                if temp and temp[0][2] == 'OUT':
                    H2, M2 = temp[0][0].split(':')
                    my_dict[c] += (int(H2) - int(H1)) * 60 + (int(M2) - int(M1))
                    temp.popleft()
                else:
                    my_dict[c] += (23 - int(H1)) * 60 + (59 - int(M1))

    res = sorted(my_dict.items())
    for r in res:
        if r[1] <= fees[0]:
            answer.append(fees[1])
        else:
            answer.append(fees[1] + math.ceil((r[1] - fees[0]) / fees[2]) * fees[3])
    return answer