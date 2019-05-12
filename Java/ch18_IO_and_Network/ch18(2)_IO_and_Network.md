## 18.6. 네트워크 기초

* **네트워크(network)** : 여러 대의 컴퓨터를 통신 회선으로 연결한 것

* **인터넷(internet)** : 지역 네트워크를 통신 회선으로 연결한 것



### 18.6.1. 서버와 클라이언트

* **서버(server)** : 서비스를 제공하는 프로그램
* **클라이언트(client)** : 서비스를 받는 프로그램

* 클라이언트는 서비스를 받기 위해 연결을 요청하고, 서버는 연결을 수락하여 서비스를 제공해준다. 서버는 클라이언트가 요청(request) 하는 내용을 처리해주고, 응답(response)을 클라이언트로 보낸다.

<img src="../capture/스크린샷 2019-05-09 오후 4.40.50.png">

* **클라이언트/서버(C/S: client/server)**
  * 두 개의 프로그램이 서버인 동시에 클라이언트 역할을 하는 P2P (peer to peer) 모델이 있다.



### 18.6.2. IP 주소와 포트(Port)

* **IP(Internet Protocol)** : 컴퓨터의 고유한 주소

  * IP 주소는 네트워크 어댑터 (랜(Lan) 카드) 마다 할당된다.

  * 연결할 상대방 컴퓨터의 IP 주소를 모른다면 프로그램들은 통신을 할 수 없다.

  * 프로그램은 DNS(Domain Name System)를 이용해서 연결할 컴퓨터의 IP 주소를 찾는다.

  * 대부분의 서버는 도메인 이름을 가지고 있다.

    ```
    DNS
    				도메인 이름			:			등록된 IP 주소
    	  	www.naver.com   : 	 222.122.195.5
    ```

  * 웹 브라우저는 사용자가가 입력한 도메인 이름을 DNS에서 검색하여 IP를 얻은 다음, 해당 IP를 가진 서버로 연결한다.

* **포트(Port)** : 컴퓨터 내에서 실행하는 서버를 선택하기 위해서 필요한 정보

  * IP는 컴퓨터의 네트워크 어댑터까지만 갈 수 있는 정보이기 때문에 컴퓨터 내에서 실행하는 서버를 선택해야하기 때문에 포트 번호가 필요하다.

  * **포트 바인딩(binding)** : 서버가 시작할 때 고정적인 포트 번호를 가지고 실행하는 것

  * **예시) 포트 바인딩**

    웹 서버는 80번과 바인딩하고, FTP 서버는 21번과 바인딩한다. 클라이언트가 웹 서버에 연결하려면 80번으로 연결 요청을 해야 하고, FTP 서버에 연결하려면 21번으로 연결 요청을 해야 한다.

    <img src="../capture/스크린샷 2019-05-09 오후 5.05.23.png">

  * 클라이언트도 서버에서 보낸 정보를 받기 위해 포트 번호가 필요한데, 서버와 같이 고정적인 포트 번호가 아니라 **운영체제가 자동으로 부여하는 동적 포트 번호를** 사용한다.

  * **동적 포트 번호** : 클라이언트가 서버로 연결 요청을 할 때 전송되어 서버가 클라이언트로 데이터를 보낼 때 사용된다.

    <img src="../capture/스크린샷 2019-05-09 오후 5.12.30.png">



### 18.6.3. InetAddress로 IP 주소 얻기

자바는 IP 주소를 java.net.InetAddress 객체로 표현한다. 

* **InetAddress 클래스** : 로컬 컴퓨터의 IP 주소 뿐만 아니라 도메인 이름을 DNS에서 검색한 후 IP 주소를 가져오는 기능을 제공한다.

  * **로컬 컴퓨터의 InetAddress 얻기**

    ```java
    InetAddress ia = InetAddress.getLocalHost();
    ```

  * **외부 컴퓨터의 도메인 이름 얻기**

    ```java
    // 매개값으로 준 도메인 이름으로 DNS에서 단 하나의 IP 주소를 얻어와 InetAddress를 생성 후 리턴
    InetAddress ia = InetAddress.getByName(String host);
    
    // 여러 개의 컴퓨터 IP가 등록된 도메인의 모든 IP를 얻어올 때 사용
    InetAddress[] iaArr = InetAddress.getAllByName(String host);
    ```

  * **네이버 도메인으로 ip 주소 가져오기**

    ```java
    package inet_address_basic;
    
    import java.net.InetAddress;
    import java.net.UnknownHostException;
    
    public class GlobalIpGet {
        public static void main(String[] args) throws UnknownHostException {
            InetAddress ia = InetAddress.getByName("www.naver.com");
            InetAddress[] iaArr = InetAddress.getAllByName("www.naver.com");
            System.out.println(ia);
            for (InetAddress ip : iaArr) {
                System.out.println(ip);
            }
    
            System.out.println(ia.getHostAddress());
        }
    }
    ```

    **실행 결과**

    ```
    www.naver.com/210.89.160.88
    www.naver.com/210.89.160.88
    www.naver.com/125.209.222.142
    210.89.160.88
    ```

* **예제) IP 주소 얻기**

  ```java
  package inet_address_basic;
  
  import java.net.InetAddress;
  import java.net.UnknownHostException;
  
  public class InetAddressExample {
      public static void main(String[] args) {
          try {
              InetAddress local = InetAddress.getLocalHost();
              System.out.println("내컴퓨터 IP주소: " + local.getHostAddress());
  
              InetAddress[] iaArr = InetAddress.getAllByName("www.naver.com");
  
              for (InetAddress remote : iaArr) {
                  System.out.println("www.naver.com IP주소: " +
                          remote.getHostAddress());
              }
  
          } catch (UnknownHostException e) {
              e.printStackTrace();
          }
      }
  }
  ```



## 18.7. TCP 네트워킹

