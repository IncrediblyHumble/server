# server
Look at maven_commands_to_know.txt for installations and running

One note on running: The last command is
```
mvn exec:java -D exec.mainClass=com.incredibly_humble.server.Main -Dexec.args="/home/noam/Desktop/incredibly_humble/server/db/"
```
In this command, you will need to change 

```
-Dexec.args="/home/noam/Desktop/incredibly_humble/server/db/"
```

to
```
-Dexec.args="path/to/server/db"
```
That is, the path in quotes should lead to the folder "db" which is location in the folder for this repo (where the repo is on your computer).

In emergencies (like a demo), you can go into the main file and remove 
```
path = args[0];
System.out.println(path);
```
And manually set the path variable there. But do not commit this change becaues it will cause annoying bugs for everyone else.
