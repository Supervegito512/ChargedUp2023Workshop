// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Roller;

public class IntakeRetract extends CommandBase {
  
  private Roller Roller;
 
  /** Creates a new IntakeRetract. */
  public IntakeRetract(Roller Roller) {
    // Use addRequirements() here to declare subsystem dependencies.
   Roller = Roller.getInstance();
  addRequirements(Roller);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Roller.stop();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Roller.mouthclose();
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Roller.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

