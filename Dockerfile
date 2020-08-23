FROM openjdk:14-alpine
COPY . /app
WORKDIR /app
RUN chmod a+x run_game.sh
CMD ["./run_game.sh"]
