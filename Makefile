full:
	make build
	make run

build: 
	cd "$(shell pwd)/src"  && javac Urna.java

run: 
	cd "$(shell pwd)/src" && java Urna

clean:
	rm **/*.class