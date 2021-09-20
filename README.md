# Meli_Validador_Dna
Servicio que detecta si un humano es mutante basándose en su secuencia de ADN.

El servicio recibe un array de Strings que representauna tabla de NxN dimensiones con la secuencia del ADN. Se restringe el 
uso de letras las cuales solo pueden ser solo pueden ser: (A,T,C,G), las cuales representan cada base nitrogenada del ADN.

la siguiente matriz muestra dos ejemplos de una matriz cuando el resultado de la validacion es positivo y negativo
<img src="/docs_img/matriz.png" alt="Matriz"/>

#Java 11
Se elaboro un servico Rest en Spring Boot, con JDK 11, IDE Spring Tools, este servicio expone 2 Metodos.

- POST → /mutant → se encarga de realizar las validacion de los DNA ingresados, adicional Guarda los registros de las peticiones realizadas en base de datos.
  Inptu: dna”:["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"] }
  Output: HTTP 200-OK "isMutant"
          HTTP 403-Forbidden "notIsMutant"
 <img src="/docs_img/validator_200.png" alt="validator_200"/>          
 <img src="/docs_img/validator_403.png" alt="validator_403"/>          

- GET → /stats → genera una estadistica de las preticiones realizadas, retorna 
  Output: {“count_mutant_dna”:40, “count_human_dna”:100: “ratio”:0.4}
<img src="/docs_img/stats.png" alt="stats"/>          
