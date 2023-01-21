// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.utils;

/** Add your docs here. */
public class AprilCam {

    private double distance;
    private double horizontalOffset;
    private double zAngle;

    public AprilCam() {
        distance = 0.00;
        horizontalOffset = 0.00;
    }
    
    public double getDistance() {
        return distance;
    }

    public double getHorizontalOffset() {
        return horizontalOffset;
    }

    public double getZAngle() {
        return zAngle;
    }


}
