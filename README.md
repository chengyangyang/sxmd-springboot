# sxmd-springboot
主旨：求易去难，解放开发。   

优点：
1.大大提升开发效率。
2.去除复杂的单表通用crud，使每个开发者都能看懂。
3.易扩展，好理解。

本项目使用最新的spring boot2.1.5.RELEASE 版本，对spring和springmvc和mybatis 进行整合。
项目包含： 通用单表crud，代码生成。

项目框架：mybatis3.5 + spring5.1.7 + springMvc + mysql8

一. 项目架构  

1.common java 的公用模块
2.generator  代码生成模板 （支持mysql/postgresql 的生成）
3.mybatis-plug  对mybatis 进行封装  通用单表的增删查改和扩展（现仅支持mysql，初学者都很容易改造成自己需要的数据库版本）