* **TCP(Transmission Control Protocal)** : 연결 지향적 프로토콜이다.
  * **연결 지향 프로토콜** : 클라이언트와 서버가 연결된 상태에서 데이터를 주고받는 프로토콜이다. 클라이언트가 연결을 요청하고, 서버가 연결을 수락하면 통신 선로가 고정되고, 모든 데이터는 고정된 통신 선로를 통해서 순차적으로 전달된다.
  * **장점** : 데이터를 정확하고 안정적으로 전달
  * **단점** : 반드시 연결이 형성되어야 하고, 고정된 통신 선로가 최단선(네트워크 길이 측면)이 아닐 경우 상대적으로 **UDP(User Datagram Protocal)** 보다 데이터 전송 속도가 느리다.
  * 자바는 TCP 네트워킹을 위해 java.net.ServerSocket과 java.net.Socket 클래스를 제공하고 있다.



### 18.7.1. ServerSocket과 Socket의 용도

* **TCP 서버의 역할**

  * 클라이언트의 연결 요청 수락
  * 연결된 클라이언트와 통신
  * **java.net.ServerSocket 클래스** : 클라이언트 연결 수락 담당
  * **java.net.Socket 클래스** : 연결된 클라이언트와 통신 담당

  

* **서버와 클라이언트의 통신 과정**

  <img src="../capture/스크린샷 2019-05-09 오후 5.50.27.png">

  * **바인딩(binding) 포트** : 클라이언트가 접속할 포트를 서버가 생성하는 것
  * 서버는 고정된 포트 번호에 바인딩해서 실행하므로, ServerSocket을 생성할 때 포트 번호 하나를 지정해야 한다. 위 그림에서는 5001번이 서버 바인딩 포트이다.
  * 서버가 실행되면 클라이언트는 서버의 IP 주소와 바인딩 포트 번호로 Socket을 생성해서 연결 요청을 할 수 있다.
  * **ServerSocket** 은 클라이언트가 연결 요청을 해오면 **accept() 메소드로** 연결 수락을 하고 **통신용 Socket을 생성한다.**
  * 그 후 클라이언트와 서버는 각각의 Socket을 이용해서 데이터를 주고받게 된다.



### 18.7.2. ServerSocket 생성과 연결 수락

서버를 개발하려면 우선 ServerSocket 객체를 얻어야 한다. 

* **ServerSocket 얻는 방법**

  * 생성자에 바인딩 포트를 대입하고 객체를 생성하는 것

    ```java
    // 5001번 포트에 바인딩하는 ServerSocket 생성
    ServerSocket serverSocket = new ServerSocket(5001);
    ```

  * 디폴트 생성자로 객체를 생성하고 포트 바인딩을 위해 bind() 메소드를 호출하는 것

    ```java
    // ServerSocket 객체 생성
    ServerSocket serverSocket = new ServerSocket();
    // bind 메소드를 통해 포트 바인딩
    serverSocket.bind(new InetSocketAddress(5001));
    ```

  * 서버 PC에 멀티 IP가 할당되어 있을 경우, 특정 IP로 접속 연결 수락할 때

    ```java
    ServerSocket serverSocket = new ServerSocket();
    // "localhost" 대신 정확한 IP를 작성하면 특정 IP로만 연결을 수락한다.
    serverSocket.bind(new InetSocketAddress("localhost", 5001));
    ```

  * 해당 포트가 이미 다른 프로그램에서 사용 중이라면 BindException 이 발생한다. 이 경우에는 다른 포트로 바인딩하거나, 다른 프로그램을 종료하고 다시 실행하면 된다.

* **클라이언트 연결 수락**

  * 포트 바인딩이 끝나면 클라이언트 연결 수락을 위해 **accept()** 메소드를 실행해야 한다.

  * **블로킹(blocking)** : 스레드가 대기 상태가 된다는 뜻이다.

  * accept() 메소드는 클라이언트가 연결 요청하기 전까지 블로킹된다. 그래서 **이벤트를 처리하는 스레드에서 accept() 메소드를 호출하지 않도록** 한다. 

  * 클라이언트가 연결 요청을 하면 accept() 는 클라이언트와 통신할 Socket을 만들고 리턴한다. 이것이 **연결 수락이다.**

  * accept() 에서 블로킹되어 있을 때 **close()** 메소드를 호출하면 **SocketException** 이 발생한다. 그렇기 때문에 예외 처리가 필요하다.

    ```java
    try {
      Socket socket = serverSocket.accept();
    } catch (Exception e) {
      ...
    }
    ```

* **연결된 클라이언트의 정보**

  * **getRemoteSocketAddress() 메소드** : 연결된 클라이언트의 IP와 포트 정보 확인

    ```java
    InetSocketAddress socketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
    ```

  * **InetSocketAddress 메소드들**

    | 리턴 타입 | 메소드명(매개 변수) | 설명                             |
    | --------- | ------------------- | -------------------------------- |
    | String    | getHostName()       | 클라이언트 IP 리턴               |
    | int       | getPort()           | 클라이언트 포트 번호 리턴        |
    | String    | toString()          | "IP:포트번호" 형태의 문자열 리턴 |

* **연결 해제**

  * 더 이상 클라이언트 연결 수락이 필요 없으면 ServerSocket의 **close() 메소드를** 호출해서 포트를 **언바인딩시켜야** 한다.

    ```java
    serverSocket.close();
    ```

* **예제) 연결 수락**

  ```java
  package tcp_networking;
  
  import java.io.IOException;
  import java.net.*;
  
  public class ServerExample {
      public static void main(String[] args) throws UnknownHostException {
          ServerSocket serverSocket = null;
          InetAddress local = InetAddress.getLocalHost();
  
          try {
              // ServerSocket 생성
              serverSocket = new ServerSocket();
              serverSocket.bind(new InetSocketAddress(
                      // 127.0.0.1 로 할당
                      "localhost"
                      // 로컬 IP 주소로 서버 IP를 할당
                      // local.getHostAddress(),
                      ,
                      // 포트 번호 할당
                      5001
              ));
  
              System.out.println(serverSocket.getInetAddress());
  
              while (true) {
                  System.out.println("[연결 기다림]");
  
                  // 클라이언트 연결 수락
                  Socket socket = serverSocket.accept();
                  InetSocketAddress isa = (InetSocketAddress) socket.getRemoteSocketAddress();
                  System.out.println("[연결 수락함] " + isa.getHostName());
              }
  
          } catch (Exception e) {
              e.printStackTrace();
          }
  
          // ServerSocket 이 닫혀있지 않을 경우
          if (!serverSocket.isClosed()) {
              try {
                  // ServerSocket 닫기
                  serverSocket.close();
              } catch (IOException e1) {}
          }
      }
  }
  ```



