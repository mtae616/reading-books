def solution(command):
    dx = [1, 0, -1, 0]
    dy = [0, 1, 0, -1]

    x, y, idx, i = 0, 0, 0, 0
    while i < len(command):
        if command[i] == 'G':
            x += dx[idx]
            y += dy[idx]
        elif command[i] == 'B':
            x -= dx[idx]
            y -= dy[idx]
        elif command[i] == 'R':
            idx += 1
            if idx > 3:
                idx = 0
        elif command[i] == 'L':
            idx -= 1
            if idx < 0:
                idx = 3
        i += 1
    return [y, x]

solution("GRGRGRB")