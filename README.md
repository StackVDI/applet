applet
======

Applet java to exec rdp client

Is mandatory to be signed to use it 

  javac appletvdi.java 
  jar cvf appletvdi.jar appletvdi.java
  keytool -genkey -alias stackvdi -v
  jarsigner appletvdi.jar stackvdi --verbose

