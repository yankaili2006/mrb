Maven
===

####在环境上安装Maven

步骤可以参考步骤可以参考
[Maven安装步骤](http://yankaili2006.github.io/2014/03/02/maven-on-ubuntu/)


####安装官方repository里面没有的jar到本地的repo

在终端执行如下命令：

`mvn install:install-file -Dfile=/home/liyankai/github/mrb/src/main/webapp/WEB-INF/lib/sdk-java-1.0.jar -DgroupId=com.smvp -DartifactId=smvp-com -Dversion=1.0 -Dpackaging=jar -DgeneratePom=true -DcreateChecksum=true`
  

pom.xml里增加如下配置：

    <dependency>
        <groupId>com.smvp</groupId>
        <artifactId>smvp-com</artifactId>
        <version>1.0</version>
        <scope>compile</scope>
    </dependency>
    
    
MYSQL
===

####安装mysql

安装mysql并设置字符集为utf-8，具体可以参考[安装步骤](http://yankaili2006.github.io/2014/01/13/install-mysql-on-ubuntu/)


####创建数据库，并建立数据库表和初始化基础数据

    CREATE DATABASE mrb DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
    mysql -uuser -ppwd
    source ~/github/mrb/mrb.sql
    
mysqldump命令使用方法： `mysqldump -u 用户名 -p 数据库名 > 导出的文件名`


运行
===

执行如下命令

    mvn clean package
    mvn install && mvn jetty:run

