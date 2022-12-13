# 연산자

1. 산술 > 비교 > 논리 > 대입
2. 단항 > 이항 > 삼항
3. 단항 연산자와 대입 연산자를 제외한 모든 연산의 진행방향은 왼쪽에서 오른쪽이다.

### 단항연산자

```java
// 전위, 후위 연산자
public class Operator {
    public static void main(String[] args){
        int i=5,j=0;
        j=i++; // 대입 후 1 증가
        System.out.println(j+", "+i); // 5, 6
        j=0;
        i=5;
        j=++i; // 대입 전 1 증가
        System.out.println(j+", "+i); // 6, 6
    }
}
```