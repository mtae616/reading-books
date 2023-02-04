eg = input()

ans = 26 if eg[0] == 'c' else 10

for i in range(1, len(eg)):
    if eg[i] == 'c' and eg[i - 1] != 'c':
        ans *= 26
    elif eg[i] == 'c':
        ans *= 25
    elif eg[i] == 'd' and eg[i - 1] != 'd':
        ans *= 10
    else:
        ans *= 9
print(ans)