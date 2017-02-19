void setup() {
  pinMode(13, OUTPUT);
  // initialize serial:
  Serial.begin(9600);
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
for(int i = 0; i < servos[0]; i++)
enviar(String( servos[i]));
//if(servos.length > 0) enviar("ok");




// este codigo esta funcionando com o eclipse normalmente
//  delay(1000);
//  Serial.print("!iTeste s!e07");
//  delay(3000);
//  Serial.print("!ifinalLoop!e09");

}