### 18.7.3. Socket 생성과 연결 요청

클라이언트가 서버에 연결 요청을 하려면 Socket 객체의 생성자 매개값으로 서버의 IP 주소와 바인딩 포트 번호를 제공하면 된다.

* **예시**

  ```java
  try {
    Socekt socket = new Socket("localhost", 5001);	// 방법 1
    Socket socket = new Socket(new InetSocketAddress("localhost", 5001));	// 방법 2
  } catch (UnKnownHostException e) {
    // IP 표기 방법이 잘못되었을 경우
  } catch (IOException e) {
    // 해당 포트의 서버에 연결할 수 없는 경우
  }
  ```



외부 서버에 접속하려면 localhost 대신 정확한 IP를 입력하면 된다. 만약 IP 대신 도메인 이름만 알고 있다면, 도메인 이름을 IP 주소로 번역해야 하므로 InetSocketAddress 객체를 이용해야 한다.

* **예시**

  ```java
  socket = new Socket();
  socket.connet(new InetSocketAddress("localhost", 5001));
  ```



* **연결 요청시 발생할 수 있는 예외**
  * **UnknownHostException** : 잘못 표기된 IP 주소를 입력했을 경우에 발생
  * **IOException** : 주어진 포트로 접속할 수 없을 때 발생
  * 따라서 두 가지 예외를 처리해야 한다.



**Socket 생성자** 및 **connet() 메소드는** 서버와 연결이 될 때까지 **블로킹된다.** 그렇기 때문에 이벤트를 처리하는 스레드에서 Socket 생성자 및 connect() 를 호출하지 않도록 한다.



Socket의 close( ) 메소드도 IOException이 발생할 수 있기 때문에 예외 처리가 필요하다.

```java
try {
  socket.close();
} catch (IOException e) {}
```



* **예제) localhost 5001 포트로 연결 요청**

  ```java
  package tcp_networking;
  
  import java.io.IOException;
  import java.net.InetSocketAddress;
  import java.net.Socket;
  
  public class ClientExample {
      public static void main(String[] args) {
          Socket socket = null;
  
          try {
              // Socket 생성
              socket = new Socket();
              System.out.println("[연결 요청]");
              
              // 연결 요청
              socket.connect(new InetSocketAddress("localhost", 5001));
              System.out.println("[연결 성공]");
  
          } catch (IOException e) { }
  
          // 연결이 되어 있을 경우
          if (!socket.isClosed()) {
              try {
                  // 연결 끊기
                  socket.close();
              } catch (IOException e1) {}
          }
      }
  }
  ```



### 18.7.4. Socket 데이터 통신

클라이언트가 연결 요청(connect) 하고 서버가 연결 수락(accept) 했다면, 양쪽의 Socket 객체로부터 각각 입력 스트림(InputStream) 과 출력 스트림(OutputStream)을 얻을 수 있다.

<img src="../capture/스크린샷 2019-05-10 오후 4.20.14.png">

* **Stream 얻는 방법**

  ```java
  // 입력 스트림 얻기
  InputStream is = socket.getInputStream();
  
  // 출력 스트림 얻기
  OutputStream os = socket.getOutputStream();
  ```

* **데이터 보내는 방법**

  ```java
  String data = "보낼 데이터";
  byte[] byteArr = data.getBytes("UTF-8");
  OutputStream outputStream = socket.getOutputStream();
  outputStream.write(byteArr);
  outputStream.flush();
  ```

* **데이터 받는 방법**

  ```java
  byte[] byteArr = new byte[100];
  InputStream inputStream = socket.getInputStream();
  int readByteCount = inputStream.read(byteArr);
  String data = new String(byteArr, 0, readByteCount, "UTF-8");
  ```

* **예제) 데이터 보내고 받기**

  ClientExample.java

  ```java
  package socket_data_communication;
  
  import java.io.IOException;
  import java.io.InputStream;
  import java.io.OutputStream;
  import java.net.InetSocketAddress;
  import java.net.Socket;
  import java.nio.charset.StandardCharsets;
  
  public class ClientExample {
      public static void main(String[] args) {
          Socket socket = null;
  
          try {
              // 소켓 생성
              socket = new Socket();
              System.out.println("[연결 요청]");
  
              // 서버에 연결을 요청하기 위해 IP 주소와 바인딩 포트 제공하여 연결 요청
              socket.connect(new InetSocketAddress("localhost", 5001));
              System.out.println("[연결 성공]");
  
              // 보낼 데이터를 저장할 공간
              byte[] bytes = null;
              String message = null;
  
              // 출력 스트림 생성
              OutputStream os = socket.getOutputStream();
              message = "Hello Server";
  
              // "Hello Server"를 바이트 배열로 바꾼다.
              bytes = message.getBytes(StandardCharsets.UTF_8);
  
              // 서버로 바이트 배열을 보낸다.
              os.write(bytes);
              os.flush();
              System.out.println("[데이터 보내기 성공]");
  
              // 입력 스트림 생성
              InputStream is = socket.getInputStream();
  
              // 입력 받을 데이터 배열 생성
              bytes = new byte[100];
  
              // 바이트 배열에 데이터를 저장하고 입력받은 바이트 개수를 저장
              int readByteCount = is.read(bytes);
  
              // 바이트 배열을 String 형으로 바꾼다.
              message = new String(bytes, 0, readByteCount, StandardCharsets.UTF_8);
              System.out.println("[데이터 받기 성공]: " + message);
  
              os.close();
              is.close();
  
          } catch (Exception e) {}
  
          if (!socket.isClosed()) {
              try {
                  socket.close();
              } catch (IOException e1) {}
          }
  
      }
  }
  ```

  ServerExample.java

  ```java
  package socket_data_communication;
  
  import java.io.IOException;
  import java.io.InputStream;
  import java.io.OutputStream;
  import java.net.InetSocketAddress;
  import java.net.ServerSocket;
  import java.net.Socket;
  import java.nio.charset.StandardCharsets;
  
  public class ServerExample {
      public static void main(String[] args) {
          ServerSocket serverSocket = null;
  
          try {
              // 서버소켓 생성
              serverSocket = new ServerSocket();
              
              // 서버소켓의 IP와 포트 바인딩
              serverSocket.bind(new InetSocketAddress("localhost", 5001));
  
              while (true) {
                  // 클라이언트 연결 수락
                  System.out.println("[연결 기다림]");
                  Socket socket = serverSocket.accept();
                  
                  // 연결된 클라이언트 정보 얻기
                  InetSocketAddress isa = (InetSocketAddress) socket.getRemoteSocketAddress();
                  System.out.println("[연결 수락함] " + isa.getHostName());
  
                  // 입력받을 데이터 공간
                  byte[] bytes = null;
                  String message = null;
  
                  // 입력 스트림 생성
                  InputStream is = socket.getInputStream();
                  
                  // 데이터를 입력받을 바이트 배열 생성
                  bytes = new byte[100];
                  
                  // 데이터를 저장하고, 바이트 개수 저장
                  int readByteCount = is.read(bytes);
                  
                  // 바이트 배열을 String 으로 변환한다.
                  message = new String(bytes, 0, readByteCount, StandardCharsets.UTF_8);
                  System.out.println("[데이터 받기 성공]: " + message);
  
                  // 출력 스트림 생성
                  OutputStream os = socket.getOutputStream();
                  
                  // 출력할 문자열
                  message = "Hello Client";
                  
                  // 바이트 배열로 변환
                  bytes = message.getBytes(StandardCharsets.UTF_8);
                  
                  // 바이트 배열 출력
                  os.write(bytes);
                  os.flush();
                  System.out.println("[데이터 보내기 성공]");
  
                  // 종료
                  is.close();
                  os.close();
                  socket.close();
              }
              
          } catch (Exception e) {}
  
          if (!serverSocket.isClosed()) {
              try {
                  serverSocket.close();
              } catch (IOException e1) {}
          }
      }
  }
  ```

  **연결후 실행 결과**

  ServerExample 실행 결과

  ```
  [연결 기다림]
  [연결 수락함] localhost
  [데이터 받기 성공]: Hello Server
  [데이터 보내기 성공]
  [연결 기다림]
  ```

  ClientExample 실행 결과

  ```
  [연결 요청]
  [연결 성공]
  [데이터 보내기 성공]
  [데이터 받기 성공]: Hello Client
  ```



