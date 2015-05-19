/*
 * Create Author  : chen.chen.9
 * Create Date    : 2013-9-22
 * File Name      : ExtendedDaoSupport.java
 */

package org.archmage.cc.crawl.daosupport;

import org.archmage.cc.common.resourceLoader.ResourceLoader;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * extended dao support
 * <p>
 * 
 * @author : chen.chen.9
 * @date : 2013-9-22
 */
public class ExtendedDaoSupport {
    /** {@link JdbcTemplate} */
    private JdbcTemplate jdbcTemplate;

    /** {@link MongoTemplate} */
    private MongoTemplate mongoTemplate;

    /** {@link ResourceLoader} */
    private ResourceLoader resourceLoader;

    /**
     * getter method
     * 
     * @see ExtendedDaoSupport#jdbcTemplate
     * @return the jdbcTemplate
     */
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    /**
     * setter method
     * 
     * @see ExtendedDaoSupport#jdbcTemplate
     * @param jdbcTemplate
     *            the jdbcTemplate to set
     */
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

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

    @Override
    public String toString() {
        return "ExtendedDaoSupport [jdbcTemplate=" + jdbcTemplate + ", mongoTemplate=" + mongoTemplate + ", resourceLoader=" + resourceLoader + "]";
    }
}
