package CollectionFrameworkPrac;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class AsListCollection {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Toy", "Box", "Robot", "Box");
        // 인자로 전달된 인스턴스들을 저장한 컬렉션 인스턴스의 생성 및 반환
        list = new ArrayList<>(list);

        // for문 기반의 반복자 획득과 순차적 참조
        for(Iterator<String> itr = list.iterator(); itr.hasNext();)
            System.out.println(itr.next());
        System.out.println();

        // "Box"를 모두 삭제하기 위한 반복문
        for(Iterator<String> itr = list.iterator(); itr.hasNext();)
            if (itr.next().equals("Box"))
                itr.remove();

        for(Iterator<String> itr = list.iterator(); itr.hasNext();)
            System.out.println(itr.next());
    }
}
