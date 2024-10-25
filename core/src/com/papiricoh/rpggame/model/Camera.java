package com.papiricoh.rpggame.model;

public class Camera {
    private float cameraX;
    private float cameraY;

    public void update(float newCamX, float newCamY) {
        this.cameraX = newCamX;
        this.cameraY = newCamY;
    }

    public float getCameraX() {
        return cameraX;
    }

    public float getCameraY() {
        return cameraY;
    }
}