* **InputStream의 read( ) 메소드가 블로킹(blocking) 해제되고 리턴되는 경우**

  | 블로킹이 해제되는 경우                      | 리턴값           |
  | ------------------------------------------- | ---------------- |
  | 상대방이 데이터를 보냄                      | 읽은 바이트      |
  | 상대방이 정상적으로 Socket의 close()를 호출 | -1               |
  | 상대방이 비정상적으로 종료                  | IOException 발생 |



상대방이 정상적으로 Socket의 close( ) 를 호출하고 연결을 끊었을 경우와 비정상적으로 종료했을 경우, 모두 예외 처리를 해야한다.

```java
try {
  ...
  // 상대방이 비정상적으로 종료했을 경우 IOException 발생
  int readByteCount = inputStream.read(byteArr);
  
  // 상대방이 정상적으로 Socket의 close()를 호출했을 경우
  if (readByteCount == -1) {
    throw new IOException();	// 강제로 IOException 발생시킴
  }
  ...
} catch (Exception e) {
  try { socket.close(); } catch(Exception e2) {}
}
```



### 18.7.5. 스레드 병렬 처리

ServerSocket 과 Socket 은 동기(블로킹) 방식으로 구동된다. 그렇기 때문에 **accept(), connect(), read(), write()** 는 별도의 작업 스레드를 생성해서 병력적으로 처리하는 것이 좋다.

* **다중 클라이언트와 병렬적으로 통신하는 모습**

  <img src="../capture/스크린샷 2019-05-10 오후 6.54.59.png">

  > 이와 같은 구조는 클라이언트의 폭증으로 인해 서버의 과도한 스레드를 생성할 수도 있다.



* **스레드풀을 이용한 서버 구현 방식**

  <img src="../capture/스크린샷 2019-05-10 오후 6.57.55.png">

  1. 클라이언트 연결 요청
  2. 서버의 스레드풀에서 연결 수락 후, Socket 생성
  3. 클라이언트가 작업 처리 요청
  4. 서버의 스레드풀에서 요청 처리
  5. 응답을 클라리언트로 보냄



### 18.7.6. 채팅 서버 구현

#### 서버 클래스 구조

* **예제) 채팅 서버**

  ```java
  public class SeverExample extends Application {
    // 스레드풀 필드 선언
    ExecutorService executorService;
    // 클라이언트의 연결을 수락하는 필드 선언
    ServerSocket serverSocket;
    // 연결된 클라이언트를 저장하는 필드 선언
    List<Client> connections = new Vector<Client>();
    
    void startServer() { 
      // 서버 시작 코드
    }
    
    void stopServer() {
      // 서버 종료 코드
    }
    
    class Client {
      // 데이터 통신 코드
    }
    
    // UI 생성코드
  }
  ```



#### startServer() 메소드

서버를 시작하는 메소드인, startServer() 메소드에는 **ExecutorService 생성, ServerSocket 생성 및 포트 바인딩, 연결 수락** 코드가 있어야 한다.

* **ExecutorService 생성 예시**

  ```java
  void startServer() {
    // ExecutorService 객체 호출
    executorService = Executors.newFixedThreadPool(
      // 사용가능한 CPU 코어 수 호출
    	Runtime.getRuntime().availableProcessors()
    );
    ...
  }
  ```

* **ServerSocket 생성 및 포트 바인딩 예시**

  ```java
  ...
    try {
      // ServerSocket 객체 생성
      serverSocket = new ServerSocket();
      // ServerSocket울 로컬로 IP를 잡고 5001 포트와 바인딩한다.
      serverSocket.bind(new InetSocketAddress("localhost", 5001));
    } catch (Exception e) {
      // 예외가 발생할 경우 서버를 닫고 메소드를 종료한다.
      if(!serverSocket.isClosed()) {
        stopServer();
        return;
      }
    }
  ...
  ```

