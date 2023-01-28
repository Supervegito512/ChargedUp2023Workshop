// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Ports;
import frc.robot.Constants.RollerConstants;

public class Roller extends SubsystemBase {
  /** Creates a new Intake. */
  private CANSparkMax rollerMotor;
  private static Roller instance;
  private DoubleSolenoid mouth;
  

  private Roller() {
    rollerMotor=new CANSparkMax(Ports.ROLLER_PORT, MotorType.kBrushless);
    rollerMotor.setInverted(RollerConstants.ROLLER_INVENTED);
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

public static void stop() {
  rollerMotor.set(0);
}

public static void mouthclose() {
  mouth.set(Value.kForward);
}

public void mouthopen() {
  mouth.set(Value.kReverse);
}
@Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
