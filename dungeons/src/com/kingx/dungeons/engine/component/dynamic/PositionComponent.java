package com.kingx.dungeons.engine.component.dynamic;

import com.badlogic.gdx.math.Vector3;

public class PositionComponent extends AbstractComponent {
    public Vector3 vector;

    public PositionComponent(float x, float y, float z) {
        this.vector = new Vector3(x, y, z);
    }

    public PositionComponent(Vector3 vector) {
        this.vector = vector;
    }

    @Override
    public String toString() {
        return "PositionComponent [x=" + vector.x + ", y=" + vector.y + ", z=" + vector.z + "]";
    }

    @Override
    public void setComponent(int id, int value) {
        switch (id) {
            case 0:
                this.vector.x = value / AbstractComponent.INT_TO_FLOAT;
                break;
            case 1:
                this.vector.y = value / AbstractComponent.INT_TO_FLOAT;
                break;
            case 2:
                this.vector.z = value / AbstractComponent.INT_TO_FLOAT;
                break;
        }

    }

    public float getX() {
        return vector.x;
    }

    public float getY() {
        return vector.y;
    }

    public float getZ() {
        return vector.z;
    }

    public void setX(float x) {
        vector.x = x;
    }

    public void setY(float y) {
        vector.y = y;
    }

    public void setZ(float z) {
        vector.z = z;
    }

}