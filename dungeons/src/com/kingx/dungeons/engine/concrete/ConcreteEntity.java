package com.kingx.dungeons.engine.concrete;

import java.util.ArrayList;
import java.util.List;

import com.kingx.artemis.Component;
import com.kingx.artemis.Entity;
import com.kingx.artemis.World;

public abstract class ConcreteEntity {
    private final World world;
    private Entity entity;
    protected final List<Component> bag = new ArrayList<Component>();

    public ConcreteEntity(World world) {
        this.world = world;
    }

    public Entity getEntity() {
        return createEntity();
    }

    public Entity createEntity() {
        if (entity == null) {
            entity = world.createEntity();
            for (Component c : bag) {
                entity.addComponent(c);
            }
        }
        return entity;
    }

}
