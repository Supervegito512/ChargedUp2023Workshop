package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
   
    public class Arm extends SubsystemBase {
      private DoubleSolenoid Army;
      private static Arm instance;
      
      public Arm() {
        Army= new DoubleSolenoid(0, PneumaticsModuleType.REVPH, 1, 2);
      } 
      
      public static Arm getInstance() {
        if (instance == null) {
          instance = new Arm();
        }
        return instance;
      }
  
      public void armReach(){
        Army.set(Value.kForward);
      }
      
      public void armRetract(){
        Army.set(Value.kReverse);
      }
      
@Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
    }
