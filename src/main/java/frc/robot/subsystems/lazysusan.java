// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class lazysusan extends SubsystemBase {
  private CANSparkMax lazysusan;
  private static lazysusan instance;
  /** Creates a new lazysusan. */
  public lazysusan() {
    lazysusan=new CANSparkMax(12, MotorType.kBrushless);
    lazysusan.setInverted(false);
    lazysusan.setIdleMode(IdleMode.kBrake);
  }

  public lazysusan getInstance() {
    if (instance == null) {
      instance = new lazysusan();
    }
    return instance;
  }

  public void clockwise() {
    lazysusan.set(1);
  }

  public void counterclockwise() {
    lazysusan.set(-1);
  }

  public void stop() {
    lazysusan.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
