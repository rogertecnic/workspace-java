#include<Servo.h>

Servo servo_0;
Servo servo_1;
Servo servo_2;
Servo servo_3;
Servo servo_4;
Servo servo_5;
Servo servo_6;
Servo servo_7;

#define PIN_SERVO_0 13
#define PIN_SERVO_1 12
#define PIN_SERVO_2  7
#define PIN_SERVO_3  6
#define PIN_SERVO_4 16
#define PIN_SERVO_5 17
#define PIN_SERVO_6 18
#define PIN_SERVO_7 19

//perna esquerda para direita 0-7
#define MIN_RANGE_SERVO_0 700 //calcanhar E ( 38 )
#define MIN_RANGE_SERVO_1 700 //joelho E - 36 
#define MIN_RANGE_SERVO_2 700//coxa E- 43
#define MIN_RANGE_SERVO_3 700//quadril E - 12N
#define MIN_RANGE_SERVO_4 700//quadril D - 10N
#define MIN_RANGE_SERVO_5 700//coxa D -35
#define MIN_RANGE_SERVO_6 700//joelho D - 37
#define MIN_RANGE_SERVO_7 700// calcanhar D (39 )

#define MAX_RANGE_SERVO_0 2200
#define MAX_RANGE_SERVO_1 2300
#define MAX_RANGE_SERVO_2 2200
#define MAX_RANGE_SERVO_3 2200
#define MAX_RANGE_SERVO_4 2300
#define MAX_RANGE_SERVO_5 2500
#define MAX_RANGE_SERVO_6 2300
#define MAX_RANGE_SERVO_7 2220
void setup() {
  pinMode(13, OUTPUT);
  // initialize serial:
  Serial.begin(9600);
  initServos();
}

String s = "";
void loop() {
  //s = i;
  //if(i<=512 && isArduinoConectado()){
  //enviar(s);
  //i++;
  //}



  //  s = ler();
  //  if (!s.equals("")) {
  //    // contando o numero de servos
  //    int numeroDeServos;
  //    for (int i = 0; i < s.length(); i++)
  //      if (s.charAt(i) == ';') numeroDeServos ++;
  //
  //    // lendo os valores de cada servo
  //    int estadoDosServos[numeroDeServos];
  //    int indexInicio = 0;
  //    int indexFinal;
  //    for (int i = 0; i < numeroDeServos; i++){
  //      indexFinal = s.indexOf(';', indexInicio); // a partir do index "i" (incluindo-o)
  //      String substr = s.substring(indexInicio, indexFinal);
  //      estadoDosServos[i] = substr.toInt();
  //      indexInicio = indexFinal+1;
  //    }
  //
  //    //int index = s.indexOf(';', 0); // a partir do index "i" (incluindo-o)
  //          //enviar(String(index));
  //          s = "";
  //    }

  int *servos = estadoRecebido();
  String s = "";
  for (int i = 0; i < servos[0]; i++)
    s += String( servos[i]);
  if (s != "")
    enviar(s);
  if (servos[0] > 8) {
    servo_0.writeMicroseconds(map(servos[1], 0, 180, MIN_RANGE_SERVO_0, MAX_RANGE_SERVO_0));
    servo_1.writeMicroseconds(map(servos[2], 0, 180, MIN_RANGE_SERVO_1, MAX_RANGE_SERVO_1));
    servo_2.writeMicroseconds(map(servos[3], 0, 180, MIN_RANGE_SERVO_2, MAX_RANGE_SERVO_2));
    servo_3.writeMicroseconds(map(servos[4], 0, 180, MIN_RANGE_SERVO_3, MAX_RANGE_SERVO_3));
    servo_4.writeMicroseconds(map(servos[5], 0, 180, MIN_RANGE_SERVO_4, MAX_RANGE_SERVO_4));
    servo_5.writeMicroseconds(map(servos[6], 0, 180, MIN_RANGE_SERVO_5, MAX_RANGE_SERVO_5));
    servo_6.writeMicroseconds(map(servos[7], 0, 180, MIN_RANGE_SERVO_6, MAX_RANGE_SERVO_6));
    servo_7.writeMicroseconds(map(servos[8], 0, 180, MIN_RANGE_SERVO_7, MAX_RANGE_SERVO_7));
  }
  //resetArrayServos();
  //if(servos.length > 0) enviar("ok");




  // este codigo esta funcionando com o eclipse normalmente
  //  delay(1000);
  //  Serial.print("!iTeste s!e07");
  //  delay(3000);
  //  Serial.print("!ifinalLoop!e09");

}


