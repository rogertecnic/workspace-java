#include <Servo.h>

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

int motors[] = {15, 90, 90, 90, 90, 90, 90, 90, 90, 0, 0, 0, 0, 0, 0, 0}; //posição motores
int q[] = {0, 0, 0, 0, 0, 0, 0, 0};     //posição inicial calibração copia
int dq[] = {0, 0, 0, 0, 0, 0, 0, 0};


//vetores auxiliar para posição e velocidades
//cada estado esta dividido em 10 vetores
int q_0[] = {0, 0, 0, 0, 0, 0, 0, 0};
int q_1[] = {0, 0, 0, 0, 0, 0, 0, 0};
int q_2[] = {0, 0, 0, 0, 0, 0, 0, 0};
int q_3[] = {0, 0, 0, 0, 0, 0, 0, 0};
int q_4[] = {0, 0, 0, 0, 0, 0, 0, 0};
int q_5[] = {0, 0, 0, 0, 0, 0, 0, 0};
int q_6[] = {0, 0, 0, 0, 0, 0, 0, 0};
int q_7[] = {0, 0, 0, 0, 0, 0, 0, 0};
int q_8[] = {0, 0, 0, 0, 0, 0, 0, 0};
int q_9[] = {0, 0, 0, 0, 0, 0, 0, 0};
int q_10[] = {0, 0, 0, 0, 0, 0, 0, 0};
int vel[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};


Servo servo_0;
Servo servo_1;
Servo servo_2;
Servo servo_3;
Servo servo_4;
Servo servo_5;
Servo servo_6;
Servo servo_7;

//protocolos
//I[x][xx][xx][xxx]F
//I[M][00][00][090]F
const uint8_t BUFFER_SIZE = 18;
char buffer[BUFFER_SIZE];
uint8_t size = 0;

bool inAuto = false;
bool serial = false;
int counter = 0;


String inputString = "";     // a string to hold incoming data
/*************************************************************************************************************
  SerialEvent occurs whenever a new data comes in the
  hardware serial RX.  This routine is run between each
  time loop() runs, so using delay inside loop can delay
  response.  Multiple bytes of data may be available.
*************************************************************************************************************/
//void serialEvent() {
//
//  while (Serial.available()) {
//    // get the new byte:
//    char inChar = (char)Serial.read();
//    // add it to the inputString:
//    inputString += inChar;
//  }
//
//}

void setup() {

  pinMode(13, OUTPUT);  //usar led pino 13
  inputString.reserve(200);
  initServos();
  Serial.begin(9600);// Inicializa a comunicação serial com uma taxa de 9600 bauds.

  delay(1000);

 /* state0();
  state1();
  state2();
  state3();
  state5();
  state6();
  //state8();
  //*/
  state0();
}

void loop() {

//  if (inputString.length() != 0)
//    process();//processa a mensagen recebida

  //if (size == BUFFER_SIZE)
  //  process();//processa a mensagen recebida

  // if (inAuto)
  //   runStateVariables();
  // else if (inicial) {
  //  setStateVariable(q, 200);
  //  inicial = false;
  // }

  writeServos(0);

   //walk();
  ////
}

void walk() {
  state1();
  state2();
  state3();
  state5();
  state6();
  state8();
  state9();
}


//inclinarDireita
void state0() {
  int t = 100;
  int v_1[] =  { 0, 0, 0, 0, 0, 0, 0, 0};
  int v_2[] =  { 0, 1, 1, 0, 0, -1, -1, 0};
  int v_3[] =  { 0, 2, 2, 0, 0, -2, -2, 0};
  int v_4[] =  { 0, 4, 3, 0, 0, -3, -4, 0};
  int v_5[] =  { 0, 6, 4, 0, 0, -4, -6, 0};
  int v_6[] =  { 0, 8, 6, 0, 0, -6, -8, 0};
  int v_7[] =  { 0, 10, 7, 0, 0, -7, -10, 0};
  int v_8[] =  { 0, 12, 8, 0, 0, -8, -12, 0};
  int v_9[] =  { 0, 12, 8, 0, 0, -8, -12, 0};
  int v_10[] = { 0, 12, 8, 0, 0, -8, -12, 0};
  int vel2[] = {t, t, t, t, t, t, t, t, t, t};

  array8(dq, v_10);

  array8(q_1, v_1);
  array8(q_2, v_2);
  array8(q_3, v_3);
  array8(q_4, v_4);
  array8(q_5, v_5);
  array8(q_6, v_6);
  array8(q_7, v_7);
  array8(q_8, v_8);
  array8(q_9, v_9);
  array8(q_10, v_10);
  array10(vel, vel2);


  runStateVariables();
}

