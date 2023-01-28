// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.utils;

import java.util.List;

import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;
import org.photonvision.targeting.TargetCorner;
import edu.wpi.first.math.geometry.Transform3d;

/** Add your docs here. */
public class AprilCam {

    private PhotonCamera camera;
    private PhotonPipelineResult result;
    private PhotonTrackedTarget bestTarget;

    public AprilCam(String cameraName) {
        camera = new PhotonCamera(cameraName);
        update();
    }

    public void update() {
        this.result = camera.getLatestResult();
        this.bestTarget = result.getBestTarget();
    }

    public double getYaw() {
        return bestTarget.getYaw();
    }

    public double getPitch() {
        return bestTarget.getPitch();
    }

    public double getArea() {
        return bestTarget.getArea();
    }

    public List<TargetCorner> getCorners() {
        return bestTarget.getDetectedCorners();
    }

    public Transform3d getCameraToTarget() {
        return bestTarget.getBestCameraToTarget();
    }

    public int getFiducialId() {
        return bestTarget.getFiducialId();
    }

    public double getPoseAmbiguity() {
        return bestTarget.getPoseAmbiguity();
    }

    public Transform3d getAltCam() {
        return bestTarget.getAlternateCameraToTarget();
    }
}