* **연결 수락 예시**

  ```java
  ...
    // 수락 작업 생성
    Runnable runnable = new Runnable() {
    @Override
    public void run() {
      System.out.println("[서버 시작]");
      while (true) {
        try {
          // 연결 수락
          Socket socket = serverSocket.accept();
          String message = "[연결 수락: " + 
            socket.getRemoteSocketAddress() +
            ": " + Thread.currentThread().getName() +
            "]";
          System.out.println(message);
  
          // Client 객체 저장
          Client client = new Client(socket);
          connections.add(client);
        } catch (Exception e) {
          if (!serverSocket.isClosed()) {
            stopServer();
            break;
          }
        }
      }
    }
  };
  // 스레드풀에서 처리
  executorService.submit(runnable);
  }
  ```



#### stopServer() 메소드

* **모든 Socket 닫기, ServerSocket 닫기, ExecutorService 종료 코드**

  ```java
  void stopServer() {
          try {
  //            (원래 방법) 모든 Socket 닫기
  //            Iterator<Client> iterator = connections.iterator();
  //            while (iterator.hasNext()) {
  //                Client client = iterator.next();
  //                client.socket.close();
  //                iterator.remove();
  //            }
               
              // (스트림 이용 방법) 모든 Socket 닫기
              connections.forEach(client -> {
                          try {
                              client.socket.close();
                          } catch (IOException e) {
                              e.printStackTrace();
                          }
                      });
              
              // ServerSocket 닫기
              if (serverSocket != null && !serverSocket.isClosed()) {
                  serverSocket.close();
              }
              
              // ExecutorService 종료
              if (executorService != null && !executorService.isShutdown()) {
                  executorService.isShutdown();
              }
  
              System.out.println("[서버 멈춤]");
              
          } catch (Exception e) {}
      }
  ```



#### Client 클래스

서버에 다수의 클라이언트가 연결하기 때문에 서버는 **클라이언트를 관리해야 한다.** 클라이언트별로 고유한 데이터를 저장할 필요도 있기 때문에 Client 클래스를 작성하고, **연결 수락 시 마다 Client 인스턴스를 생성해서 관리하는 것이 좋다.**

* **코드**

  ```java
  // Client 를 내부 클래스로 선언
  class Client {
    Socket socket;
  
    // 매개값으로 socket 을 받는 생성자
    Client(Socket socket) {
      this.socket = socket;
      receive();
    }
  
    void receive() {
      // 데이터 받기 작업 생성
      Runnable runnable = new Runnable() {
        @Override
        public void run() {
          try {
            while (true) {
              byte[] byteArr = new byte[100];
              InputStream inputStream = socket.getInputStream();
  
              // 클라이언트가 비정상 종료를 했을 경우 IOException 발생
              int readByteCount = inputStream.read(byteArr);  // 데이터 받기
  
              // 클라이언트가 정상적으로 Socket 의 close() 를 호출했을 경우
              if (readByteCount == -1) {
                throw new IOException();
              }
  
              String message = "[요청 처리: " + socket.getRemoteSocketAddress() + ": " +
                Thread.currentThread().getName() + "]";
              System.out.println(message);
  
              // 문자열로 변환
              String data = new String(byteArr, 0, readByteCount, StandardCharsets.UTF_8);
  
              // 모든 클라이언트에게 보냄
              for (Client client : connections) {
                client.send(data);
              }
            }
          } catch (Exception e) {
            try {
              connections.remove(Client.this);
  
              String message = "[클라이언트 통신 안됨: " +
                socket.getRemoteSocketAddress() +
                ": " + Thread.currentThread().getName() + "]";
              System.out.println(message);
  
              socket.close();
            } catch (IOException e2) {}
          }
  
        }
      };
  
      // 스레드풀에서 처리
      executorService.submit(runnable);
    }
  
    void send(String data) {
      // 데이터 보내기 작업 생성
      Runnable runnable = new Runnable() {
        @Override
        public void run() {
          // 클라이언트로 데이터 보내기
          try {
            byte[] byteArr = data.getBytes(StandardCharsets.UTF_8);
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(byteArr);
            outputStream.flush();
          } catch (Exception e) {
            try {
              String message = "[클라이언트 통신 안됨: " +
                socket.getRemoteSocketAddress() + ": " +
                Thread.currentThread().getName() + "]";
              System.out.println(message);
  
              connections.remove(Client.this);
              socket.close();
            } catch (IOException e2) {}
          }
        }
      };
      // 스레드풀에서 처리
      executorService.submit(runnable);
    }
  }
  ```



#### UI 생성 메소드

* **코드**

  ```java
  public class ServerExample {
  		...
      JPanel mainPanel = new JPanel();
      static JTextArea jTextArea = new JTextArea();	// 스레드 내에서 UI를 조작하기 위해
    	... 
        
      void start() {
          ServerExample server = new ServerExample();
  
          JFrame jFrame = new JFrame("Server");
  
          mainPanel.setLayout(new BorderLayout());
          JButton jButton = new JButton("START");
  
          jTextArea.setEditable(false);
  
          jButton.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                  if (jButton.getLabel().equals("START")) {
                      server.startServer();
                      jButton.setLabel("STOP");
                  } else {
                      server.stopServer();
                      jButton.setLabel("START");
                  }
              }
          });
  
          mainPanel.add(jTextArea, BorderLayout.CENTER);
          mainPanel.add(jButton, BorderLayout.SOUTH);
  
          jFrame.add(mainPanel);
  
          jFrame.setSize(500, 300);
          jFrame.setVisible(true);
      }
  
      public static void main(String[] args) {
          ServerExample serverExample = new ServerExample();
          serverExample.start();
      }
  }    
  ```



#### 전체 코드

