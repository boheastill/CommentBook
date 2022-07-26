package com.github.boheastill.pd2.util

/**13/5应该分三页*/
fun groupDiv(all: Int, lenth: Int): Int {
    var model = all % lenth
    if (model == 0) {
        return all / lenth
    } else {
        return all / lenth + 1
    }
}