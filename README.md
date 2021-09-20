# Meli_Validador_Dna
Servicio que detecta si un humano es mutante basándose en su secuencia de ADN.

El servicio recibe un array de Strings que representauna tabla de NxN dimensiones con la secuencia del ADN. Se restringe el 
uso de letras las cuales solo pueden ser solo pueden ser: (A,T,C,G), las cuales representan cada base nitrogenada del ADN.

la siguiente matriz muestra dos ejemplos de una matriz cuando el resultado de la validacion es positivo y negativo
<img src="/docs_img/matriz.png" alt="Matriz"/>

# DESARROLLO Y DESPLIEGUE
Se elaboro un servico Rest en Spring Boot, con JDK 11, IDE Spring Tools, este servicio expone 2 Metodos.

- POST → /mutant → se encarga de realizar las validacion de los DNA ingresados, adicional Guarda los registros de las peticiones realizadas en base de datos.
  Endpoint: https://detetoradnmutante.uc.r.appspot.com/mutant
  Inptu: dna”:["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"] }
  Output: HTTP 200-OK "isMutant"
          HTTP 403-Forbidden "notIsMutant"
<img src="/docs_img/validator_200.png" alt="validator_200"/>          

- GET → /stats → genera una estadistica de las preticiones realizadas, retorna 
  Endpoint: https://detetoradnmutante.uc.r.appspot.com/stats
  Output: {“count_mutant_dna”:40, “count_human_dna”:100: “ratio”:0.4}
<img src="/docs_img/stats.png" alt="stats"/>          

- Se usa una base de dado MySql aprovisionada en Google Cloud Con la siguiente estructura: 
<img src="/docs_img/db_structure.png" alt="db_structure"/>          
<img src="/docs_img/gcloud.png" alt="gcloud"/>          

- Este servicio de desplego en Google Cloud en APP Engine
<img src="/docs_img/gcloud2.png" alt="gcloud2"/>          

# PRUEBAS UNITARIAS
para este proyecto de se realizo pruebas unitarias mediante JUnit5 y Mockito
Se realizo el analisis de las pruenas unitarias por medio de COVERAGE
El informer se realiza bajo la tecnologia de JACOCO

<img src="/docs_img/jacoco1.png" alt="jacoco1"/>          
<img src="/docs_img/jacoco2.png" alt="jacoco2"/>          
<img src="/docs_img/jacoco3.png" alt="jacoco3"/>          
<img src="/docs_img/jacoco4.png" alt="jacoco4"/>          

# Elaborado por Javier Andres Celis para Mercado Libre.
