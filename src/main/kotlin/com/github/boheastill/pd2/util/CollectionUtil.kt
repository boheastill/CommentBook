package com.github.boheastill.pd2.util

/**
 * 集合工具
 *
 * @author wangchao
 * @date 2019/12/14
 */
object CollectionUtil {
    /**
     * 是否空
     *
     * @param collection 集合
     * @return boolean
     */
    fun isEmpty(collection: Collection<*>?): Boolean {
        return collection == null || collection.isEmpty()
    }

    /**
     * 是否不空
     *
     * @param collection 集合
     * @return boolean
     */
    fun isNotEmpty(collection: Collection<*>?): Boolean {
        return collection != null && !collection.isEmpty()
    }

    /**
     * 包含任何
     *
     * @param coll1 coll1
     * @param coll2 coll2
     * @return boolean
     */
    fun containsAny(coll1: Collection<*>, coll2: Collection<*>): Boolean {
        if (coll1.size < coll2.size) {
            for (aColl1 in coll1) {
                if (coll2.contains(aColl1)) {
                    return true
                }
            }
        } else {
            for (aColl2 in coll2) {
                if (coll1.contains(aColl2)) {
                    return true
                }
            }
        }
        return false
    }
}