```java
package chat_server_implement;

import com.sun.security.ntlm.Client;
import sun.lwawt.PlatformEventNotifier;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

public class ServerExample {

    ExecutorService executorService;
    ServerSocket serverSocket;
    List<Client> connections = new Vector<>();

    JPanel mainPanel = new JPanel();
    static JTextArea jTextArea = new JTextArea();

    void startServer() {
        // ExecutorService 객체 호출
        executorService = Executors.newFixedThreadPool(
                // 사용가능한 CPU 코어 수 호출
                Runtime.getRuntime().availableProcessors()
        );

        try {
            // ServerSocket 객체 생성
            serverSocket = new ServerSocket();
            // ServerSocket 을 로컬로 IP를 잡고 5001 포트와 바인딩한다.
            serverSocket.bind(new InetSocketAddress("localhost", 5001));
        } catch (Exception e) {
            // 예외가 발생할 경우 서버를 닫고 메소드를 종료한다.
            if (!serverSocket.isClosed()) {
                stopServer();
                return;
            }
        }

        // 수락 작업 생성
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("[서버 시작]");
                jTextArea.append("[서버 시작]\n");

                while (true) {
                    try {
                        // 연결 수락
                        Socket socket = serverSocket.accept();
                        String message = "[연결 수락: " +
                                socket.getRemoteSocketAddress() +
                                ": " + Thread.currentThread().getName() +
                                "]";
                        System.out.println(message);
                        jTextArea.append(message + "\n");

                        // Client 객체 저장
                        Client client = new Client(socket);
                        connections.add(client);

                        jTextArea.append("[연결 개수: " + connections.size() + "]" + "\n");
                    } catch (Exception e) {
                        if (!serverSocket.isClosed()) {
                            stopServer();
                            break;
                        }
                    }
                }
            }
        };
        // 스레드풀에서 처리
        executorService.submit(runnable);
    }

    void stopServer() {
        try {
//            (원래 방법) 모든 Socket 닫기
//            Iterator<Client> iterator = connections.iterator();
//            while (iterator.hasNext()) {
//                Client client = iterator.next();
//                client.socket.close();
//                iterator.remove();
//            }

            // (스트림 이용 방법) 모든 Socket 닫기
            connections.forEach(client -> {
                try {
                    client.socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            // ServerSocket 닫기
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }

            // ExecutorService 종료
            if (executorService != null && !executorService.isShutdown()) {
                executorService.isShutdown();
            }

            System.out.println("[서버 멈춤]");
            jTextArea.append("[서버 멈춤]\n");

        } catch (Exception e) {
        }
    }

    // Client 를 내부 클래스로 선언
    class Client {
        Socket socket;

        // 매개값으로 socket 을 받는 생성자
        Client(Socket socket) {
            this.socket = socket;
            receive();
        }

        void receive() {
            // 데이터 받기 작업 생성
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            byte[] byteArr = new byte[100];
                            InputStream inputStream = socket.getInputStream();

                            // 클라이언트가 비정상 종료를 했을 경우 IOException 발생
                            int readByteCount = inputStream.read(byteArr);  // 데이터 받기

                            // 클라이언트가 정상적으로 Socket 의 close() 를 호출했을 경우
                            if (readByteCount == -1) {
                                throw new IOException();
                            }

                            String message = "[요청 처리: " + socket.getRemoteSocketAddress() + ": " +
                                    Thread.currentThread().getName() + "]";
                            System.out.println(message);
                            jTextArea.append(message + "\n");

                            // 문자열로 변환
                            String data = new String(byteArr, 0, readByteCount, StandardCharsets.UTF_8);

                            // 모든 클라이언트에게 보냄
                            for (Client client : connections) {
                                client.send(data);
                            }
                        }
                    } catch (Exception e) {
                        try {
                            connections.remove(Client.this);

                            String message = "[클라이언트 통신 안됨: " +
                                    socket.getRemoteSocketAddress() +
                                    ": " + Thread.currentThread().getName() + "]";
                            System.out.println(message);
                            jTextArea.append(message + "\n");

                            socket.close();
                        } catch (IOException e2) {
                        }
                    }

                }
            };

            // 스레드풀에서 처리
            executorService.submit(runnable);
        }

        void send(String data) {
            // 데이터 보내기 작업 생성
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    // 클라이언트로 데이터 보내기
                    try {
                        byte[] byteArr = data.getBytes(StandardCharsets.UTF_8);
                        OutputStream outputStream = socket.getOutputStream();
                        outputStream.write(byteArr);
                        outputStream.flush();
                    } catch (Exception e) {
                        try {
                            String message = "[클라이언트 통신 안됨: " +
                                    socket.getRemoteSocketAddress() + ": " +
                                    Thread.currentThread().getName() + "]";
                            System.out.println(message);
                            jTextArea.append(message + "\n");

                            connections.remove(Client.this);
                            socket.close();
                        } catch (IOException e2) {
                        }
                    }
                }
            };
            // 스레드풀에서 처리
            executorService.submit(runnable);
        }
    }

    void start() {
        ServerExample server = new ServerExample();

        JFrame jFrame = new JFrame("Server");

        mainPanel.setLayout(new BorderLayout());
        JButton jButton = new JButton("START");

        jTextArea.setEditable(false);

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jButton.getLabel().equals("START")) {
                    server.startServer();
                    jButton.setLabel("STOP");
                } else {
                    server.stopServer();
                    jButton.setLabel("START");
                }
            }
        });

        mainPanel.add(jTextArea, BorderLayout.CENTER);
        mainPanel.add(jButton, BorderLayout.SOUTH);

        jFrame.add(mainPanel);

        jFrame.setSize(500, 300);
        jFrame.setVisible(true);
    }

    public static void main(String[] args) {
        ServerExample serverExample = new ServerExample();
        serverExample.start();
    }
}
```



### 18.7.7. 채팅 클라이언트 구현

#### 클라이언트 클래스 구조

* **채팅 클라이언트 코드**

  ```java
  public class ClientExample extends Application {
    Socket socket;
    
    void startClient() {
      // 연결 시작 코드
    }
    
    void stopClient() {
      // 연결 끊기 코드
    }
    
    void receive() {
      // 데이터 받기 코드
    }
    
    void send(String data) {
      // 데이터 전송 코드
    }
    
    // UI 생성 코드
  }
  ```



#### startClient() 메소드

Socket 생성 및 연결 요청하는 메소드

