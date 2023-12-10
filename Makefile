up-dev:
	 docker-compose -f adapters/docker-compose.adapters.yml up -d --build && \
	 docker-compose -f docker-compose.dev.yml up -d --build

down-dev:
	docker-compose -f docker-compose.dev.yml down -v && \
    docker-compose -f adapters/docker-compose.adapters.yml down -v
