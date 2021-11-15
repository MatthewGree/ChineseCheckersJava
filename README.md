# ChineseCheckersJava
Implementation of chinese checkers in java language. Consists of server and client, clients can play with each other through server socket
on port 4999, it's also possible to play with a web client: https://github.com/MatthewGree/ChineseCheckersWeb, but then server needs to route 
port 4888 to port 4999 by websockify (https://github.com/novnc/websockify). Instructions on how to use available in polish language in file INSTRUKCJA.pdf.

Basic instructions:
1. Run mvn install in project root
2. Run server jar with one of the arguments : TwoPlayerChineseCheckers, ThreePlayerChineseCheckers, FourPlayerChineseCheckers, SixPlayerChineseCheckers.
3. Launch required number of clients specified in step 2.

<img src=https://i.imgur.com/mX5422l.png/>
