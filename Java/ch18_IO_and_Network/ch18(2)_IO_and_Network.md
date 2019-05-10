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

  