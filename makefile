build:
	mvn compile

package:
	mvn assembly:single

test:
	mvn test

docker-build:
	docker-compose build

docker-run:
	docker-compose up

build-run:
	mvn compile
	mvn assembly:single
	docker-compose build
	docker-compose up

docker-build-run:
	docker-compose build
	docker-compose up