run: program
	java -Dsun.java2d.uiScale=1 -Dsun.java2d.dpiaware=true -cp out Main console

program:
	javac -d out \
					src/*.java \
					src/game/*.java \
					src/game/keys/*.java \
					src/game/words/*.java \
					src/ui/*.java
