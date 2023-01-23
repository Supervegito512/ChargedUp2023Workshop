// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.utils;

import java.util.List;
import org.photonvision.PhotonCamera;
import org.photonvision.targeting.TargetCorner;
import edu.wpi.first.math.geometry.Transform3d;

/** Add your docs here. */
public class AprilCam {

    private PhotonCamera camera;

    public AprilCam(String cameraName) {
        camera = new PhotonCamera(cameraName);
    }

    public double getHorizontalOffset() {
        var result = camera.getLatestResult();
        double offset = result.getBestTarget().getYaw();
        return offset;
    }

    public double getPitch() {
        var result = camera.getLatestResult();
        double pitch = result.getBestTarget().getPitch();
        return pitch;
    }

    public double getArea() {
        var result = camera.getLatestResult();
        double area = result.getBestTarget().getArea();
        return area;
    }

    public List<TargetCorner> getCorners() {
        var result = camera.getLatestResult();
        List<TargetCorner> corners = result.getBestTarget().getDetectedCorners();
        return corners;
    }

    public Transform3d getCameraToTarget() {
        var result = camera.getLatestResult();
        Transform3d camToTarget = result.getBestTarget().getBestCameraToTarget();
        return camToTarget;
    }

    public int getFiducialId() {
        var result = camera.getLatestResult();
        int ficId = result.getBestTarget().getFiducialId();
        return ficId;
    }

    public double getPoseAmbiguity() {
        var result = camera.getLatestResult();
        double ambiguity = result.getBestTarget().getPoseAmbiguity();
        return ambiguity;
    }

    public Transform3d getAltCam() {
        var result = camera.getLatestResult();
        Transform3d altCam = result.getBestTarget().getAlternateCameraToTarget();
        return altCam;
    }
}
