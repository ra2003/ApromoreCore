package org.apromore.cache.ehcache;

import org.apromore.cache.Cache;
import org.junit.Assert;
import org.junit.Test;

public class EhCacheManagerTest {

    @Test
    public void testGetCache() throws Exception {
        EhCacheManager cacheManager = new EhCacheManager();

        try {
            Cache<Object, Object> someCache = cacheManager.getCache("someCache");
            Assert.assertNotNull(someCache);

            final String key = "key";
            final String value = "value";
            Assert.assertNull(someCache.put(key, value));
            Assert.assertEquals(value, someCache.get(key));
        } finally {
            cacheManager.destroy();

        }
    }
}