//inclinarDireita
void state1() {
  int t = 100;
  int v_1[] =  { 1, 12, 8, 0, 0, -8, -12, -1};
  int v_2[] =  { 2, 12, 8, 0, 0, -7, -13, -2};
  int v_3[] =  { 3, 12, 8, 0, 0, -6, -12, -3};
  int v_4[] =  { 4, 12, 8, 0, 0, -5, -10, -4};
  int v_5[] =  { 6, 12, 8, 0, 0, -4,  -8, -6};
  int v_6[] =  { 10, 12, 8, 0, 0, -3,  -6, -8};
  int v_7[] =  {12, 12, 8, 0, 0, -2,  -5, -10};
  int v_8[] =  {15, 12, 8, 0, 0, -1,  -4, -13};
  int v_9[] =  {18, 12, 8, 0, 0,  0,  -2, -14};
  int v_10[] = {20, 12, 8, 0, 0,  0,  0,  -15};
  int vel2[] = {t, t, t, t, t, t, t, t, t, t};

  array8(q_1, v_1);
  array8(q_2, v_2);
  array8(q_3, v_3);
  array8(q_4, v_4);
  array8(q_5, v_5);
  array8(q_6, v_6);
  array8(q_7, v_7);
  array8(q_8, v_8);
  array8(q_9, v_9);
  array8(q_10, v_10);
  array10(vel, vel2);

  runStateVariables();
}

//inclinarDireita
void state2() {
  int t = 10;
  int v_1[] =  { 20, 14, 10, 0, 0, 1, 1, -15};
  int v_2[] =  { 18, 14, 10, 0, 0, 2, 2, -15};
  int v_3[] =  { 16, 14, 10, 0, 0, 3, 3, -15};
  int v_4[] =  { 17, 14, 10, 0, 0, 4, 4, -13};
  int v_5[] =  { 17, 14, 10, 0, 0, 5, 4, -10};
  int v_6[] =  { 17, 14, 10, 0, 0, 6, 4, -6};
  int v_7[] =  { 17, 14, 10, 0, 0, 6, 4, -4};
  int v_8[] =  { 17, 14, 10, 0, 0, 6, 4, -3};
  int v_9[] =  { 17, 14, 10, 0, 0, 6, 4, -2};
  int v_10[] = { 17, 14, 10, 0, 0, 6, 4, 0};
  int vel2[] = {t, t, t, t, t, t, t, t, t, t};

  array8(q_1, v_1);
  array8(q_2, v_2);
  array8(q_3, v_3);
  array8(q_4, v_4);
  array8(q_5, v_5);
  array8(q_6, v_6);
  array8(q_7, v_7);
  array8(q_8, v_8);
  array8(q_9, v_9);
  array8(q_10, v_10);
  array10(vel, vel2);

  runStateVariables();
}

//inclinarDireita
void state3() {
  int t = 100;
  int v_1[] =  { 17, 14, 10, 0, 0, 6, 4, 2};
  int v_2[] =  { 15, 14, 10, 0, 0, 6, 4, 4};
  int v_3[] =  { 13, 14, 10, 0, 0, 6, 4, 6};
  int v_4[] =  { 11, 14, 10, 0, 0, 6, 4, 8};
  int v_5[] =  { 8, 14, 10, 0, 0, 6, 4, 10};
  int v_6[] =  { 6, 14, 10, 0, 0, 6, 4, 8};
  int v_7[] =  { 4, 14, 10, 0, 0, 6, 4, 6};
  int v_8[] =  { 2, 14, 10, 0, 0, 6, 4, 4};
  int v_9[] =  { 1, 14, 10, 0, 0, 6, 4, 2};
  int v_10[] = { 0, 14, 10, 0, 0, 6, 4, 0};
  int vel2[] = {t, t, t, t, t, t, t, t, t, t};

  array8(q_1, v_1);
  array8(q_2, v_2);
  array8(q_3, v_3);
  array8(q_4, v_4);
  array8(q_5, v_5);
  array8(q_6, v_6);
  array8(q_7, v_7);
  array8(q_8, v_8);
  array8(q_9, v_9);
  array8(q_10, v_10);
  array10(vel, vel2);

  runStateVariables();
}


