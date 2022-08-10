FROM selenium/standalone-chrome
COPY ../../backend ./
COPY ../../backend/chromedriver.exe driver.exe
ENV  PATH=$PATH:/driver.exe
RUN sudo ./mvnw clean install

CMD ["java","-jar","/target/Horus-0.1.jar"]
