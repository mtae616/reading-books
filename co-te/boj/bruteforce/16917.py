# 양념 a
# 후라이드 b
# 반반 c

# 양념 x
# 후라이드 y

a, b, c, x, y = map(int, input().split())

print(min(a*x+b*y,(x*2*c)+(y-x)*b if x<y else (y*2*c)+(x-y)*a,max(x,y)*2*c))
