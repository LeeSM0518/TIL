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
