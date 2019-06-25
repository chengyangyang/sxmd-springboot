package com.sxmd.bean;

import com.sxmd.helper.EntityHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.Table;

/**
 * Description: 使用spring 获取注解类
 *
 * @author cy
 * @date 2019年06月19日 17:56
 * Version 1.0
 */
@Component
@Scope(value="singleton") // 单例
public class SpringAnnotationBean implements BeanFactoryPostProcessor, ApplicationContextAware, ApplicationRunner {

    Logger log = LoggerFactory.getLogger(SpringAnnotationBean.class);

    private  ApplicationContext applicationContext;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        Scanner scanner = new Scanner((BeanDefinitionRegistry) configurableListableBeanFactory);
        scanner.setResourceLoader(this.applicationContext);
        scanner.scan("com");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            this.applicationContext = applicationContext;
    }

    public void getTableBean() throws BeansException {
        EntityHelper.intTableInfo(applicationContext.getBeansWithAnnotation(Table.class));
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.debug("===============服务启动成功===================");
        getTableBean();
    }
}
