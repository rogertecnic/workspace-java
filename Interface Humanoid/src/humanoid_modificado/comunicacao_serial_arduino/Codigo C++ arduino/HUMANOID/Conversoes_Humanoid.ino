int *estadoDosServos = motors ; // ponteiro global que vai guardar o array de valores dos servos,
/**
   funcao retorna um ponteiro para o array do angulo dos servos, o primeiro elemento [0] eh o tamanho do array;
   se caso ainda nao foi recebido nenhum array utilizavel, ele vai ser um array de um elemento sendo: {0};
   utilizar assim:
   int* servos = estadoRecebido();
   pode-se acessar os valores no array normalmente, acessando o valor na quarta posicao do array (terceiro motor):
   servos[3];
   lembrando que:
   servos[0]: tamanho do array;
   servos[1]: primeiro motor;
   servos[2]: segundo motor;
   .
   .
   .
  servos[pservos[0]-1]: ultimo motor;
*/
int* estadoRecebido() {
  String s = ler();
  if (!s.equals("")) {
    // contando o numero de servos
    int numeroDeServos;
    for (int i = 0; i < s.length(); i++)
      if (s.charAt(i) == ';') numeroDeServos ++;

    // deletando o array anterior, se nao deletar manualmente uma instancia "new" ela fica na memoria (nao tenho certeza sobre isso)
    delete estadoDosServos;

    // lendo os valores de cada servo e guardando em uma nova instancia int[] que eh atribuido ao ponteiro global
    estadoDosServos = new int[numeroDeServos + 1];
    int indexInicio = 0;
    int indexFinal;
    estadoDosServos[0] = numeroDeServos + 1;
    for (int i = 0; i < numeroDeServos; i++) {
      indexFinal = s.indexOf(';', indexInicio); // a partir do index "i" (incluindo-o)
      String substr = s.substring(indexInicio, indexFinal);
      estadoDosServos[i + 1] = substr.toInt();
      indexInicio = indexFinal + 1;
    }

    s = "";
  }
  return estadoDosServos;
}

void resetArrayServos(){
  delete estadoDosServos;
  estadoDosServos = motors;
}

