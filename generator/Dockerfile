
# 基于Java 8 进行构建
FROM docker.io/adoptopenjdk/openjdk8
#  接收项目mvn打包接受的参数  暂时不能使用这个 要使用Docker-Compose
ARG JAR_FILE
# 在镜像中创建一个目录
WORKDIR /worker
# 系统时间进行统一
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
# 将jar 文件复制到工作目录中
ADD target/${JAR_FILE}  /worker/app.jar
# 修改文件的访问时间
RUN bash -c 'touch /worker/app.jar'
# 启动java文件
ENTRYPOINT ["java","-jar","-Duser.timezone=GMT+8","/worker/app.jar"]




