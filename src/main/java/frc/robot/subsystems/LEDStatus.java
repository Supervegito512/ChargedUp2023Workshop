package frc.robot.subsystems;


import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Ports;
import frc.robot.Utils.LEDColors.Colors;

public class LEDStatus extends SubsystemBase {
    private static Spark led = new Spark(Ports.BLINKIN_PORT);

    //Colors indicating the pick up sequence.
    public static final Colors READY_PICKUP = Colors.DARK_GREEN;
    public static final Colors PICKUP = Colors.GREEN;
    public static final Colors DONE_PICKUP = Colors.LAWN_GREEN;

    //These colors will change
    public static final Colors ALLIANCE_BLUE = Colors.BLUE;
    public static final Colors ALLIANCE_RED = Colors.RED;


}
