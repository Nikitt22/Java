package com.example.flashlight;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;

public class LightClass {
    private boolean lightStatus = false;
    private Context context;

    public LightClass(Context context) {
        this.context = context;

    }
    public void lightOn() {

        CameraManager cameraManager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
        try {
            String cameraId = cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(cameraId, true);
            lightStatus = true;
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

    }

    public void lightOff() {
        CameraManager cameraManager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
        try {
            String cameraId = cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(cameraId, false);
            lightStatus = false;
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    public boolean isLightStatus() {
        return lightStatus;
    }
}
