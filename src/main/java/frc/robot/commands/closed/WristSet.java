
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.closed;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.ProfiledPIDCommand;
import frc.robot.Constants.OutConstants;
import frc.robot.subsystems.Claw;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class WristSet extends ProfiledPIDCommand {

  public enum WristPosition {
    RETRACTED(0),
    TOP(78),
    MID(15),
    HPS(20);

    public final double value;

    private WristPosition(double position) {
      this.value = position;
    }
  }

  /** Creates a new ArmSet. */
  public WristSet(WristPosition position) {
    super(
        // The ProfiledPIDController used by the command
        new ProfiledPIDController(
            // The PID gains
            OutConstants.WRIST_PID.kP,
            OutConstants.WRIST_PID.kI,
            OutConstants.WRIST_PID.kD,
            // The motion profile constraints
            new TrapezoidProfile.Constraints(OutConstants.WRIST_VELOCITY, OutConstants.WRIST_ACCELERATION)),
        // This should return the measurement
        () -> Claw.getInstance().getWristPosition(),
        // This should return the goal (can also be a constant)
        position.value,
        // This uses the output
        (output, setpoint) -> {
          // Use the output (and setpoint, if desired) here
          Claw.getInstance().move(output);
        });
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
    getController().setTolerance(OutConstants.WRIST_POSITION_TOLERANCE);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return getController().atGoal();
  }
}
