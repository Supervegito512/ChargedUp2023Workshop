// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.complex;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.RobotContainer;
import frc.robot.commands.basic.IntakeChomp;
import frc.robot.commands.basic.IntakeEat;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoCinco extends SequentialCommandGroup {
  /** Creates a new AutoCinco. */
  public AutoCinco() {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new AutoUno(),
      new IntakeChomp(),
      new WaitCommand(1),
      new RobotContainer().pathUno,
      new IntakeEat()
    );
  }
}