/*void state4() {
  int t = 100;
  int v_1[] = { 10, 14, 10, 0, 0, 6, 4, 10};
  int v_2[] = { 9, 14, 10, 0, 0, 6, 4, 9};
  int v_3[] = { 8, 14, 10, 0, 0, 6, 4, 8};
  int v_4[] = { 7, 14, 10, 0, 0, 6, 4, 7};
  int v_5[] = { 6, 14, 10, 0, 0, 6, 4, 6};
  int v_6[] = { 5, 14, 10, 0, 0, 6, 4, 5};
  int v_7[] = { 4, 14, 10, 0, 0, 6, 4, 4};
  int v_8[] = { 3, 14, 10, 0, 0, 6, 4, 3};
  int v_9[] = { 2, 14, 10, 0, 0, 6, 4, 1};
  int v_10[] = { 0, 14, 10, 0, 0, 6, 4, 0};
  int vel2[] = {t, t, t, t, t, t, t, t, t, t};

  array8(q_1, v_1);
  array8(q_2, v_2);
  array8(q_3, v_3);
  array8(q_4, v_4);
  array8(q_5, v_5);
  array8(q_6, v_6);
  array8(q_7, v_7);
  array8(q_8, v_8);
  array8(q_9, v_9);
  array8(q_10, v_10);
  array10(vel, vel2);

  runStateVariables();
  }
*/

void state5() {
  int t = 100;
  int v_1[] = { 0, 14, 10, 0, 0, 6, 4, 0};
  int v_2[] = { -2, 12, 9, 0, 0, 8, 12, -2};
  int v_3[] = { -5, 11, 8, 0, 0, 8, 12, -5};
  int v_4[] = { -8, 10, 7, 0, 0, 8, 12, -8};
  int v_5[] = { -10, 8, 6, 0, 0, 8, 12, -9};
  int v_6[] = { -12, 6, 5, 0, 0, 8, 12, -10};
  int v_7[] = { -15, 5, 4, 0, 0, 8, 12, -12};
  int v_8[] = { -15, 3, 3, 0, 0, 10, 12, -15};
  int v_9[] = { -15, 2, 2, 0, 0, 14, 12, -15};
  int v_10[] = { -15, 0, 0, 0, 0, 15, 12, -15};
  int vel2[] = {t, t, t, t, t, t, t, t, t, t};

  array8(q_1, v_1);
  array8(q_2, v_2);
  array8(q_3, v_3);
  array8(q_4, v_4);
  array8(q_5, v_5);
  array8(q_6, v_6);
  array8(q_7, v_7);
  array8(q_8, v_8);
  array8(q_9, v_9);
  array8(q_10, v_10);
  array10(vel, vel2);

  runStateVariables();
}

void state6() {
  int t = 10;
  int v_1[] = { -15, 0, 0, 0, 0, 14, 12, -15};
  int v_2[] = { -15, -1, -1, 0, 0, 12, 12, -15};
  int v_3[] = { -15, -2, -2, 0, 0, 10, 12, -15};
  int v_4[] = { -15, -5, -3, 0, 0, 10, 10, -15};
  int v_5[] = { -15, -8, -4, 0, 0, 10, 10, -15};
  int v_6[] = { -15, -8, -5, 0, 0, 10, 10, -15};
  int v_7[] = { -15, -8, -6, 0, 0, 10, 10, -15};
  int v_8[] = { -15, -8, -7, 0, 0, 10, 10, -15};
  int v_9[] = { -15, -8, -8, 0, 0, 10, 10, -15};
  int v_10[] = { -15, -8, -8, 0, 0, 10, 10, -15};
  int vel2[] = {t, t, t, t, t, t, t, t, t, t};

  array8(q_1, v_1);
  array8(q_2, v_2);
  array8(q_3, v_3);
  array8(q_4, v_4);
  array8(q_5, v_5);
  array8(q_6, v_6);
  array8(q_7, v_7);
  array8(q_8, v_8);
  array8(q_9, v_9);
  array8(q_10, v_10);
  array10(vel, vel2);

  runStateVariables();
}

