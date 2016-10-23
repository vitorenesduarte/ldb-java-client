package org.haslab.ldb;

import org.haslab.ldb.object.reply.GCounterReply;
import org.haslab.ldb.object.reply.GSetReply;
import org.haslab.ldb.object.reply.MVMapReply;

/**
 *
 * @author Vitor Enes (vitorenesduarte ~at~ gmail ~dot~ com)
 */
public enum LDBType {

    GCOUNTER("gcounter"),
    GSET("gset"),
    MVMAP("mvmap");

    private final String type;

    LDBType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
    
    public Class<?> replyClass(){
        switch(this){
            case GCOUNTER:
                return GCounterReply.class;
            case GSET:
                return GSetReply.class;
            case MVMAP:
                return MVMapReply.class;
            default:
                throw new UnsupportedOperationException();
        }
    }
}
