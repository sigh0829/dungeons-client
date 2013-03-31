package com.kingx.dungeons.engine.system.client;

import com.kingx.artemis.Aspect;
import com.kingx.artemis.ComponentMapper;
import com.kingx.artemis.Entity;
import com.kingx.artemis.annotations.Mapper;
import com.kingx.artemis.systems.EntityProcessingSystem;
import com.kingx.dungeons.App;
import com.kingx.dungeons.engine.component.dynamic.GravityComponent;
import com.kingx.dungeons.engine.component.dynamic.PositionComponent;
import com.kingx.dungeons.engine.component.dynamic.SizeComponent;
import com.kingx.dungeons.geom.Collision;
import com.kingx.dungeons.geom.Point;
import com.kingx.dungeons.geom.Point.Int;

public class CollisionSystem extends EntityProcessingSystem {
    @Mapper
    ComponentMapper<PositionComponent> postionMapper;
    @Mapper
    ComponentMapper<SizeComponent> sizeMapper;
    @Mapper
    ComponentMapper<GravityComponent> gravityMapper;

    public CollisionSystem() {
        super(Aspect.getAspectForAll(PositionComponent.class, SizeComponent.class));
    }

    @Override
    protected void process(Entity e) {
        PositionComponent position = postionMapper.get(e);
        SizeComponent size = sizeMapper.get(e);
        resolveMove(position, size);
    }

    /**
     * Checks for collision on given position. If object is entity is colliding,
     * it is pushed to the other side
     * 
     * @param position
     *            current position of entity
     * @param size
     *            size of entity
     * @return {@code true} whether there were collision, {@code false}
     *         otherwise
     */
    protected boolean resolveMove(PositionComponent position, SizeComponent size) {

        // FIXME Nasty hack: half-size is cut off so it does not collide on borders.
        float halfSize = size.getSize() / 2f - 0.1f;
        float x = position.getScreenX();
        float y = position.getY();
        //        System.out.println("getting my x : " + x);

        float leftBound = x - halfSize;
        float rightBound = x + halfSize;
        float downBound = y - halfSize;
        float upBound = y + halfSize;

        Int leftPoint = new Point.Int((int) (leftBound / App.UNIT), (int) (y / App.UNIT));
        Int rightPoint = new Point.Int((int) (rightBound / App.UNIT), (int) (y / App.UNIT));
        Int downPoint = new Point.Int((int) (x / App.UNIT), (int) (downBound / App.UNIT));
        Int upPoint = new Point.Int((int) (x / App.UNIT), (int) (upBound / App.UNIT));

        boolean collision = false;
        if (!Collision.isWalkable(leftPoint)) {
            x = (leftPoint.x + 1) * App.UNIT + halfSize;
        } else if (!Collision.isWalkable(rightPoint)) {
            x = rightPoint.x * App.UNIT - halfSize;
        }

        if (!Collision.isWalkable(downPoint)) {
            y = (downPoint.y + 1) * App.UNIT + halfSize;
            collision = true;
        } else if (!Collision.isWalkable(upPoint)) {
            y = upPoint.y * App.UNIT - halfSize;
            collision = true;
        }

        position.setScreenX(x);
        position.setY(y);

        return collision;
    }

}
