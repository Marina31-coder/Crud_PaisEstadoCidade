## Bem vindo ao projeto de cadastro de sistemas com a BE_Next_955

### Execução do projeto
Esse projeto foi construído com base no Java 17 e para tal precisa da JVM correspondente para execução.
Certifique-se de que existe o arquivo crud.properties na raiz do projeto, pois o mesmo é utilizado para configurar a execução.

Exemplo de arquivo:
```
sistema.controller.tipo=DEFINITIVO
sistema.persistencia.tipo=XML
```
#### sistema.controller.tipo
Define o tipo de controller a ser utilizado. Valores possíveis são: DEFINITIVO ou VOLATIL

#### sistema.persistencia.tipo
Define o tipo de persistência a ser utilizada. Valores possíveis são: XML ou BINARIA