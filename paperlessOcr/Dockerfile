FROM amazoncorretto:21-alpine-jdk

ENV TESSDATA_PREFIX /app/tessdata

# Install Tesseract
RUN apk --no-cache add tesseract-ocr
RUN mkdir -p /app/tessdata \ && wget -O /app/tessdata/eng.traineddata https://github.com/tesseract-ocr/tessdata/raw/main/eng.traineddata

WORKDIR /app

# Install Maven (if not already installed)
RUN apk --no-cache add maven

# copy sourcecode
COPY paperlessOcr /app
#COPY ./src/main/java/com/paperless/2023-11-28_17-04.png 2023-11-28_17-04.png

# Build the application --> skit tests for now
RUN mvn package -DskipTests

RUN ls -la target

EXPOSE 7000

CMD ["java","-jar","target/paperlessOcr-0.0.1-SNAPSHOT.jar"]