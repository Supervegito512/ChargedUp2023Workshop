package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Ports;


public class Claw extends SubsystemBase {
  private DoubleSolenoid clawy;
  private DoubleSolenoid Wrist;
  private static Claw instance;

  private Claw() {
    clawy = new DoubleSolenoid(Ports.PNEUMATIC_MODULE, PneumaticsModuleType.REVPH, Ports.CLAW_GRAB, Ports.CLAW_RELEASE);
  }

  public static Claw getInstance() {
    if (instance == null) {
      instance = new Claw();
    }
    return instance;
  }

  public void ClawGrab() {
    clawy.set(Value.kForward);
  }
  
  public void ClawRelease() {
    clawy.set(Value.kReverse);
  }
  
 public void SetLow() {
  Wrist.set(Value.kForward);
 }

 public void SetHigh(){
  Wrist.set(Value.kReverse);
 }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  

  }
}
