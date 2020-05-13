package com.vava.net.tokens;

import com.vava.net.EntityNet;

/**
 * @author Steve
 * Created on 2020-05
 */
public class OriginNetOp extends NetOp {
    private EntityNet entityNet;

    public OriginNetOp(EntityNet entityNet) {
        this.entityNet = entityNet;
    }

    @Override
    EntityNet execute() {
        return null;
    }
}
