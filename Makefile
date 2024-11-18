run: program
	java -cp out Main

program:
	javac -d out src/*.java src/game/*.java src/ui/*.java

