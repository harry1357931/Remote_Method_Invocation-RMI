Remote Method Invocation:
=========================
Java Remote Method Invocation (Java RMI) enables the programmer to create distributed Java technology-based 
to Java technology-based applications, in which the methods of remote Java objects can be invoked from 
other Java virtual machines, possibly on different hosts. This repository contains working code of  RMI 
Client and RMI Server Side.<br>
Source: Oracle.com <br>
Here are few Images to understand the RMI Client Server Model:
<img src="http://upload.wikimedia.org/wikipedia/commons/thumb/b/ba/RMI-Stubs-Skeletons.svg/798px-RMI-Stubs-Skeletons.svg.png" alt="RMI Structure_Image1" width="" height="">
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<img src="http://lycog.com/wp-content/uploads/2011/03/java-rmi-overview.png" alt="RMI Structure_Image2" width="" height="">

The Java Remote Method Invocation Application Programming Interface (API), or Java RMI, is a Java API 
that performs the object-oriented equivalent of remote procedure calls (RPC), with support for direct 
transfer of serialized Java objects and distributed garbage collection. <br>
Source: Wikipedia<br>

Generally speaking, there are three main classes that matter: RMIServer, RMIClient, RMI_Interface.

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
