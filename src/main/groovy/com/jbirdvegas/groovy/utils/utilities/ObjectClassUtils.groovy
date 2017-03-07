package com.jbirdvegas.groovy.utils.utilities

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jbirdvegas.groovy.utils.annotations.ClassMagic
import com.jbirdvegas.groovy.utils.internal.Applier

@ClassMagic
class ObjectClassUtils implements Applier {
    private static final Gson DENSE = new Gson()
    private static final Gson PRETTY = new GsonBuilder().setPrettyPrinting().create()

    static addJsonUtils() {
        Object.metaClass.json = { ->
            DENSE.toJson(delegate)
        }

        Object.metaClass.jsonPretty = { ->
            PRETTY.toJson(delegate)
        }
    }

    @Override
    void applyAll() {
        addJsonUtils()
    }
}
