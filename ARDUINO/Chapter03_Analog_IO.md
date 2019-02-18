# 아날로그 출력과 입력

## 6장. 소리만들기 - 피에조 스피커

* **소리 스케치**

  ```c
  int speakerPin = 3;		// 스피커에 연결되는 핀 번호
  
  void setup() {
  }
  
  void loop() {
      // 5000Hz의 음을 1초 동안 내기
    tone(speakerPin, 0, 1000);
  
    delay(2000);
  }
  ```

  > **코드에 사용된 새로운 명령어**
  >
  > * tone(pin, frequency, duration) : 특정 주파수로 소리를 일정시간 동안 낸다.

* **멜로디 만들기**

  ```c
  #include "pitches.h"  // 계명을 정의해 둔 헤더파일 선언
  
  // 멜로디 계명
  int melody[] = {
    NOTE_C4, NOTE_G3, NOTE_G3, NOTE_A3, NOTE_G3, 0, NOTE_B3, NOTE_C4
  };
  
  // note durations: 4 = quarter note, 8 = eighth note, etc.:
  int noteDurations[] = {
    4, 8, 8, 4, 4, 4, 4, 4
  };
  
  void setup() {
    // iterate over the notes of the melody:
    for (int thisNote = 0; thisNote < 8; thisNote++) {
  
      // to calculate the note duration, take one second divided by the note type.
      //e.g. quarter note = 1000 / 4, eighth note = 1000/8, etc.
      int noteDuration = 1000 / noteDurations[thisNote];
      tone(8, melody[thisNote], noteDuration);
  
      // to distinguish the notes, set a minimum time between them.
      // the note's duration + 30% seems to work well:
      int pauseBetweenNotes = noteDuration * 1.30;
      delay(pauseBetweenNotes);
      // stop the tone playing:
      noTone(8);
    }
  }
  
  void loop() {
    // no need to repeat the melody.
  }
  ```



## 8장. 광센서로 어둠을 감지하자

* **스케치**

  ```c
  /*
    제목    : 조도센서로 어두워지면 LED 켜기
    내용   : 조도센서를 손으로 가려 어둡게 만들면 LED가 켜지도록 해봅시다.
  */
   
  // 조도센서를 A0핀으로 선언하고 led핀을 3번핀으로 선언합니다.
  int cds = A0;
  int led = 3;
   
  // 실행시 가장 먼저 호출되는 함수이며, 최초 1회만 실행됩니다.
  // 변수를 선언하거나 초기화를 위한 코드를 포함합니다.
  void setup() {
    Serial.begin(9600);          // 조도센서의 동작 상태를 확인하기 위하여 시리얼 통신을 설정합니다. (전송속도 9600bps)
    pinMode(led, OUTPUT);   // LED 핀을 OUTPUT으로 설정합니다.
  }
   
  // setup() 함수가 호출된 이후, loop() 함수가 호출되며,
  // 블록 안의 코드를 무한히 반복 실행됩니다.
  void loop() {
    // 조도센서로 부터 측정된 밝기 값을 읽어 cdsValue라는 변수에 저장합니다.
    int cdsValue = analogRead(cds);
   
    // 측정된 밝기 값를 시리얼 모니터에 출력합니다.
    Serial.print("cds =  ");
    Serial.println(cdsValue);
   
    // 조도센서로 부터 측정된 밝기 값이 500보다 크다면, 아래의 블록을 실행합니다.
    // 기준 밝기값은 주변 환경에 따라 바꿔주세요.
    if (cdsValue > 500) {
      digitalWrite(led, HIGH);
      Serial.println("LED ON (cds > 500)");
    }
   
    // 조도센서로 부터 측정된 밝기 값이 500보다 작다면, 아래의 블록을 실행합니다.
    else {
      digitalWrite(led, LOW);
      Serial.println("LED OFF (cds < 500)");
    }
    delay(200);
  }
  ```



## 과제

* 회로

  ![1550295758835](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1550295758835.png)

* 스케치

  ```c
  int cds = A0;
  int led = 3;
   
  void setup() {
    Serial.begin(9600);    
    pinMode(led, OUTPUT);   
  }
  
  void loop() {
    int cdsValue = analogRead(cds);
   
    Serial.print("cds =  ");
    Serial.println(cdsValue);
   
    if (cdsValue > 700) {
      analogWrite(led, 255);
      Serial.println("LED HIGH (cds > 700)");
    }
  
    else if (cdsValue < 700 && cdsValue > 400) {
      analogWrite(led, 60);
      Serial.println("LED medium ( 500 < cdsValue < 400 )");
    }
   
    
    else {
      analogWrite(led, 0);
      Serial.println("LED OFF (cds < 500)");
    }
    delay(200);
  }
  ```




## 10장. 프로세싱과 아두이노 통신

* **프로세싱**

  ```c
  import processing.serial.*;
  
  Serial port;
  color c;
  PFont font;
  
  void setup() {
    size(300, 300);
    font = loadFont("ArialNarrow-48.vlw");
    
    fill(255);
    textFont(font, 32);
    
    String arduinoPort = Serial.list()[0];
    port = new Serial(this, arduinoPort, 9600);
  }
  
  void draw() {
    background(c);
    if (port.available() > 0) {
      int inByte = port.read();
      text("Hello", 50, 100);
      port.clear();
    }
  }
  ```

* **아두이노**

  ```c
  int val = 0;
  void setup() {
    Serial.begin(9600);
    pinMode(13, OUTPUT);
    digitalWrite(12, HIGH);
  }
  
  void loop() {
    val = digitalRead(12);
    if(val == HIGH) {
      digitalWrite(13, LOW);
    }
    else {
      digitalWrite(13, HIGH);
      Serial.println(val);
    }
  }
  ```

* **실행 결과**

  ![1550476199961](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1550476199961.png)