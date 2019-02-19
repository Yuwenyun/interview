#!/bin/bash

myenv_home=`dirname $0`/..
echo ${myenv_home}

# check the services list
svsList=${myenv_home}/config/services.list
svsHome=${myenv_home}/services
if [ -r ${svsList} ]; then

  echo "About to copy the services"

  # read from services.list
  while read line
  do
    if [ -z ${line} ]; then
      continue
    fi

    # prepare the variables
    echo ${line}
    svsKVPair=(${line//=/ })
    keyName=(${svsKVPair[0]//_/ })
    serviceName=$(basename ${svsKVPair[1]})

    # tar each folder
    if [ -s ${svsHome}/${serviceName}.tar ]; then
      echo "${svsHome}/${serviceName}.tar exists..."
    else    
      if [ -d ${svsKVPair[1]} ]
      then
        tar -cvzPf ${svsHome}/${serviceName}.tar ${svsKVPair[1]}
      else
        echo ${svsKVPair[1]} not exists...
      fi
    fi
  done <${svsList}
else
  echo ${myenv_home}/config/services.list not exists...
  exit
fi
