package system_class;

import java.util.Objects;
import java.util.Properties;
import java.util.Set;

public class get_property_method {
    public static void main(String[] args) {
        // String 객체에 특정 시스템 프로퍼티 저장
        String osName = System.getProperty("os.name");
        String userName = System.getProperty("user.name");
        String userHome = System.getProperty("user.home");

        System.out.println("운영체제 이름: " + osName);
        System.out.println("사용자 이름: " + userName);
        System.out.println("사용자 홈디렉토리: " + userHome);

        System.out.println("----------------------------------");
        System.out.println(" [ key ]  value");
        System.out.println("----------------------------------");

        // 모든 프로퍼티 속성의 키와 값을 호출
        Properties props = System.getProperties();

        // 키만으로 구성된 Set 객체 저장
        Set keys = props.keySet();

        for(Object objKey : keys) {
            // 키를 하나씩 얻어내어 문자열로 변환한 뒤
            String key = (String) objKey;

            // 키로 값을 불러온다.
            String value = System.getProperty(key);


            System.out.println("[ " + key + " ]  " + value);
        }
    }
}