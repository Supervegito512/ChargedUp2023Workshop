// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Camera;
import frc.robot.subsystems.Drivetrain;

public class ALine extends CommandBase {
  /** Creates a new ALine. */
  private Drivetrain drivetrain;
  private Camera camera;
  private double yaw;

  public ALine() {
    // Use addRequirements() here to declare subsystem dependencies.
    drivetrain = Drivetrain.getInstance();
    camera = Camera.getInstance();
    yaw = camera.getGridLLCam().getYaw();
    addRequirements(drivetrain,camera);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    drivetrain.drive(0, 0, 0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    yaw = camera.getGridLLCam().getYaw();
    
    if(yaw > 0.75){
      drivetrain.drive(0, -0.5, 0);
    }

    else if(yaw < -0.75){
      drivetrain.drive(0, 0.5, 0);
    }

    else{
        drivetrain.drive(0, 0, 0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drivetrain.drive(0, 0, 0);

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
