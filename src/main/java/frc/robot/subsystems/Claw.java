package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
   
    public class Claw extends SubsystemBase {
      private DoubleSolenoid Clawy;
      private static Claw instance;
      
      public Claw() {
        Clawy= new DoubleSolenoid(0, PneumaticsModuleType.REVPH, 1, 2);
      } 
      
      public static Claw getInstance() {
        if (instance == null) {
          instance = new Claw();
        }
        return instance;
      }
  
      public void ClawRelease(){
        Clawy.set(Value.kForward);
      }
      
      public void ClawGrab(){
        Clawy.set(Value.kReverse);
      }
      
@Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

public void stop() {
}
    }
