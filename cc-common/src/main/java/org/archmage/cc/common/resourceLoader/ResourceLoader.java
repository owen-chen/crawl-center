/*
 * Create Author  : chen.chen.9
 * Create Date    : 2013-8-13
 * File Name      : ResourceLoader.java
 */

package org.archmage.cc.common.resourceLoader;

import java.io.IOException;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.Assert;
import org.springframework.util.ResourceUtils;

/**
 * A {@link ResourcePatternResolver} implementation that is able to resolve a
 * specified resource location path into one or more matching Resources.
 * <p>
 * 
 * @author : chen.chen.9
 * @date : 2013-8-13
 */
public class ResourceLoader extends PathMatchingResourcePatternResolver {
    /** LOGGER */
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Override
    public Resource[] getResources(String locationPattern) throws IOException {
        Assert.notNull(locationPattern, "Location pattern must not be null");
        if (locationPattern.startsWith(CLASSPATH_ALL_URL_PREFIX)) {
            // a class path resource (multiple resources for same name possible)
            if (getPathMatcher().isPattern(locationPattern.substring(CLASSPATH_ALL_URL_PREFIX.length()))) {
                // a class path resource pattern
                return findPathMatchingResources(locationPattern);
            }
            else {
                // all class path resources with the given name
                return findAllClassPathResources(locationPattern.substring(CLASSPATH_ALL_URL_PREFIX.length()));
            }
        }
        else if (locationPattern.startsWith(CLASSPATH_URL_PREFIX)) {
            // Only look for a pattern after a prefix here
            // (to not get fooled by a pattern symbol in a strange prefix).
            int prefixEnd = locationPattern.indexOf(":") + 1;
            if (getPathMatcher().isPattern(locationPattern.substring(prefixEnd))) {
                // a file pattern
                return findPathMatchingResources(locationPattern);
            }
            else {
                // a single resource with the given name
                return new Resource[] { getResourceLoader().getResource(locationPattern) };
            }
        }
        else {
            // a single resource with the given name
            return new Resource[] { getResourceLoader().getResource(ResourceUtils.FILE_URL_PREFIX + locationPattern) };
        }
    }

    @Override
    public Resource getResource(String location) {
        Resource[] resources = null;
        try {
            resources = getResources(location);
        }
        catch (IOException e) {
            LOGGER.error(ExceptionUtils.getStackTrace(e));
        }

        if (ArrayUtils.isNotEmpty(resources)) {
            return resources[0];
        }
        else {
            return null;
        }
    }
}
