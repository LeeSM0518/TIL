package properties;

import java.io.FileReader;
import java.net.URLDecoder;
import java.util.Properties;

public class PropertiesExample {
    public static void main(String[] args) throws Exception {
        // 프로퍼티 파일을 읽기 위해서는
        // Properties 객체를 생성하고,
        // load() 메소드를 호출하면 된다.
        Properties properties = new Properties();

        //프로퍼티 파일의 경로를 얻으려면 Class의
        // getResource() 메소드를 이용하면 된다.
        // 그리고 getPath() 메소드는
        // URL 파일의 절대 경로를 리턴한다.
        String path = PropertiesExample.class.getResource(
                "database.properties"
        ).getPath();
        path = URLDecoder.decode(path, "utf-8");
        properties.load(new FileReader(path));

        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");

        System.out.println("driver : " + driver);
        System.out.println("url : " + url);
        System.out.println("username : " + username);
        System.out.println("password : " + password);
    }
}
