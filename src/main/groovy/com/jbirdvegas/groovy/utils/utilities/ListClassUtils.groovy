package com.jbirdvegas.groovy.utils.utilities

import com.jbirdvegas.groovy.utils.internal.Applier

class ListClassUtils implements Applier {
    static def addInputOutputStreams() {
        ArrayList.metaClass.getInputStream = { ->
            if (delegate instanceof List<Byte> || delegate instanceof ArrayList<Byte>) {
                return new ByteArrayInputStream(delegate as byte[])
            } else return null
        }
    }

    @Override
    void applyAll() {
        addInputOutputStreams()
    }
}
