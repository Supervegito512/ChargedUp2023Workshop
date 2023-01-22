// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class SwerveTurnConstants {
    public static final double GEER_RATTIOLI = (24.0 / 12.0) * (72.0 / 14.0);
    public static final double ANGLE_THRESHOLD = Units.degreesToRadians(2);
    
    public static final double FL_OFFSET = 0;
    public static final double FR_OFFSET = 0;
    public static final double BL_OFFSET = 0;
    public static final double BR_OFFSET = 0;

    public static final double TOP_ANGULAR_SPEED = 2 * 2 * Math.PI;
  } 

  public static class SwerveDriveConstants {
    public static final double WHEEL_DIAMETER = Units.inchesToMeters(4);
    public static final double GEER_RATTIOLI = 6.55;
    public static final double TOP_SPEED = Units.feetToMeters(9.6);
  }

  public static class RobotConstants {
    public static final double TRACK_WIDTH = Units.inchesToMeters(23);
    public static final double WHEEL_BASE = Units.inchesToMeters(23);

    public static final int BLINKIN_PORT = 0;
  }
}
