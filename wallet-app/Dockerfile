FROM golang:1.20

WORKDIR /app/

RUN apt-get update && apt-get install -y librdkafka-dev

EXPOSE 5432
# -p 5432:5432


CMD ["go", "run", "cmd/walletcore/main.go"]