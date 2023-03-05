package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Ports;
import frc.robot.Constants.OutConstants;

public class Arm extends SubsystemBase {

  private CANSparkMax motor;
  private RelativeEncoder encoder;
  private static Arm instance;

  private Arm() {
    motor = new CANSparkMax(Ports.ARM, MotorType.kBrushless);
    motor.setInverted(OutConstants.ARM_INVERTED);
    encoder = motor.getAlternateEncoder(OutConstants.ARM_COUNTS_PER_REV);
    resetPosition();
  }

  public static Arm getInstance() {
    if (instance == null) {
      instance = new Arm();
    }
    return instance;
  }

  public double getPosition() {
    return encoder.getPosition();
  }

  public void reach() {
    motor.set(OutConstants.ARM_SPEED);
  }

  public void retract() {
    motor.set(-OutConstants.ARM_SPEED);
  }

  public void move(double speed) {
    motor.set(speed);
  }

  public void stop() {
    motor.set(0);
  }

  public void resetPosition(double pos) {
    encoder.setPosition(pos);
  }

  public void resetPosition() {
    encoder.setPosition(0);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Arm Position", getPosition());
  }
}
