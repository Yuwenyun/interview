**myssh** is used for quick connection to target host

How to use the script.

1. install sshpass
- make sure **brew** installed on the linux box, if not, run below command
> /usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
- install sshpass
> brew install https://raw.githubusercontent.com/kadwanev/bigboybrew/master/Library/Formula/sshpass.rb

2. use sshpass in below grammer
```
sshpass -p passwd ssh acct@host
```

3. config host in config/hosts.properties, accounts and passwords in config/accounts.properties