/*void state7() {

  int v_1[] = { 0, 0, 0, 0, 0, 8, 12, -15};
  int v_2[] = { 0, -1, -1, 0, 0, 7, 12, -15};
  int v_3[] = { 0, -2, -2, 0, 0, 6, 12, -15};
  int v_4[] = { 0, -5, -3, 0, 0, 5, 12, -15};
  int v_5[] = { 0, -8, -4, 0, 0, 4, 10, -15};
  int v_6[] = { 0, -10, -5, 0, 0, 3, 8, -15};
  int v_7[] = { 0, -12, -6, 0, 0, 2, 6, -15};
  int v_8[] = { 0, -13, -7, 0, 0, 1, 4, -15};
  int v_9[] = { 0, -14, -8, 0, 0, 0, 2, -15};
  int v_10[] = { 0, -14, -10, 0, 0, 0, 0, -15};
  int vel2[] = {100, 100, 100, 100, 100, 100, 100, 100, 100, 100};

  array8(q_1, v_1);
  array8(q_2, v_2);
  array8(q_3, v_3);
  array8(q_4, v_4);
  array8(q_5, v_5);
  array8(q_6, v_6);
  array8(q_7, v_7);
  array8(q_8, v_8);
  array8(q_9, v_9);
  array8(q_10, v_10);
  array10(vel, vel2);

  runStateVariables();
}
*/
void state8() {
  
 int t = 100;
  int v_1[] = { -15, -8, -8, 0, 0, 10, 10, -15};
  int v_2[] = { -12, -8, -7, 0, 0, 8, 9, -12};
  int v_3[] = { -10,  -8, -6, 0, 0, 6, 8, -10};
  int v_4[] = {-8,  -6, -5, 0, 0, 4, 7, -8};
  int v_5[] = { -7,  -4, -4, 0, 0, 2, 6, -7};
  int v_6[] = { -4,  -2, -3, 0, 0, 1, 5, -4};
  int v_7[] = { -2,  -1, -2, 0, 0, 0, 4, -2};
  int v_8[] = { -1,   0, -1, 0, 0, 0, 3, -1};
  int v_9[] = { 0,   0,  0, 0, 0, 0, 2, 0};
  int v_10[] = { 0,  0,  0, 0, 0, 0, 1, 0};
  int vel2[] = {t, t, t, t, t, t, t, t, t, t};

  array8(q_1, v_1);
  array8(q_2, v_2);
  array8(q_3, v_3);
  array8(q_4, v_4);
  array8(q_5, v_5);
  array8(q_6, v_6);
  array8(q_7, v_7);
  array8(q_8, v_8);
  array8(q_9, v_9);
  array8(q_10, v_10);
  array10(vel, vel2);

  runStateVariables();
}

