# make sure you have docker installed on your system before running this script.

# constants
docker_file=Dockerfile
image_name=rsn11/console_snake:latest
container_name=console_snake

# build image
docker build -f ${docker_file} -t ${image_name} .

# run image
docker run --rm -it --name ${container_name} ${image_name} \
    /bin/sh run_game.sh
