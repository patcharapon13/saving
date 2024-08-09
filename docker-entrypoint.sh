#!/bin/bash

echo $(ls -1)
echo $(ls -1 /usr/src/app/libs)
echo $(ls -1 /usr/src/app)

exec java -jar /usr/src/app/libs/saving.jar