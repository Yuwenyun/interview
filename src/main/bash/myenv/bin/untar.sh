#!/bin/bash

myenv_home=`dirname $0`/..
echo ${myenv_home}

# import the properties variables
source ${myenv_home}/config/default.properties

# check services in services.list
if [ -s ${myenv_home}/config/services.list ]; then
  while read line
  do
    if [ -z ${line} ]; then
      continue
    fi

    # prepare the variables
    svsKVPair=(${line//=/ })
    keyName=(${svsKVPair[0]//_/ })
    pkgName=$(basename ${svsKVPair[1]})

    # service installed as dir exists
    if [ -d ${svsKVPair[1]} ]; then
      echo ${svsKVPair[1]} installed...
    else
      # if the tar package exists, untar it
      if [ -s ${myenv_home}/services/${pkgName}.tar ]; then
        echo Untar ${myenv_home}/services/${pkgName[0]}.tar to ${svsKVPair[1]}
        mkdir -p ${svsKVPair[1]}
        tar -xvzPf ${myenv_home}/services/${pkgName[0]}.tar
      else
        echo ${myenv_home}/services/${pkgName[0]}.tar not exist...
        continue
      fi
    fi

    # check the softlink
    if [ -L ${link_home}/${keyName} ]; then
      rm ${link_home}/${keyName}
    fi
    echo Create softlink ${link_home}/${keyName}
    ln -s ${svsKVPair[1]} ${link_home}/${keyName}
  done <${myenv_home}/config/services.list
else
  echo ${myenv_home}/config/services.list not exists...
fi

# untar the package that not listed in services.list

