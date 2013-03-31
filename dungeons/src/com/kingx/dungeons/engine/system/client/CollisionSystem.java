package com.kingx.dungeons.engine.system.client;

import com.kingx.artemis.Aspect;
import com.kingx.artemis.ComponentMapper;
import com.kingx.artemis.Entity;
import com.kingx.artemis.annotations.Mapper;
import com.kingx.artemis.systems.EntityProcessingSystem;
import com.kingx.dungeons.engine.component.CollisionComponent;
import com.kingx.dungeons.engine.component.dynamic.PositionComponent;
import com.kingx.dungeons.engine.component.dynamic.SizeComponent;
import com.kingx.dungeons.geom.Collision;

public class CollisionSystem extends EntityProcessingSystem {
    @Mapper
    ComponentMapper<PositionComponent> postionMapper;
    @Mapper
    ComponentMapper<SizeComponent> sizeMapper;
    @Mapper
    ComponentMapper<CollisionComponent> collisionMapper;

    public CollisionSystem() {
        super(Aspect.getAspectForAll(PositionComponent.class, SizeComponent.class, CollisionComponent.class));
    }

    @Override
    protected void process(Entity e) {
        PositionComponent position = postionMapper.get(e);
        SizeComponent size = sizeMapper.get(e);
        CollisionComponent collision = collisionMapper.get(e);

        collision.setUp(Collision.resolveCollisionUp(position, size));
        collision.setDown(Collision.resolveCollisionDown(position, size));
        collision.setRight(Collision.resolveCollisionRight(position, size));
        collision.setLeft(Collision.resolveCollisionLeft(position, size));

        System.out.println(collision);
    }

}
