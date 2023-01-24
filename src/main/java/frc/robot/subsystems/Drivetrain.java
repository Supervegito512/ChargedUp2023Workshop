// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import org.ejml.data.MatrixType;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Rotation2d;
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
  
  private AHRS navX;

  private boolean fieldMode;

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

      navX = new AHRS(SPI.Port.kMXP);

      navX.reset();

      fieldMode = true;
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

  public void drive(double xSpeed, double ySpeed, double rotSpeed, boolean fieldMode){
    xSpeed *= SwerveDriveConstants.TOP_SPEED;
    ySpeed *= SwerveDriveConstants.TOP_SPEED;
    rotSpeed *= SwerveTurnConstants.TOP_ANGULAR_SPEED;

    ChassisSpeeds speeds = fieldMode ? 
      ChassisSpeeds.fromFieldRelativeSpeeds(xSpeed, ySpeed, rotSpeed, getHeading()) : 
      new ChassisSpeeds(xSpeed, ySpeed, rotSpeed);
    
    SwerveModuleState[] states = driveKinematics.toSwerveModuleStates(speeds);
    SwerveDriveKinematics.desaturateWheelSpeeds(states, SwerveDriveConstants.TOP_SPEED);

    swerveFL.setState(states[0]);
    swerveFR.setState(states[1]);
    swerveBL.setState(states[2]);
    swerveBR.setState(states[3]);
  }

  public void resetIMU() {
    navX.reset();
  }

  public Rotation2d getRawHeading() {
    return Rotation2d.fromDegrees(navX.getAngle());
  }

  public Rotation2d getHeading() {
    return Rotation2d.fromRadians(MathUtil.angleModulus(getRawHeading().getRadians()));
  }

  public boolean getFieldMode() {
    return fieldMode;
  }

  public void setFieldMode(boolean fieldMode) {
    this.fieldMode = fieldMode;
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

    SmartDashboard.putNumber("FL Drive Position", swerveFL.getPosition().distanceMeters);
    SmartDashboard.putNumber("FR Drive Position", swerveFR.getPosition().distanceMeters);
    SmartDashboard.putNumber("BL Drive Position", swerveBL.getPosition().distanceMeters);
    SmartDashboard.putNumber("BR Drive Position", swerveBR.getPosition().distanceMeters);

    SmartDashboard.putNumber("FL Desired Angle", swerveFL.desiredAngle);
    SmartDashboard.putNumber("FR Desired Angle", swerveFR.desiredAngle);
    SmartDashboard.putNumber("BL Desired Angle", swerveBL.desiredAngle);
    SmartDashboard.putNumber("BR Desired Angle", swerveBR.desiredAngle);

    SmartDashboard.putNumber("FL Optimized Angle", swerveFL.optimizedAngle);
    SmartDashboard.putNumber("FR Optimized Angle", swerveFR.optimizedAngle);
    SmartDashboard.putNumber("BL Optimized Angle", swerveBL.optimizedAngle);
    SmartDashboard.putNumber("BR Optimized Angle", swerveBR.optimizedAngle);

    SmartDashboard.putNumber("Robot Angle", getHeading().getDegrees());
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    updateTelemetry();
  }
}
