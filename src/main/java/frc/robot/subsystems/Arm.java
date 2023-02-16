package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Ports;
import frc.robot.Constants.ArmConstants;

public class Arm extends SubsystemBase {

  private CANSparkMax armMotor;
  private static Arm instance;

  private Arm() {
    armMotor = new CANSparkMax(Ports.ARM, MotorType.kBrushless);
    armMotor.setInverted(ArmConstants.IS_INVERTED);
  }

  public static Arm getInstance() {
    if (instance == null) {
      instance = new Arm();
    }
    return instance;
  }

  public void reach() {
    armMotor.set(ArmConstants.DEFAULT_SPEED);
  }

  public void retract() {
    armMotor.set(-ArmConstants.DEFAULT_SPEED);
  }

  public void stop() {
    armMotor.set(0);
  }

  @Override
  public void periodic() {
  }
}
