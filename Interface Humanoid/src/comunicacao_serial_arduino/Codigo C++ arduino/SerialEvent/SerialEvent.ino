void setup() {
  pinMode(13, OUTPUT);
  // initialize serial:
  Serial.begin(9600);
}


String str = "arduino disse.";
String s = "";
void loop() {
  enviar(str);
  s = ler();

  if (!s.equals("")){
    enviar("arduino leu:" + s);
    s = "";
 
  }






  // este codigo esta funcionando com o eclipse normalmente
  //  delay(1000);
  //  Serial.print("!iTeste s!e07");
  //  delay(3000);
  //  Serial.print("!ifinalLoop!e09");

}


