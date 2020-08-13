#!/bin/sh

# make sure you have docker installed on your system before running this script.

# constants
docker_file=Dockerfile
image_name=rsn11/console-snake:latest
container_name=console-snake

# build image
echo Building ${image_name}
echo "***************************************"
docker build -f ${docker_file} -t ${image_name} . \
&& echo \
&& echo Running ${image_name}
echo "***************************************"
docker run --rm -it --name ${container_name} ${image_name} \
    /bin/sh run_game.sh
