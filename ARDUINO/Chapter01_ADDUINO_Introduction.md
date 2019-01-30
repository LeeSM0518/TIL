1부. 아두이노 소개
====
## Chapter 01 ) 아두이노 소개와 설치

<br>

### 아두이노란?
**아두이노 = 하드웨어 보드 + 소프트웨어 언어 + 오픈소스 그룹 <br> 소스코드 = 스케치 (아두이노는 소스 프로그램을 '스케치'라 한다.)**

<br>

* **아두이노 구성** <br>
  <img src="https://kjkjjang.files.wordpress.com/2017/07/arduino.png?w=840" width="100%">

<br>

* **구조(Structure)** <br>
  아두이노 프로그램을 **스케치**(소스코드) 라고 부르며 일반적으로 두 개의 함수를 호출한다.
  ```c
  void setup(){
    // 이곳에 있는 코드는 한번만 실행됨
  }
  void loop(){
    // 이곳의 코드는 반복해서 실행됨
  }
  ```

<br>

* **문법(Syntax)** <br>

  |기호(문법)|내용|
  |:---:|:---:|
  |**//**|두 줄 슬래쉬 뒤는 한 라인의 설명문|
  |**//**| 두 줄 슬래쉬 뒤는 한 라인의 설명문|
  |/* */ | 여러 라인의 설명문|
  |**{ }** | 코드 블록|
  |**;** | 한 줄의 코드 끝에는 세미콜론|

<br>

* **변수(Variables)**
  
  |기호(문법)|내용|
  |:---:|:---:|
  | **int**|2바이트, 16비트 정수, -32,768 ~ 32767|
  |**long**| 4바이트, 32비트 정수|
  |**boolean**| 1 비트, 참 or 거짓|
  |**float** | 4바이트 소수|
  |**char**| ASCII 코드|

<br>

* 산술 연산자(수학적 계산)
  
  |기호(문법)|내용|
  |:---:|:---:|
  | == |같은가?|
  |!=| 같지 않은가?|
  |<| 작은가?|
  |>|큰가?|
  

<br>

* 제어구조

  |기호(문법)|내용|
  |:---|:---:|
  |**if(조건){ }<br>else if(조건){ }<br>else{ }**|조건문|
  |for(int i = 0 ; i < 반복횟수 ; i ++ ){ } |for문|

<br>

* 디지털 함수
  * **pinMode(pin, mode);**
  * **digitalWrite(pin, value)**
  * **int digitalRead(pin)**

<br>

* 아날로그 함수
  * int analogWrite(pin, value);
  * int analogRead(pin);

<br>

* 아두이노 프로그램의 실행
  * 스케치 작성
  * 컴파일
  * 업로드

<br>

### 아두이노 메뉴 기능

#### Edit

* **Copy for Discourse** : 색상까지 클립보드에 복사
* **Copy as HTML** : HTML로 클립보드에 복사, 웹페이지에 적합

#### Sketch

* **Verify/Compile** : 스케치의 오류 점검
* **Import Library** : 라이브러리 헤더 파일을 가져옴
* **Show Sketch Folder** : 스케치 폴더를 열어 보여줌
* **Add File...** : 소스 파일을 스케치에 추가

#### Tools

* **Auto Format** : 코드를 멋지게 정리해 줌
* **Board** : 아두이노 보드를 선택
* **Serial Port** : 시리얼 포트 선택
* **Burn Bootloader** : 일반적으로 사용되지 않음, 부트로더가 없는 ATmega에 부트로더를 만들어 줌.

#### Sketchbook

* 작업한 스케치를 저장하고 관리한다.



<br>

## Chapter 02. 아두이노 보드 가지고 놀기

### 간단한 예제

* **아두이노와 대화하기**

  ```c
  // 아두이노가 1초 마다 숫자를 증가시켜
  // 컴퓨터로 보낸다.
  void setup()
  {
      Serial.begin(9600);	// 통신속도 9600
  }
  int cnt=0;
  void loop()
  {
      cnt++;
      Serial.println(cnt);
      delay(1000);
  }
  ```

