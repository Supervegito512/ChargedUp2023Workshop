// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Roller extends SubsystemBase {
  /** Creates a new Intake. */
  private CANSparkMax rollerMotor;
  public static Roller instance;
  
  public Roller() {
    rollerMotor=new CANSparkMax(11, MotorType.kBrushless);
    rollerMotor.setInverted(false);
    rollerMotor.setIdleMode(IdleMode.kBrake);
}

public Roller getInstance() {
  if (instance == null) {
    instance = new Roller();
  }
  return instance;
}

public void takein() {
  rollerMotor.set(1);
}

public void takeout() {
  rollerMotor.set(-1);
}

public void stop() {
  rollerMotor.set(0);
}

@Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
