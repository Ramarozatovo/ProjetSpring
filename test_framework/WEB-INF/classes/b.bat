export: CLASSPATH.:/home/
source ~/.bathrc

javac -d . *.javac
jar -cvf ../framework/WEB-INF/lib/ETU1850.jar ETU1850

cd ../framework/WEB-INF/classes/

export CLASSPATH=.:/home/tovo/ITU/code

javac -d . *.javac
cd ../../
jar .cvf ./framework .war

