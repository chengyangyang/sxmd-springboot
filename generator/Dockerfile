
# 基于Java 8 进行构建
FROM java:8
# 映射的端口
EXPOSE 8096
# 挂载
VOLUME /tmp
# 接收项目mvn 打包接受的参数  暂时不能使用这个 要使用Docker-Compose
ARG JAR_FILE
# 在镜像中创建一个目录
RUN mkdir /worker
# 将jar 文件复制到工作目录中
ADD target/generator-1.0-SNAPSHOT.jar  /worker/app.jar
# 修改文件的访问时间
RUN bash -c 'touch /worker/app.jar'
# 启动java文件
ENTRYPOINT ["java","-jar","/worker/app.jar"]




