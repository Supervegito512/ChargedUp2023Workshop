package frc.robot.utils;

import java.util.List;

import org.photonvision.PhotonCamera;
import org.photonvision.targeting.TargetCorner;

import edu.wpi.first.math.geometry.Transform3d;

public class Retroreflective {

    private PhotonCamera camera;

    public Retroreflective(String cameraName) {
        camera = new PhotonCamera(cameraName);
    }

    public double getYaw() {
        var result = camera.getLatestResult();
        double yaw = result.getBestTarget().getYaw();
        return yaw;
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

    public double getSkew() {
        var result = camera.getLatestResult();
        double skew = result.getBestTarget().getSkew();
        return skew;
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
}