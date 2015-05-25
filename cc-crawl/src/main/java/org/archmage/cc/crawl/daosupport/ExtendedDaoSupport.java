/*
 * Create Author  : chen.chen.9
 * Create Date    : 2013-9-22
 * File Name      : ExtendedDaoSupport.java
 */

package org.archmage.cc.crawl.daosupport;

import org.archmage.cc.common.resourceLoader.ResourceLoader;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * extended dao support
 * <p>
 * 
 * @author : chen.chen.9
 * @date : 2013-9-22
 */
public class ExtendedDaoSupport {
    /** {@link HibernateTemplate} */
    private HibernateTemplate hibernateTemplate;

    /** {@link MongoTemplate} */
    private MongoTemplate mongoTemplate;

    /** {@link ResourceLoader} */
    private ResourceLoader resourceLoader;

    /**
     * getter method
     * 
     * @see ExtendedDaoSupport#mongoTemplate
     * @return the mongoTemplate
     */
    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }

    /**
     * setter method
     * 
     * @see ExtendedDaoSupport#mongoTemplate
     * @param mongoTemplate
     *            the mongoTemplate to set
     */
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * getter method
     * 
     * @see ExtendedDaoSupport#resourceLoader
     * @return the resourceLoader
     */
    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }

    /**
     * setter method
     * 
     * @see ExtendedDaoSupport#resourceLoader
     * @param resourceLoader
     *            the resourceLoader to set
     */
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    /**
     * getter method
     * 
     * @see ExtendedDaoSupport#hibernateTemplate
     * @return the hibernateTemplate
     */
    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    /**
     * setter method
     * 
     * @see ExtendedDaoSupport#hibernateTemplate
     * @param hibernateTemplate
     *            the hibernateTemplate to set
     */
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Override
    public String toString() {
        return "ExtendedDaoSupport [hibernateTemplate=" + hibernateTemplate + ", mongoTemplate=" + mongoTemplate + ", resourceLoader=" + resourceLoader + "]";
    }
}
