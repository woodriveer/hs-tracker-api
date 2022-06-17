#!/bin/bash
echo "Starting to call local API and request heroes information"
FILE=$1
cat $FILE | while read line
do
  A="$(cut -d'/' -f5 <<<"$line")"
  echo "Starting to process hero $A"
  curl --location --request GET "http://localhost:8081/heroes/$A"
  printf "\n"
done