# sxmd-springboot
主旨：求易去难。   去除复杂的单表通用crud，让每个开发者都能看懂，易扩展，好理解，编写属于自己的通用接口等。

本项目使用最新的spring boot2.1.5.RELEASE 版本，对spring和springmvc和mybatis 进行整合。
项目包含： 通用单表crud，代码生成。

项目框架：mybatis3.5 + spring5.1.7 + springMvc + mysql8

一. 项目架构  文档的整理

1.common java 的公用模块
2.generator  代码生成模板
3.mybatis-plug  对mybatis 进行封装  通用单表的增删查改和扩展（现仅支持mysql）
