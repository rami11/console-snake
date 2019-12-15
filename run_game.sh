if [ ! -e script_out ]
then
	mkdir script_out
fi
cd src
javac *.java -d ../script_out/.
cd ../script_out
java core.GameStarter