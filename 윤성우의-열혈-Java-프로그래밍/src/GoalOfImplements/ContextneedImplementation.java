package GoalOfImplements;

class Friend {
    protected String name;
    protected String phone;

    public Friend(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public void showInfo() {
        System.out.println("이름: " + name);
        System.out.println("전화: " + phone);
    }
}

class UnivFriend extends Friend {
    private String major;

    public UnivFriend(String name, String phone, String major) {
        super(name, phone);
        this.major = major;
    }
    public void showInfo() {
        super.showInfo();
        System.out.println("전공: " + major);
    }
}

class CompFriend extends Friend {
    private String department;

    public CompFriend(String name, String phone, String department) {
        super(name, phone);
        this.department = department;
    }

    public void showInfo() {
        super.showInfo();
        System.out.println("부서: " + department);
    }
}

public class ContextneedImplementation {
    public static void main(String[] args) {
        // 상속 : 연관된 일련의 클래스들에 대해 공통적인 규약을 정의할 수 있다.
        Friend[] frns = new Friend[10]; // 하나의 배열에 모든 객체를 담을 수 있음
        int cnt = 0;
        frns[cnt++] = new UnivFriend("Lee", "Computer", "010-123-123");
        frns[cnt++] = new UnivFriend("seo", "Computer", "010-123-123");
        frns[cnt++] = new CompFriend("Yoon", "R&D 1", "010-123-123");
        frns[cnt++] = new CompFriend("Tae", "R&D 2", "010-123-123");

        for(int i = 0; i < cnt; i++){
            frns[i].showInfo();
            System.out.println();
        }
    }
}
