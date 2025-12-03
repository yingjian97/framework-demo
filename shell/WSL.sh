# IP
ifconfig | grep eth0 -n1 | grep inet | awk '{print $3}'