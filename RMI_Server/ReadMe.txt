Generate rmi stub/skeleton for RMI client from RMI server side
--------------------------------------------------------------
C:\rmi_demo\server>rmic RMI_BioAPI_AsteriskJava_Server

Server side:
------------
For windows (by default it runs rmiregistry at 1099, so pick any other port like 2099 for RMI server service):
C:\rmi_demo\server>start rmiregistry

For Linux/Unix variant:
rmiregistry &

C:\rmi_demo\server>java java RMI_BioAPI_AsteriskJava_Server 2099

Client side:
------------
C:\rmi_demo\client>java -Djava.security.policy=grant.policy RMI_BioAPI_Demo demo16.txt 1688  localhost my_transact demo.txt

** demo.txt is the source file on remote RMI_BioAPI_AsteriskJava_server side **

** 1688 is the socket listener port for the RMI_BioAPI_AsteriskJava_client side **

** localhost is the IP location of the RMI server "RMI_BioAPI_AsteriskJava_Server **


OR

C:\rmi_demo\client>java RMI_BioAPI_Demo demo16.txt 1688  localhost my_transact demo.txt

