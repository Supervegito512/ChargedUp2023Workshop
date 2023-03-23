// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.complex;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.closed.ArmSet;
import frc.robot.commands.closed.WristSet;
import frc.robot.commands.closed.ArmSet.ArmPosition;
import frc.robot.commands.closed.WristSet.WristPosition;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Wrarm extends ParallelCommandGroup {
  public enum ComboPosition {
    RETRACTED(ArmPosition.RETRACTED, WristPosition.RETRACTED),
    TOP(ArmPosition.TOP, WristPosition.TOP),
    MID(ArmPosition.MID, WristPosition.MID),
    HPS(ArmPosition.HPS, WristPosition.HPS);

    public final ArmPosition armPos;
    public final WristPosition wristPos;

    private ComboPosition(ArmPosition armPos, WristPosition wristPos) {
      this.armPos = armPos;
      this.wristPos = wristPos;
    }
  }
  /** Creates a new Wrarm. */
  public Wrarm(ComboPosition comboPosition) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new WristSet(comboPosition.wristPos),
      new ArmSet(comboPosition.armPos)
    );
  }
}