* **아두이노에게 이야기하기**

  ```c
  // 'h'를 받으면 2초갼 LED를 킨다.
  void setup() {
    Serial.begin(9600);
    pinMode(LED_BUILTIN, OUTPUT);
  }
  
  void loop() {
    char ch = Serial.read();
    if(ch=='h') {
      digitalWrite(LED_BUILTIN, HIGH);
      delay(2000);
      digitalWrite(LED_BUILTIN, LOW); 
    }
  }
  ```



<br>

## Chpater 03. 전자부품과 친해지기

### 전자의 기본지식

* **저항**

  : 전압(V)은 저항(I)과 전류(R)의 곲으로 표현된다.

* **저항 읽기**

  ![1548605637324](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1548605637324.png)

* **커패시터 읽기**

  ![1548605757553](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1548605757553.png)



<br>

# 2부. 디지털 출력과 입력

## Chapter 04. LED 가지고 놀기

* **LED**

  : LED는 전기가 한쪽 방향으로만 흐르고, 긴 다리 쪽이 +, 짧은 다리 쪽이 - 이다.

* **LED 가지고 놀기**

  * **저항** : 220옴(빨빨갈 색띠)

* **회로도**

  ![1548656941459](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1548656941459.png)

  > 구부러진 곳이 긴 다리이다.

* **Sktech**

  ```c
  void setup() {
    pinMode(13, OUTPUT);
  }
  
  void loop() {
    digitalWrite(13, HIGH);
    delay(1000);
    digitalWrite(13, LOW);
    delay(1000);
  }
  ```



<br>

## Chapter 05. 푸시버튼 스위치 가지고 놀기

* **디지털 입력이란?**

  : 디지털은 0과 1로 표현되며, 0인 상태는 0V(볼트), 1인 상태는 5V(볼트)를 나타냄. 아두이노에서는 HIGH 신호와 LOW 신호

* **회로도**

  ![1548657075868](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1548657075868.png)

* **버튼 스케치**

  ```c
  const int buttonPin = 2;     // the number of the pushbutton pin
  const int ledPin =  13;      // the number of the LED pin
  int buttonState = 0;         // variable for reading the pushbutton status
  
  void setup() {
    // initialize the LED pin as an output:
    pinMode(ledPin, OUTPUT);
    // initialize the pushbutton pin as an input:
    pinMode(buttonPin, INPUT);
  }
  
  void loop() {
    // read the state of the pushbutton value:
    buttonState = digitalRead(buttonPin);
  
    // check if the pushbutton is pressed. If it is, the buttonState is HIGH:
    if (buttonState == HIGH) {
      // turn LED on:
      digitalWrite(ledPin, HIGH);
    } else {
      // turn LED off:
      digitalWrite(ledPin, LOW);
    }
  }
  ```

* **LED 깜빡임 속도 조절하기**

  ```c
  const int buttonPin = 2;     // the number of the pushbutton pin
  const int ledPin =  13;      // the number of the LED pin
  int buttonState = 0;         // variable for reading the pushbutton status
  int delayval = 100;
  
  void setup() {
    // initialize the LED pin as an output:
    pinMode(ledPin, OUTPUT);
    // initialize the pushbutton pin as an input:
    pinMode(buttonPin, INPUT);
  }
  
  void loop() {
    // read the state of the pushbutton value:
    buttonState = digitalRead(buttonPin);
  
    // check if the pushbutton is pressed. If it is, the buttonState is HIGH:
    if (buttonState == HIGH) {
      delayval = 1000;
    } else {
      delayval = 100;
    }
  
    digitalWrite(ledPin, HIGH);
    delay(delayval);
    digitalWrite(ledPin, LOW);
    delay(delayval);
  }
  ```

