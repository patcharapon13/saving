FROM ubuntu:latest
LABEL authors="timmy"

ENTRYPOINT ["top", "-b"]