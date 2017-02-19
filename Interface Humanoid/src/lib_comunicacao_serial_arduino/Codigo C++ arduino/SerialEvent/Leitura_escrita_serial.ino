/**
   nao usar nenhuma funcao de leitura ou escrita que espera o timeout do arduino
   por exempo: readString();
   isso atraza o processamento do arduino muito;
   utilizar as funcoes deste arquivo;
   buffer do arduino de 64 bytes, nao ultrapassar isso
*/

String strLida = "";
boolean arduinoConectado = false;

String ler() {
  String s = strLida;
  strLida = "";
  return s;
}

/**
   metodo usado para enviar uma string "str" para o pc
*/
void enviar(String str) {
  String inicio = "!i";
  String final = "!e";
  int tamanho = str.length();
  if (tamanho < 10) {
    final += "0";
    final += tamanho;
  } else final += tamanho;

  inicio += str;
  inicio += final;
  Serial.print(inicio);

}


/**
   este metodo eh executado todo final de loop se caso tenha dados na serial
*/
void serialEvent() {
  delay(30);
  char c;
  String s = "";
  int caso = 1;
  int strLength = 0;
  boolean readOk = false;
  while (Serial.available() > 0) {
    delay(2);
    c = (char)Serial.read();
    switch (caso) {
      case 1: {
          if (c == '!')
            caso = 2;
          break;
        }
      case 2: {
          if (c == 'i')
            caso = 3;
          else caso = 0;
          break;
        }
      case 3: {
          if ( c != '!')
            s += c;
          else caso = 4;
          break;
        }
      case 4: {
          if (c == 'e')
            caso = 5;
          else caso = 7;
          break;
        }
      case  5: {
          strLength = ((int)c - 48) * 10;
          caso = 6;
          break;
        }
      case 6: {
          strLength += (int)c - 48;
          if (s.length() == strLength) {
            if (s.equals("start")) arduinoConectado = true;
            if (s.equals("close")) arduinoConectado = false;
            if (!s.equals("ok") &&
                !s.equals("close") &&
                !s.equals("start") ) strLida = s;
            readOk = true;
            enviar("readOk");
          }
          break;
        }
      case 7: {
          s = "";
          caso = 8; // terminar de limpar o buffer de entrada
          strLength = 0;
          break;
        }
      case 8: {
          break;
        }
    }
  }

  if (!readOk) enviar("readFail");

  while ((Serial.available() == 0) && arduinoConectado) {

  }
}

boolean isArduinoConectado(){
  return arduinoConectado;
}


