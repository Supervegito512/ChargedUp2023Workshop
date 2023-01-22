package frc.robot.subsystems;


import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class LEDStrips extends SubsystemBase {
    private static Spark Led = new Spark(Constants.RobotConstants.BLINKIN_PORT);
}
