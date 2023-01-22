// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Ports;
import frc.robot.Constants.RobotConstants;
import frc.robot.Constants.SwerveDriveConstants;
import frc.robot.Constants.SwerveTurnConstants;

public class Drivetrain extends SubsystemBase {

  private static Drivetrain instance;

  private final SwerveModule swerveFL;
  private final SwerveModule swerveFR;
  private final SwerveModule swerveBL;
  private final SwerveModule swerveBR;

  private final SwerveDriveKinematics driveKinematics;

  /** Creates a new Drivetrain. */
  private Drivetrain() {
    swerveFL = new SwerveModule(Ports.SWERVE_DRIVE_FL, Ports.SWERVE_TURN_FL, SwerveTurnConstants.FL_OFFSET);
    swerveFR = new SwerveModule(Ports.SWERVE_DRIVE_FR, Ports.SWERVE_TURN_FR, SwerveTurnConstants.FR_OFFSET);
    swerveBL = new SwerveModule(Ports.SWERVE_DRIVE_BL, Ports.SWERVE_TURN_BL, SwerveTurnConstants.BL_OFFSET);
    swerveBR = new SwerveModule(Ports.SWERVE_DRIVE_BR, Ports.SWERVE_TURN_BR, SwerveTurnConstants.BR_OFFSET);

    driveKinematics = new SwerveDriveKinematics(
      new Translation2d(RobotConstants.WHEEL_BASE / 2, -RobotConstants.TRACK_WIDTH / 2),
      new Translation2d(RobotConstants.WHEEL_BASE / 2, RobotConstants.TRACK_WIDTH / 2),
      new Translation2d(-RobotConstants.WHEEL_BASE / 2, -RobotConstants.TRACK_WIDTH / 2),
      new Translation2d(-RobotConstants.WHEEL_BASE / 2, RobotConstants.TRACK_WIDTH / 2));

      createInputs();
  }

  public static Drivetrain getInstance() {
    if (instance == null) {
      instance = new Drivetrain();
    }
    return instance;
  }

  public void drive(double xSpeed, double ySpeed, double rotSpeed){
    xSpeed *= SwerveDriveConstants.TOP_SPEED;
    ySpeed *= SwerveDriveConstants.TOP_SPEED;
    rotSpeed *= SwerveTurnConstants.TOP_ANGULAR_SPEED;

    ChassisSpeeds speeds = new ChassisSpeeds(xSpeed, ySpeed, rotSpeed);
    
    SwerveModuleState[] states = driveKinematics.toSwerveModuleStates(speeds);
    SwerveDriveKinematics.desaturateWheelSpeeds(states, SwerveDriveConstants.TOP_SPEED);

    swerveFL.setState(states[0]);
    swerveFR.setState(states[1]);
    swerveBL.setState(states[2]);
    swerveBR.setState(states[3]);
  }

  public void createInputs() {
    SmartDashboard.putNumber("P", 0.05);
  }

  public void updateTelemetry() {
    SmartDashboard.putNumber("FL Angle Deg", swerveFL.getPosition().angle.getDegrees());
    SmartDashboard.putNumber("FR Angle Deg", swerveFR.getPosition().angle.getDegrees());
    SmartDashboard.putNumber("BL Angle Deg", swerveBL.getPosition().angle.getDegrees());
    SmartDashboard.putNumber("BR Angle Deg", swerveBR.getPosition().angle.getDegrees());

    SmartDashboard.putNumber("FL Angle Rad", swerveFL.getPosition().angle.getRadians());
    SmartDashboard.putNumber("FR Angle Rad", swerveFR.getPosition().angle.getRadians());
    SmartDashboard.putNumber("BL Angle Rad", swerveBL.getPosition().angle.getRadians());
    SmartDashboard.putNumber("BR Angle Rad", swerveBR.getPosition().angle.getRadians());
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    updateTelemetry();
    swerveFL.turnController.setP(SmartDashboard.getNumber("P", 0.05));
  }
}
