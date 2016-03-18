SD=http://java.sun.com/j2se/1.4.1/docs/api

R=org
DM=$(R)/leplus
PM=$(R).leplus
DC=$(DM)/channel
PC=$(PM).channel
DLC=$(DM)/libcrypto
PLC=$(PM).libcrypto
DLCT=$(DLC)/test
PLCT=$(PLC).test

JAR=DSASubliminal.jar

CD=classes
ED=doc

all: jar documentation

jar: binary
	rm -f $(JAR)
	jar cvfm $(JAR) Manifest -C $(CD) $(R)
	rm -Rf $(CD)

binary: main libcrypto

main:
	mkdir -p $(CD)
	javac -d $(CD) $(DC)/*.java

libcrypto:
	mkdir -p $(CD)
	javac -d $(CD) $(DLC)/*.java

documentation:
	mkdir -p $(ED)
	javadoc -public -use -version -author -linksource -link $(SD) -footer "<p>`date`</p>" -d $(ED) $(PLC)

clean:
	rm -Rf $(CD) $(ED) core* *~
