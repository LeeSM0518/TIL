# Lecture Manager (Java Project)

# UI

* **계획**

  1. 로그인 및 회원 가입 UI

     <img src="../../../capture/스크린샷 2019-05-13 오전 12.05.57.png">

     2. 학생 UI

     <img src="../../../capture/스크린샷 2019-05-13 오전 12.07.42.png">

     1. 교수님 UI

     <img src="../../../capture/스크린샷 2019-05-13 오전 12.08.43.png">

     <img src="../../../capture/스크린샷 2019-05-13 오전 12.09.44.png">



# Class

* **Server(서버)**
  * 
* **Client(클라이언트)**
  * 교수 Client
  * 학생 Client



# FlowChart

* **Server(서버) 순서도(로그인 및 회원가입)**

  ```mermaid
  graph TB
  
  start(서버 시작) --> newServer[서버 소켓 생성-ip,port]
  newServer --> newSocekt[클라이언트 소켓 생성]
  newSocekt --> checkSocket{로그인을 요청했는가?}
  checkSocket -->|Yes| checkId{아이디와 비밀번호가 존재하고 맞는가?}
  checkSocket -->|No| checkSign{회원가입 인가?}
  checkSign -->|Yes| inputInf[회원가입]
  checkSign -->|No| checkSocket
  checkId -->|Yes| checkStudent{학생인가 교수인가?}
  checkStudent -->|Student| newStudentClient[학생 클라이언트 생성]
  checkStudent -->|Professor| newProfessorClient[교수 클라이언트 생성]
  checkId -->|No| showError[에러]
  showError --> checkSocket
  ```

  * **Client 클래스가 필요하다 **

    ```java
    public class Client {
      String user;			// 학생인지 교수인지 판별
      String id;				// 학번
      String password;	// 비밀번호
    }
    ```

  * **Message 클래스 필요**

    ```java
    public class Message {
      String type;		// 로그인인지, 회원가입인지 등등 어떤 내용을 가진 메시지인지 알기 위함.
      String context;	// 내용
      final static char SPLIT_UNIT = '@';
      
      String[] splitContext(String subContext) {
        String[] splits = new String[subContext.length];
        
        // subContext를 SPLIT_UNIT 단위로 자른 후 splits에 저장
        
        return splits;
      }
      
    }
    ```

  * **파일 입출력 필요**

    * 객체 입출력으로 회원정보를 입력 및 출력 할 수 있는지 확인 필요.
    * 객체 입출력이 되지 않는다면 Writer, Reader 을 사용해서 20171687,1234,student 와 같이 입출력을 파일에 한다.

