# simple-java-ssh-honeypot
simple ssh server that logs logins to the console and log file

it's based on [apache mina](https://mina.apache.org/sshd-project/index.html)

## usage
1. checkout and maven install the project OR simply download the jar from [pingunaut.com](https://pingunaut.com/weblog/wp-content/uploads/2015/07/simple-java-ssh-honeypot-0.0.1-SNAPSHOT.jar_.zip)
2. navigate into the folder where the jar is located
3. open a command line and run

> java -jar simple-java-ssh-honeypot-0.0.1-SNAPSHOT.jar

The SSH-Server is now running on port 4000. 
Try connecting to it using 
> ssh -p 4000 localhost

Try to login with any password. The login will be logged to the console and a log file.

## configuration
You can set a custom listening port by passing it as command line parameter:
> java -jar simple-java-ssh-honeypot-0.0.1-SNAPSHOT.jar 4001

Watch out: setting a port <= 1024 won't work without root permission. 