void state9() {
  
 int t = 100;
  int v_1[] =  { 0, 0,  0, 0, 0,  0,  0, 0};
  int v_2[] =  { 0, 1,  0, 0, 0,  0,  0, 0};
  int v_3[] =  { 0, 2,  0, 0, 0, -1,  0, 0};
  int v_4[] =  { 0, 4,  0, 0, 0, -2, -1, 0};
  int v_5[] =  { 0, 6,  0, 0, 0, -3, -2, 0};
  int v_6[] =  { 0, 8,  1, 0, 0, -4, -4, 0};
  int v_7[] =  { 0, 8,  2, 0, 0, -5, -6, 0};
  int v_8[] =  { 0, 9,  4, 0, 0, -6, -8, 0};
  int v_9[] =  { 0, 10, 6, 0, 0, -7, -10, 0};
  int v_10[] = { 0, 12, 8, 0, 0, -8, -12, 0};
  int vel2[] = {t, t, t, t, t, t, t, t, t, t};

  array8(q_1, v_1);
  array8(q_2, v_2);
  array8(q_3, v_3);
  array8(q_4, v_4);
  array8(q_5, v_5);
  array8(q_6, v_6);
  array8(q_7, v_7);
  array8(q_8, v_8);
  array8(q_9, v_9);
  array8(q_10, v_10);
  array10(vel, vel2);

  runStateVariables();
}

void array8(int arg0[8], int arg1[8]) {
  arg0[0] = arg1[0];
  arg0[1] = arg1[1];
  arg0[2] = arg1[2];
  arg0[3] = arg1[3];
  arg0[4] = arg1[4];
  arg0[5] = arg1[5];
  arg0[6] = arg1[6];
  arg0[7] = arg1[7];

}

void array10(int arg0[10], int arg1[10]) {
  arg0[0] = arg1[0];
  arg0[1] = arg1[1];
  arg0[2] = arg1[2];
  arg0[3] = arg1[3];
  arg0[4] = arg1[4];
  arg0[5] = arg1[5];
  arg0[6] = arg1[6];
  arg0[7] = arg1[7];
  arg0[8] = arg1[8];
  arg0[9] = arg1[9];
}

void initServos() {

  q[0] = motors[0];
  q[1] = motors[1];
  q[2] = motors[2];
  q[3] = motors[3];
  q[4] = motors[4];
  q[5] = motors[5];
  q[6] = motors[6];
  q[7] = motors[7];

  int timer = 100;
  servo_0.attach(PIN_SERVO_0, MIN_RANGE_SERVO_0, MAX_RANGE_SERVO_0);
  writeServos(timer);
  servo_1.attach(PIN_SERVO_1, MIN_RANGE_SERVO_1, MAX_RANGE_SERVO_1);
  writeServos(timer);
  servo_2.attach(PIN_SERVO_2, MIN_RANGE_SERVO_2, MAX_RANGE_SERVO_2);
  writeServos(timer);
  servo_3.attach(PIN_SERVO_3, MIN_RANGE_SERVO_3, MAX_RANGE_SERVO_3);
  writeServos(timer);
  servo_4.attach(PIN_SERVO_4, MIN_RANGE_SERVO_4, MAX_RANGE_SERVO_4);
  writeServos(timer);
  servo_5.attach(PIN_SERVO_5, MIN_RANGE_SERVO_5, MAX_RANGE_SERVO_5);
  writeServos(timer);
  servo_6.attach(PIN_SERVO_6, MIN_RANGE_SERVO_6, MAX_RANGE_SERVO_6);
  writeServos(timer);
  servo_7.attach(PIN_SERVO_7, MIN_RANGE_SERVO_7, MAX_RANGE_SERVO_7);
  writeServos(timer);
}


void writeServos(int timer) {
  int* servos = estadoRecebido();
  servo_0.writeMicroseconds(map(servos[1], 0, 180, MIN_RANGE_SERVO_0, MAX_RANGE_SERVO_0));
  servo_1.writeMicroseconds(map(servos[2], 0, 180, MIN_RANGE_SERVO_1, MAX_RANGE_SERVO_1));
  servo_2.writeMicroseconds(map(servos[3], 0, 180, MIN_RANGE_SERVO_2, MAX_RANGE_SERVO_2));
  servo_3.writeMicroseconds(map(servos[4], 0, 180, MIN_RANGE_SERVO_3, MAX_RANGE_SERVO_3));
  servo_4.writeMicroseconds(map(servos[5], 0, 180, MIN_RANGE_SERVO_4, MAX_RANGE_SERVO_4));
  servo_5.writeMicroseconds(map(servos[6], 0, 180, MIN_RANGE_SERVO_5, MAX_RANGE_SERVO_5));
  servo_6.writeMicroseconds(map(servos[7], 0, 180, MIN_RANGE_SERVO_6, MAX_RANGE_SERVO_6));
  servo_7.writeMicroseconds(map(servos[8], 0, 180, MIN_RANGE_SERVO_7, MAX_RANGE_SERVO_7));
  delay(timer);
  enviar(String(servos[1]));
  
}

