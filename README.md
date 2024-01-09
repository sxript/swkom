# Setup 
## Starting in Docker

 In the "PaperlessRest" and "PaperlessOCR" projects, replace all localhost attributes in the application-properties with the respective Docker service name when starting everything inside Docker containers.

Navigate to the project root directory and execute the following command in the terminal: `docker-compose up --build`

# Services
![image](https://github.com/sxript/swkom/assets/45437000/307df8ec-5fdd-4914-9948-f4ab13180609)


# Project Workflow:
### 1. Upload PDF File:

- Users upload a PDF file through the application interface.

### 2. Storage in Minio:

- The uploaded PDF file is stored in Minio, ensuring secure and efficient file storage.
### 3. Immediate Database Entry:

- Key data such as title and creation timestamp are promptly stored in the database upon upload.

### 4. Messaging via RabbitMQ:

- The database ID associated with the uploaded PDF is sent to the RabbitMQ queue.

### 5.PaperlessOCR Service Processing:
- The PaperlessOCR service monitors the RabbitMQ queue for incoming messages.

### 6. Text Extraction with Tesseract:

- Using the received ID, the PaperlessOCR service retrieves the corresponding PDF data from Minio.
- Tesseract is employed to extract content from the PDF, transforming it into machine-readable text.
### 7. Database Storage and ElasticSearch Indexing:

- Extracted content is stored in the database, creating a comprehensive record of the PDF's textual information.
- Additionally, the document is indexed in ElasticSearch for enhanced search functionality.
