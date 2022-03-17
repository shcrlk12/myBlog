FROM tomcat:9-jre8-alpine

COPY server.xml /usr/local/tomcat/conf

COPY catalina.properties /usr/local/tomcat/conf

# war 파일 복사
COPY target/*.war /usr/local/tomcat/webapps/ROOT.war

ENV JAVA_OPTS="-DsvrNo=4"
ENV TZ=Asia/Seoul
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

RUN rm -r /usr/local/tomcat/webapps/ROOT
EXPOSE 80

#FROM ubuntu:18.04
#ARG WAR_FILE=target/*.war
#
#RUN apt-get update
#RUN apt-get install -y openjdk-8-jdk
#RUN wget https://dlcdn.apache.org/tomcat/tomcat-9/v9.0.59/bin/apache-tomcat-9.0.59.tar.gz
#RUN tar xvf apache-tomcat-9.0.59.tar.gz
#RUN mv apache-tomcat-9.0.59 tomcat-9.0
#RUN rm tomcat-9.0/conf/server.xml
#COPY server.xml tomcat-9.0/conf/server.xml
#RUN chmod 600 tomcat-9.0/conf/server.xml
#COPY ${WAR_FILE} tomcat-9.0/webapps/dockerTest4-0.0.1-SNAPSHOT.war
#
#ENTRYPOINT ["./tomcat-9.0/bin/catalina.sh", "run"]
