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