* **Delay를 사용하지 않고 깜빡이기**

  ```c
  const int ledPin =  13;       // LED가 사용하는 핀 번호 설정
  int ledState = LOW;           // LED 설정값
  unsigned long previousMillis = 0;     // 이전 상태를 저장
  
  // 깜빡임 간격을 설정할 때는 변수를 long을 사용한다.
  // long은 int 보다 큰 수를 저장 할 수 있다.
  const long interval = 1000;           // 깜빡임 간격 설정, 1000은 1초
  
  void setup() {
    pinMode(ledPin, OUTPUT);            // LED는 출력으로 설정
  }
  
  void loop() {
    // 현재 시간과 이전 깜빡이는 시간차가 설정 간격보다 크면 이전 시간 저장
    unsigned long currentMillis = millis();
  
    if (currentMillis - previousMillis >= interval) {
      previousMillis = currentMillis;   // LED 이전 시간 저장
  
      if (ledState == LOW) {
        ledState = HIGH;
      } else {
        ledState = LOW;
      }
  
      // 변수의 LED 값으로 설정
      digitalWrite(ledPin, ledState);
    }
  }
  ```

  > **millis()** : 아두이노 보드가 시작되고 현재 프로그램이 시작된 후의 밀리초의 숫자를 반환한다. 1000이면 1초 100이면 0.1초이다.

* **실습문제**

  ```c
  const int ledPin = 13;
  
  void setup() {
    Serial.begin(9600);
    pinMode(ledPin, OUTPUT);
  }
  
  void loop() {
    String check = Serial.readStringUntil('\n');
    
    if(check.equals("Hi")) {
      digitalWrite(ledPin, HIGH);
    } else if(check.equals("Bye")) {
      digitalWrite(ledPin, LOW);
    }
  }
  ```

  



<br/>

# 4부. 아두이노 프로세싱 언어 및 라이브러리

## Chapter 12. 아두이노 언어

