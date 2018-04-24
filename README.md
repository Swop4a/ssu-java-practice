 <h1>SSU Java course tasks</h1>

<h2>Calculator</h2>

Common calculator with console input dialog. 
Powered by JavaScript engine. 
Could be launched as Java application from Intellij Idea or through console command `java -jar %app%.jar`or `mvn spring-boot:run`

<h2>Garage</h2>

Application provide operations for manipulations garage system

Realized as one artifact with spring profiles. Available profiles: rest, console, db

Application can be started from Intellij Idea or through console command `java -jar %app%.jar` or `mvn spring-boot:run`

1. Console application with JSON datasource. Run with `-Dspring.profiles.active=console`
2. Console application with DB (H2 in-memory database) datasource. Run with `-Dspring.profiles.active=console, db`
3. Rest service + React UI with DB datasource. Run without profiles. Front started: 
    1. `cd C:\ssu-java-practice\garage\src\main\resources\front`
    2. `yarn`
    3. `yarn start`
  
Start on http://localhost:8082/api  

<h2>Chat</h2>

Chat was implemented throught Spring WebSocket.

Application can be started from Intellij Idea or through console command `java -jar %app%.jar` or `mvn spring-boot:run`

Front as static content located in {project.dir}/chat/src/main/resources/static

Start on http://localhost:8088/
