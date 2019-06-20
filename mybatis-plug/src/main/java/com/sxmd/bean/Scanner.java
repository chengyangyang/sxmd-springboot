package com.sxmd.bean;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import javax.persistence.Table;
import java.util.Set;

/**
 * Description: 配置扫描
 *
 * @author cy
 * @date 2019年06月20日 11:36
 * Version 1.0
 */
public final class Scanner extends ClassPathBeanDefinitionScanner {


    public Scanner(BeanDefinitionRegistry registry) {
        super(registry);
    }
    @Override
    public void registerDefaultFilters() {
        this.addIncludeFilter(new AnnotationTypeFilter(Table.class));
    }
    @Override
    public Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitions =   super.doScan(basePackages);
        for (BeanDefinitionHolder holder : beanDefinitions) {
            GenericBeanDefinition definition = (GenericBeanDefinition) holder.getBeanDefinition();
            definition.getPropertyValues().add("innerClassName", definition.getBeanClassName());
            definition.setBeanClass(FactoryBeanHelp.class);
        }
        return beanDefinitions;
    }
    @Override
    public boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return super.isCandidateComponent(beanDefinition) && beanDefinition.getMetadata()
                .hasAnnotation(Table.class.getName());
    }

}