| 함수                                                         | 기능                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| **setup()**                                                  | 프로그램 초기화 부분                                         |
| **loop()**                                                   | 이 함수 내의 명령어들은 보드에서 전원이 나갈 때까지 계속 반복되는 부분 |
| **pinMode(pin, mode)**                                       | 디지털 데이터 값을 송수신하기 위하여 핀 번호를 설정하여, 이 핀의 역할 즉 입력 혹은 출력을 설정한다.<br />**예) pinMode(5, INPUT);  // 5번 을 입력으로 설정** |
| **digitalWrite(pin, value)**                                 | 디지털 핀을 켜거나(HIGH) 끄기(LOW)위해 사용된다. 이 함수를 사용하기 위해 pinMode 함수를 이용하여 미리 핀의 상태를 설정해 놓아야 한다. |
| **int digitalRead(pin)**                                     | 입력으로 설정된 핀의 상태를 읽는다. 핀에 전압이 인가되어 있으면 HIGH를, 전압이 없으면 LOW를 반환한다. **예) val=digitalRead(5);  // 5번 핀을 읽어 val 변수에 저장** |
| **int analogRead(pin)**                                      | 아날로그 입력 핀에 인가된 전압을 읽어 0부터 1023사이 값으로 변환하여 반환한다.**예) val=analogRead(0);  // 아날로그 입력 핀 0번을 읽어 변수 val에 저장** |
| **analogWrite(pin, value)**                                  | 펄스 폭 변조(PWM)표시가 있는 핀의 PWM비율을 결정한다. 아두이노에서 지원하는 핀 번호는 3,5,6,9,10,11번 핀이다. 변수 val에는 0부터 255 사이의 값을 쓸 수 있으며, 각각 0에서 5V 전압을 의미한다.<br />**예) analogWrite(3, 128);  // 3번 핀에 연결된 것의 원래 값 보다 50% 적게 함** |
| **shiftOut(dataPin, clockPin, bitOrder, value)**             | 디지털 출력의 개수를 확장하는 시프트 레지스터에 데이터를 전달한다. 이 프로토콜은 하나의 핀을 데이터(dataPin)로 또 하나의 핀을 클럭(clockPin)으로 사용한다. bitOrder는 바이트의 순서 즉 MSB가 먼저 오는지 LSB가 먼저 오는지를 나타낸다. value는 실제로 전달할 바이트이다.<br />**예) shiftOut(dataPin, clockPin, LSBFIRST, 255);** |
| **millis()**                                                 | 스케치가 시작된 뒤로 흐른 시간을 밀리세컨드(ms)단위로 반환.<br />**// 시작시간 startTime에서 시간이 얼마나 흘렀는지 계산**<br />**예) duration = millis()-startTime;** |
| **micros()**                                                 | 아두이노 보드에서 프로그램이 시작된 후 소요된 시간을 밀리세컨드 단위로 반환한다.<br />**// 프로그램이 시작된 후 소요된 시간을 time에 저장**<br />**예) time = micros();** |
| **delay()**                                                  | 입력된 밀리세컨드만큼 프로그램을 잠시 멈춘다.<br />**예) // 0.1초간 프로그램을 멈춘다.<br />delay(100);** |
| **delayMicroseconds()**                                      | 입력된 마이크로세컨드만큼 프로그램을 잠시 멈춘다.<br />**// 50마이크로세컨드 동안 프로그램을 멈춘다.**<br />**예) delayMicroseconds(50);** |
| **constrain(x,a,b)**                                         | 범위가 a와 b사이로 제약된 x값을 반환한다. 만약 x가 a보다 작으면 이 함수는 a값을 반환한다. 그리고 x가 b보다 크면 b값을 반환한다.<br />**예) // sensVal 값의 범위를 10~150 사이로 결정함**<br />**sensVal = constrain(analogRead(0), 10, 150);** |
| **map(value, fromLow, fromHigh, toLow, toHigh)**             | 이 함수를 이용하면 데이터의 범위를 재조정 할 수 있다. fromLow부터 fromHigh 사이의 값을 toLow에서 toHigh 범위에 맞게 조정한다. 이는 아날로그 센서에서 읽어 온 값을 처리하는데 유용하다.<br />**예) // 아날로그 핀 0핀에서 읽은 값을 8비트(0~255)로 표현한다.**<br />**val = map(analogRead(0), 0, 1023, 0, 255);** |
| **Serial.begin(val)**                                        | 아두이노가 시리얼 데이터를 주고 받기위해 통신 속도를 설정하는 것이다.<br />**예) Serial.begin(9600); //9600bps로 통신을 설정** |
| **Serial.print(data),<br />Serial.print(data, encoding),**<br />**Serial.println(data),**<br />**Serial.println(data, encoding)** | 이 함수는 주어진 데이터를 시리얼 포트로 전송한다. 인코딩은 옵션이며, 표기 하지 않았을 경우 데시마(DEC) 값으로 표현되어 전송된다. 옵션 encoding의 종류는 DEC(10진수), HEX(16진수), OCT(8진수), BIN(2진수), BYTE(10진수의 ASCII값)가 있다. 그리고 Serial.println(data)와 Serial.println(data, encoding)는 데이터를 입력하고 리턴이나 엔터키를 눌렀을 때의 효과를 나타내는 것으로서, 줄바꿈이 추가된다는 점을 제외하면 나머지는 Serial.print()함수와 동일하다. |
| **Serial.available()**                                       | 아두이노의 read()함수에서 사용하기 위해 시리얼 포트에 읽지 않은 바이트가 얼마나 남아 있는지 반환한다. 프록그램에서 모든 데이터를 read()로 읽은 뒤 새로운 데이터가 시리얼 포트에 올 때까지 이 함수는 항상 0을 반환한다.<br />**예) // 얼마만큼의 바이트가 수신되었는지 출력하는 프로그램**<br />**if(Serial.available() > 0) {**<br />**incomingByte = Serial.read();**<br />**Serial.print("I received: ");**<br />**Serial.println(incomingByte, DEC);**<br />**}** |
| **Serial.read()**                                            | 시리얼 포트를 통해서 도착하는 시리얼 데이터에서 한 바이트를 읽어온다.<br />**// 데이터를 읽어서 incomingByte에 저장**<br />**예) incomingByte = Serial.read();** |
| **Serial.flush()**                                           | 이 함수는 시리얼 데이터를 송수신 하는데 있어서 버퍼 역할을 한다. 시리얼 포트로 도착하는 데이터가 프로그램이 처리하는 속도보다 빨리 들어오기 때문에 아두이노는 들어오는 모든 데이터를 버퍼에 보관한다. 그래서 이 버퍼를 비우고 새로운 데이터를 채우고자 한다면 이 함수를 사용한다. |

