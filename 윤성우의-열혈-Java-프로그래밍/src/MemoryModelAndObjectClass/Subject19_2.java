package MemoryModelAndObjectClass;

class Buisness implements Cloneable {
    private String company;
    private String work;

    public Buisness(String company, String work) {
        this.company = company;
        this.work = work;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public void showBuisnessInfo() {
        System.out.println("회사 : " + company);
        System.out.println("업무 : " + work);
    }
}

class PersonalInfo implements Cloneable {
    private String name;
    private int age;
    private Buisness bz;

    public PersonalInfo(String name, int age, Buisness bz) throws CloneNotSupportedException {
        this.name = name;
        this.age = age;
        this.bz = (Buisness) bz.clone();
    }

    public void showPersonalInfo() {
        System.out.println("이름 : " + name);
        System.out.println("나이 : " + age);
    }
}

public class Subject19_2 {
    public static void main(String[] args) {

    }
}
