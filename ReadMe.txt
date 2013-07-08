Generate rmi stub/skeleton for RMI client from RMI server side
==============================================================
C:\rmi_demo\server>rmic RMI_BioAPI_AsteriskJava_Server

Server side:
============
For windows (by default it runs rmiregistry at 1099, so pick any other port like 2099 for RMI server service): <br>
C:\rmi_demo\server>start rmiregistry <br>

For Linux/Unix variant: <br>
rmiregistry & <br>

C:\rmi_demo\server>java java RMI_BioAPI_AsteriskJava_Server 2099 <br>

Client side: 
============
C:\rmi_demo\client>java -Djava.security.policy=grant.policy RMI_BioAPI_Demo demo16.txt 1688  localhost my_transact demo.txt <br>

** demo.txt is the source file on remote RMI_BioAPI_AsteriskJava_server side **    <br>

** 1688 is the socket listener port for the RMI_BioAPI_AsteriskJava_client side **  <br>

** localhost is the IP location of the RMI server "RMI_BioAPI_AsteriskJava_Server **  <br>

OR <br>

C:\rmi_demo\client>java RMI_BioAPI_Demo demo16.txt 1688  localhost my_transact demo.txt <br>

Note: Start RMI_Client after starting RMI_Server

Read Comments
=============
Read Comments in <b>.java</b> files for more Information.