* **코드**

  ```java
  void startClient() {
    // 스레드 생성
    Thread thread = new Thread() {
      @Override
      public void run() {
        try {
          // 소켓 생성 및 연결 요청
          socket = new Socket();
          socket.connect(new InetSocketAddress("localhost", 5001));
  
          String message = "[연결 완료: " + socket.getRemoteSocketAddress() + "]";
          System.out.println(message);
  
          jTextArea.append(message + "\n");
        } catch (Exception e) {
          String message = "[서버 통신 안됨]";
          System.out.println(message);
  
          jTextArea.append(message + "\n");
          if (!socket.isClosed()) {
            stopClient();
          }
          return;
        }
        // 서버에서 보낸 데이터 받기
        receive();
      }
    };
    // 스레드 시작
    thread.start();
  }
  ```



#### stopClient() 메소드

Socekt을 닫는 close() 메소드 호출

* **코드**

  ```java
  void stopClient() {
    try {
      String message = "[연결 끊음]";
      System.out.println(message);
  
      jTextArea.append(message + "\n");
  
      // 연결 끊기
      if (socket != null && !socket.isClosed()) {
        socket.close();
      }
  
    } catch (IOException e) {
  
    }
  }
  ```



#### receive() 메소드

서버에서 보낸 데이터를 받는 역할.

```java
void receive() {
  while (true) {
    try {
      byte[] byteArr = new byte[100];
      InputStream inputStream = socket.getInputStream();

      // 서버가 비정상적으로 종료했을 경우 IOException 발생
      int readByteCount = inputStream.read(byteArr);          // 데이터 받기

      // 서버가 정상적으로 Socket 의 close() 를 호출했을 경우
      if (readByteCount == -1) {
        throw new IOException();
      }

      // 문자열로 변환
      String data = new String (byteArr, 0, readByteCount, StandardCharsets.UTF_8);

      String message = "[받기 완료] " + data;

      System.out.println(message);
      jTextArea.append(message + "\n");

    } catch (Exception e) {
      String message = "[서버 통신 안됨]";
      System.out.println(message);
      jTextArea.append(message + "\n");

      stopClient();
      break;
    }
  }
}
```



#### send(String data) 메소드

사용자로 부터 메시지를 입력받고 서버로 메시지를 보낸다.

```java
void send(String data) {
  // 스레드 생성
  Thread thread = new Thread() {
    @Override
    public void run() {
      try {
        byte[] byteArr = data.getBytes(StandardCharsets.UTF_8);

        // 서버로 데이터 보내기
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(byteArr);
        outputStream.flush();

        String message = "[보내기 완료]";
        System.out.println(message);

        jTextArea.append(message + "\n");

      } catch (Exception e) {
        String message = "[서버 통신 안됨]";
        System.out.println(message);
        jTextArea.append(message + "\n");
        stopClient();
      }
    }
  };
  // 스레드 시작
  thread.start();
}
```



#### UI 생성 메소드

```java
void start() {
  Client2Example client = new Client2Example();

  JFrame jFrame = new JFrame("Client");

  JPanel subPanel = new JPanel();

  mainPanel.setLayout(new BorderLayout());
  subPanel.setLayout(new BorderLayout());

  jTextArea.setLineWrap(true);
  jTextArea.setWrapStyleWord(true);
  JScrollPane scrollPane = new JScrollPane(jTextArea);

  JButton startBtn = new JButton("Start");
  JTextField textField = new JTextField();
  JButton sendBtn = new JButton("Send");

  startBtn.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      if (startBtn.getLabel().equals("Start")) {
        jTextArea.setText("Start" + "\n");
        client.startClient();
        startBtn.setLabel("Stop");
      } else {
        client.stopClient();
        startBtn.setLabel("Start");
      }
    }
  });

  sendBtn.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      client.send(textField.getText());
      textField.setText("");
    }
  });

  subPanel.add(startBtn, BorderLayout.WEST);
  subPanel.add(textField, BorderLayout.CENTER);
  subPanel.add(sendBtn, BorderLayout.EAST);

  mainPanel.add(jTextArea, BorderLayout.CENTER);
  mainPanel.add(subPanel, BorderLayout.SOUTH);

  jFrame.add(mainPanel);
  jFrame.setSize(500, 300);
  jFrame.setVisible(true);
}
```



#### 전체 코드

```java
package chat_server_implement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ClientExample {

    Socket socket;
    JPanel mainPanel = new JPanel();
    static JTextArea jTextArea = new JTextArea();

    void startClient() {
        // 스레드 생성
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    // 소켓 생성 및 연결 요청
                    socket = new Socket();
                    socket.connect(new InetSocketAddress("localhost", 5001));

                    String message = "[연결 완료: " + socket.getRemoteSocketAddress() + "]";
                    System.out.println(message);

                    jTextArea.append(message + "\n");
                } catch (Exception e) {
                    String message = "[서버 통신 안됨]";
                    System.out.println(message);

                    jTextArea.append(message + "\n");
                    if (!socket.isClosed()) {
                        stopClient();
                    }
                    return;
                }
                // 서버에서 보낸 데이터 받기
                receive();
            }
        };
        // 스레드 시작
        thread.start();
    }

    void stopClient() {
        try {
            String message = "[연결 끊음]";
            System.out.println(message);

            jTextArea.append(message + "\n");

            // 연결 끊기
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }

        } catch (IOException e) {

        }
    }

    void receive() {
        while (true) {
            try {
                byte[] byteArr = new byte[100];
                InputStream inputStream = socket.getInputStream();

                // 서버가 비정상적으로 종료했을 경우 IOException 발생
                int readByteCount = inputStream.read(byteArr);          // 데이터 받기

                // 서버가 정상적으로 Socket 의 close() 를 호출했을 경우
                if (readByteCount == -1) {
                    throw new IOException();
                }

                // 문자열로 변환
                String data = new String (byteArr, 0, readByteCount, StandardCharsets.UTF_8);

                String message = "[받기 완료] " + data;

                System.out.println(message);
                jTextArea.append(message + "\n");

            } catch (Exception e) {
                String message = "[서버 통신 안됨]";
                System.out.println(message);
                jTextArea.append(message + "\n");

                stopClient();
                break;
            }
        }
    }

    void send(String data) {
        // 스레드 생성
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    byte[] byteArr = data.getBytes(StandardCharsets.UTF_8);

                    // 서버로 데이터 보내기
                    OutputStream outputStream = socket.getOutputStream();
                    outputStream.write(byteArr);
                    outputStream.flush();

                    String message = "[보내기 완료]";
                    System.out.println(message);

                    jTextArea.append(message + "\n");

                } catch (Exception e) {
                    String message = "[서버 통신 안됨]";
                    System.out.println(message);
                    jTextArea.append(message + "\n");
                    stopClient();
                }
            }
        };
        // 스레드 시작
        thread.start();
    }

    void start() {
        Client2Example client = new Client2Example();

        JFrame jFrame = new JFrame("Client");

        JPanel subPanel = new JPanel();

        mainPanel.setLayout(new BorderLayout());
        subPanel.setLayout(new BorderLayout());

        jTextArea.setLineWrap(true);
        jTextArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(jTextArea);

        JButton startBtn = new JButton("Start");
        JTextField textField = new JTextField();
        JButton sendBtn = new JButton("Send");

        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (startBtn.getLabel().equals("Start")) {
                    jTextArea.setText("Start" + "\n");
                    client.startClient();
                    startBtn.setLabel("Stop");
                } else {
                    client.stopClient();
                    startBtn.setLabel("Start");
                }
            }
        });

        sendBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.send(textField.getText());
                textField.setText("");
            }
        });

        subPanel.add(startBtn, BorderLayout.WEST);
        subPanel.add(textField, BorderLayout.CENTER);
        subPanel.add(sendBtn, BorderLayout.EAST);

        mainPanel.add(jTextArea, BorderLayout.CENTER);
        mainPanel.add(subPanel, BorderLayout.SOUTH);

        jFrame.add(mainPanel);
        jFrame.setSize(500, 300);
        jFrame.setVisible(true);
    }

    public static void main(String[] args) {
        Client2Example client = new Client2Example();
        client.start();
    }
}
```



