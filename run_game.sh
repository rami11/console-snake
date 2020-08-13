find . | grep .java > source.txt
javac @source.txt -d out
rm source.txt
java -cp out core/GameStarter
