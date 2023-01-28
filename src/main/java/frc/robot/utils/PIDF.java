// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.utils;

/** Add your docs here. */
public class PIDF { 

    public final double kP;
    public final double kI;
    public final double kD;
    public final double kF;
    

    public PIDF (double kP) {

        this.kP = kP;

        kI = 0.0;
        kD = 0.0;
        kF = 0.0;
        
    }

    public PIDF (double kP, double kI, double kD) {

        this.kP = kP;
        this.kI = kI;
        this.kD = kD;

        kF = 0.0;
        
    }

    public PIDF (double kP, double kI, double kD, double kF) {

        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
        this.kF = kF;
        
    }


}