## 18.8. UDP 네트워킹

* **UDP(User Datagram Protocol)** : 비연결 지향적 프로토콜이다. 즉, 발신자가 일방적으로 데이터를 발신하는 방식이다.

<img src="../capture/스크린샷 2019-05-12 오후 11.08.12.png">

### 18.8.1. 발신자 구현

- **DatagramSocket 객체 생성**

  ```java
  DatagramSocket datagramSocket = new DatagramSocket();
  ```

- **보내고자 하는 데이터 생성**

  ```java
  byte[] byteArr = data.getBytes("UTF-8");
  ```

- **DatagramPacket 생성**

  ```java
  byte[] byteArr = data.getBytes("UTF-8");
  // 첫 번째 매개값 : 보낼 데이터 (byte 배열)
  // 두 번째 매개값 : 보내고자 하는 항목 수 (byte.length)
  // 세 번째 매개값 : 수신자 IP 와 포트 정보 (SocketAddress)
  DatagramPacket packet = new DatagramPacket(
  	byteArr, byteArr.length,
    new InetSocketAddress("localhost", 5001)
  );
  ```

- **데이터 전달**

  ```java
  datagramSocket.send(packet);
  ```

- **더 이상 보낼 데이터가 없을 때**

  ```java
  datagramSocket.close();
  ```

- **예제) 발신자**

  ```java
  package udp_networking;
  
  import java.net.DatagramPacket;
  import java.net.DatagramSocket;
  import java.net.InetSocketAddress;
  import java.nio.charset.StandardCharsets;
  
  public class UdpSendExample {
      public static void main(String[] args) throws Exception {
          // DatagramSocket 객체 생성
          DatagramSocket datagramSocket = new DatagramSocket();
  
          System.out.println("[발신 시작]");
          
          for (int i = 1; i < 3; i++) {
              String data = "메시지" + i;
              byte[] byteArr = data.getBytes(StandardCharsets.UTF_8);
              
              // DatagramPacket 생성
              DatagramPacket packet = new DatagramPacket(
                      byteArr, byteArr.length,
                      new InetSocketAddress("localhost", 5001)
              );
  
              // DatagramPacket 전송
              datagramSocket.send(packet);
              System.out.println("[보낸 바이트 수]: " + byteArr.length + " bytes");
          }
  
          System.out.println("[발신 종료]");
          
          // DatagramSocket 닫기
          datagramSocket.close();
          
      }
  }
  ```



### 18.8.2. 수신자 구현

- **수신자 DatagramSocket 생성**

  ```java
  DatagramSocket datagramSocket = new DatagramSocket(5001);
  ```

- **패킷 읽을 준비**

  ```java
  // receive() 메소드를 호출해서 패킷 읽을 준비
  // 블로킹이 된다.
  datagramSocket.receive(datagramPacket);
  ```

- **패킷의 내용 저장**

  ```java
  DatagramPacket datagramPacket = new DatagramPacket(new byte[100], 100);
  ```

- **문자열로 변환**

  ```java
  String data = new String(packet.getData(), 0, packet.getLength(), "UTF-8");
  ```

- **발신자의 IP 와 포트 알아내기**

  ```java
  SocketAddress socketAddress = packet.getSocketAddress();
  ```

- **작업 스레드 종료**

  ```java
  datagramSocket.close();
  ```

- **예제) 수신자**

  ```java
  package udp_networking;
  
  import java.net.DatagramPacket;
  import java.net.DatagramSocket;
  import java.nio.charset.StandardCharsets;
  
  public class UdpReceiveExample extends Thread {
  
      public static void main(String[] args) throws Exception {
          // 5001 번 포트에서 수신하는 DatagramSocket 생성
          DatagramSocket datagramSocket = new DatagramSocket(5001);
  
          Thread thread = new Thread() {
              @Override
              public void run() {
                  System.out.println("[수신 시작]");
                  try {
                      while (true) {
                          // DatagramPacket 수신
                          DatagramPacket packet = new DatagramPacket(new byte[100], 100);
                          datagramSocket.receive(packet);
  
                          // 패킷을 스트링을 변환
                          String data = new String(packet.getData(), 0, packet.getLength(),
                                  StandardCharsets.UTF_8);
  
                          System.out.println("[받은 내용: " + packet.getSocketAddress() + "] " + data);
                      }
                  } catch (Exception e) {
                      System.out.println("[수신 종료]");
                  }
              }
          };
  
          thread.start();
  
          Thread.sleep(10000);
          
          // 종료
          datagramSocket.close();
      }
  }
  ```