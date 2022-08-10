FROM python:3
RUN apt-get update
RUN apt-get install git
RUN git clone https://github.com/sqlmapproject/sqlmap.git

ENTRYPOINT ["python","sqlmap/sqlmapapi.py","-s","--host=0.0.0.0"]
