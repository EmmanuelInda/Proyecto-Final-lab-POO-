OS := $(shell uname)

run: program
	java -Dsun.java2d.uiScale=1.0 -Dsun.java2d.dpiaware=true -cp out Main

# This target compiles the Java source files into the "out" directory
program:
	javac -d out \
		src/*.java \
		src/game/*.java \
		src/game/components/*.java \
		src/game/data/*.java \
		src/game/logic/*.java \
		src/ui/*.java
