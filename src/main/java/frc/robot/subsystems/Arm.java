    import edu.wpi.first.wpilibj2.command.SubsystemBase;
    private DoubleSolenoid Army;
    private static Arm instance;
    public class Arm extends SubsystemBase {
public Arm(){
      Army=(0, PneumaticsModuleType.REVPH, 1, 2);
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
    Army.set(Value.kBackwards);
  }
@Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
    }
