package com.vava.bean;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author steve
 */
public class ColorFactoryBean implements FactoryBean<Color> {

    public Color getObject() throws Exception {
        return new Color();
    }

    public Class<?> getObjectType() {
        return Color.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }


}