void runStateVariables() {

  setStateVariable(q_1, vel[0]);
  setStateVariable(q_2, vel[1]);
  setStateVariable(q_3, vel[2]);
  setStateVariable(q_4, vel[3]);
  setStateVariable(q_5, vel[4]);
  setStateVariable(q_6, vel[5]);
  setStateVariable(q_7, vel[6]);
  setStateVariable(q_8, vel[7]);
  setStateVariable(q_9, vel[8]);
  setStateVariable(q_10, vel[9]);
}

void setStateVariable(int val[], int timer) {

  motors[0] = q[0] + val[0];
  motors[1] = q[1] + val[1];
  motors[2] = q[2] + val[2];
  motors[3] = q[3] + val[3];
  motors[4] = q[4] + val[4];
  motors[5] = q[5] + val[5];
  motors[6] = q[6] + val[6];
  motors[7] = q[7] + val[7];

  writeServos(timer);

}

bool verificarMsg() {

  if (buffer[0] != 'I')
    return false;
  if (buffer[1] != '[')
    return false;
  if (buffer[3] != ']')
    return false;
  if (buffer[4] != '[')
    return false;
  if (buffer[7] != ']')
    return false;
  if (buffer[8] != '[')
    return false;
  if (buffer[11] != ']')
    return false;
  if (buffer[12] != '[')
    return false;
  if (buffer[16] != ']')
    return false;
  if (buffer[17] != 'F')
    return false;

  return true;

}

void decifrarMsg() {

  if (verificarMsg()) {
    char opcode;
    char codigo[3];
    char id[3];
    char value[4];

    opcode = buffer[2];
    strncpy( codigo, buffer + 5, 2 );
    strncpy( id, buffer + 9, 2 );
    strncpy( value, buffer + 13, 3 );

    codigo[2] = '\0';
    id[2] = '\0';
    value[3] = '\0';


    if (opcode == 'M' ) {
      int tempId = atoi(id);
      int tempVal = atoi(value);
      motors[tempId] = tempVal;
      //  Serial.print(tempId);
      //  Serial.print(" ");
      //  Serial.println(tempVal);
    }

    /* Serial.print("opcode: ");
       Serial.print(opcode);
       Serial.print(" codigo: ");
       Serial.print(atoi(codigo));
       Serial.print(" id: ");
       Serial.print(atoi(id));
       Serial.print(" value: ");
       Serial.print(atoi(value));
      // Serial.print(" val: ");
      // Serial.println(val);*/

  }

}
void process() {

  if ( inputString.length() != 0 && (inputString.length() >= BUFFER_SIZE )) {
    //serial = true;

    //Serial.println(inputString);
    for (int i = 0; i < BUFFER_SIZE; i++) {
      buffer[i]  = inputString.charAt(i);
    }

    if (verificarMsg()) {
      decifrarMsg();
      initBuffer();
      inputString.remove(0, BUFFER_SIZE);
    }

  }

}
void initBuffer() {
  blink_led();
  for (int i = 0; i < BUFFER_SIZE; i++) {
    buffer[i] = ' ';
  }


  Serial.print(motors[0]);
  Serial.print(" ");
  Serial.print(motors[1]);
  Serial.print(" ");
  Serial.print(motors[2]);
  Serial.print(" ");
  Serial.print(motors[3]);
  Serial.print(" ");
  Serial.print(motors[4]);
  Serial.print(" ");
  Serial.print(motors[5]);
  Serial.print(" ");
  Serial.print(motors[6]);
  Serial.print(" ");
  Serial.print(motors[7]);
  Serial.println(" ");
}

void led_on() {
  digitalWrite(13, 1);
}
void led_off() {
  digitalWrite(13, 0);
}

void blink_led() {
  led_on();
  delay(100);
  led_off();
}

