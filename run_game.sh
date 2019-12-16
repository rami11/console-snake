if [ ! -e script_out ]; then
	mkdir script_out
fi
find . | grep .java > source.txt
javac @source.txt -d script_out/
rm source.txt
java -cp script_out/ core.GameStarter
