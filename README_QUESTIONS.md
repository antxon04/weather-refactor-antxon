# Preguntas y respuestas

- ¿Qué has empezado implementando y por qué?

  He comenzado analizando la clase principal de WeatherForecast y haciendome un esquema en papel de cómo podria organizar este proyecto.
  
  Después estructuré el código en paquetes (App, Client, Exceptions, Mapper, Model, Service y Util) y ya luego empecé a implementar los DTOs con los datos que devuelve la API.

- ¿Qué problemas te has encontrado al implementar los tests y cómo los has solventado?

  El problema principal ha sido evitar las llamadas reales hacia la API, no queria que los tests dependiesen de la conexión y tuviesen alguna conexión externa, para ello lo he solucionado usando "Mockito" para simular el cliente HTTP mediante un mock, y devolver las respuestas controladas.

  El otro problema ha sido cubrir todos los casos, para ello gracias a la ejecución de tests mediante Coverage pude ver el % de las lineas que se ejecutaban y poder realizar el 100%.

- ¿Qué componentes has creado y por qué?

  Principalmente he creado 4 componentes:
  1. Los DTOs, que se encargan de recibir la información de la API para poder devolverlo luego como objeto al usuario.
  2. El cliente, que se encarga de la consulta a la API y poder hacer una peticion con datos de entrada como es la latitud y longitud y luego devuelve unos datos de salida que guardo en los DTOs.
  3. El mapper, que tiene un método encargado de recibir el weatherCode que se le pase y devuelva la predicción del tiempo dependiendo de ese código.
  4. El servicio, tiene toda la lógica y es la clase principal, se encarga de recibir una latitud, longitud que le enviara al cliente para hacer el fetch y recibir unos datos, y luego también se le pasa una fecha que es el día el cual queremos buscar la predicción del tiempo.

- Si has utilizado dependencias externas, ¿por qué has escogido esas dependencias?

  1. Apache HttpClient: Lo he usado para realizar peticiones HTTP de manera sencilla, gestionando automáticamente errores y conexiones. Es una librería muy popular y confiable, en la que ya he manejado anteriormente y estoy mas acostumbrado.
  2. Gson: Lo uso para convertir el JSON que me devuelve la API a objetos de Java y es una librería que me parece sencilla, flexible y muy confiable actualmente.
  3. Lombok: Lo utilizo para reducir el código repetitivo como los getters, setters, constructores o el toString(). Ayuda a hacer el codigo mas limpio y facil de mantener.
  4. Mockito: Gracias a esta dependencia puedo crear mocks y simular el comportamiento de dependencias en los tests, permitiendome probar el código de forma aislada sin hacer conexiones externas y controlar diferentes escenarios que pueden suceder.

- ¿Has utilizado streams, lambdas y optionals de Java 8 o superior? ¿Qué te parece la programación funcional?

  Sí, he utilizado Optionals para manejar valores que pueden estar vacíos, evitando errores por null o string vacíos. Facilita el manejo de excepciones y devolver información clara al usuario.

  La programación funcional me parece muy util porque permite escribir código mas limpio, expresivo y breve. Ayuda a manejar colecciones y datos opcionales de forma mas segura y a reducir los errores.

- ¿Qué piensas del rendimiento de la aplicación?

  Pienso que todavía se podría mejorar en caso de varias peticiones simultaneas, para evitar que haya cuello de botella y se pierda información de por medio o directamente que pueda tirar el servidor. Además se podría dividir algunas clases en funciones más pequeñas para mejorar todavía mas la eficiencia y mantenimiento.

- ¿Qué harías para mejorar el rendimiento si esta aplicación fuera a recibir al menos 100 peticiones por segundo?

  Mejoraría el rendimiento simplemente ordenando las peticiones aplicando caché, si varias peticiones piden el mismo dato devolvería el resultado almacenado en memoria sin volver a llamar a la API.

- ¿Cuánto tiempo has invertido para implementar la solución?

  Aproximadamente 16 horas.

- ¿Crees que en un escenario real valdría la pena dedicar tiempo a realizar esta refactorización?

  Claro que si, refactorizar me parece fundamental para conseguir un código limpio, facil de mantener, identificar dublicidad de código o para diferentes mejoras que se podrian implementar. Nunca viene de mas gastar algo de tiempo para refactorizar el código.
