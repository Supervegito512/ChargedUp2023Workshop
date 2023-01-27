// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.utils;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.ControlType;

import edu.wpi.first.wpilibj.motorcontrol.Spark;


/** Add your docs here. */
public class LazySparkMax extends CANSparkMax {

    private double mLastSet = Double.NaN;
    
    public LazySparkMax(int deviceNumber) {
        super(deviceNumber, MotorType.kBrushless);
    }

    public LazySparkMax(int deviceNumber, MotorType type) {
        super(deviceNumber, type);
    }

    public double getLastSet() {
        return mLastSet;
    }

    @Override
    public void set(double value) {
        if (value != mLastSet) {
            mLastSet = value;
            super.set(value);
        }
    }

}
