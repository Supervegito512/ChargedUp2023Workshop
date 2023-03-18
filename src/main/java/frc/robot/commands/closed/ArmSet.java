// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.closed;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.ProfiledPIDCommand;
import frc.robot.Constants.OutConstants;
import frc.robot.subsystems.Arm;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ArmSet extends ProfiledPIDCommand {

  public enum ArmPosition {
    LOW(78),
    MID(15),
    HIGH(20);

    public final double value;

    private ArmPosition(double position) {
      this.value = position;
    }
  }

  /** Creates a new ArmSet. */
  public ArmSet(ArmPosition position) {
    super(
        // The ProfiledPIDController used by the command
        new ProfiledPIDController(
            // The PID gains
            OutConstants.ARM_PID.kP,
            OutConstants.ARM_PID.kI,
            OutConstants.ARM_PID.kD,
            // The motion profile constraints
            new TrapezoidProfile.Constraints(OutConstants.ARM_VELOCITY, OutConstants.ARM_ACCELERATION)),
        // This should return the measurement
        () -> Arm.getInstance().getPosition(),
        // This should return the goal (can also be a constant)
        position.value,
        // This uses the output
        (output, setpoint) -> {
          // Use the output (and setpoint, if desired) here
          Arm.getInstance().move(output);
        });
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
    getController().setTolerance(OutConstants.ARM_POSITION_TOLERANCE);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return getController().atGoal();
  }
}
