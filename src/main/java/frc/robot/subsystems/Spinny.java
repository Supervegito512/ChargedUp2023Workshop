// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Ports;

public class Spinny extends SubsystemBase {
  private CANSparkMax spinnyMotor;
  private static Spinny instance;
  /** Creates a new lazysusan. */
  public Spinny() {
    spinnyMotor=new CANSparkMax(Ports.SPINNY_PORT, MotorType.kBrushless);
    spinnyMotor.setInverted(false);
  }

  public static Spinny getInstance() {
    if (instance == null) {
      instance = new Spinny();
    }
    return instance;
  }

  public void clockwise() {
    spinnyMotor.set(1);
  }

  public void counterclockwise() {
    spinnyMotor.set(-1);
  }

  public void stop() {
    spinnyMotor.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
