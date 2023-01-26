package frc.robot.subsystems;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class arm{
    public static void main(string[] args){


public class Arm extends SubsystemBase {
  
  private CANSparkMax ArmMotor;
  public static Arm instance;
 
  public Arm() {
    armMotor=new CANSparkMax(16, MotorType.kBrushless);
    armMotor.setInverted(false);
    armMotor.setIdleMode(IdleMode.kBrake);
}

public Arm getInstance() {
  if (instance == null) {
    instance = new Arm();
    }
    return instance;
}
public void reachout() {
  armMotor.set(1);
}
public void retractin() {
  armMotor.set(-1);
    }
public void stop() {
  armMotor.set(0);
}
@Override
  public void periodic() {
 }

}

    }
}