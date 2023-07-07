cd framework/src
javac -parameters -d . *.java
jar -cvf ../../ETU1850.jar ETU1850
copy ../../ETU1850.jar ../../test-framework/WEB-INF/lib/

set CLASSPATH=.;C:\Program Files\Apache Software Foundation\Tomcat 9.0\webapps\ETU1850.jar

cd ../../test-framework/WEB-INF/classes
javac -parameters -d . *.java
cd ../../
jar -cvf ../test-framework.war .

cd ../
pause
