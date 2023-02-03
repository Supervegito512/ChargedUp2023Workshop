// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

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
import frc.robot.Constants.SwerveConstants;

public class Drivetrain extends SubsystemBase {

  // an instance variable for the drivetrain
  private static Drivetrain instance;

  private final SwerveModule[] modules;

  private final SwerveDriveKinematics driveKinematics;
  
  private AHRS navX;

  private boolean fieldCentric;

  /** Creates a new Drivetrain. */
  private Drivetrain() {
    modules = new SwerveModule[4];

    modules[0] = new SwerveModule(SwerveConstants.SWERVE_FL);
    modules[1] = new SwerveModule(SwerveConstants.SWERVE_FR);
    modules[2] = new SwerveModule(SwerveConstants.SWERVE_BL);
    modules[3] = new SwerveModule(SwerveConstants.SWERVE_BR);

    driveKinematics = new SwerveDriveKinematics(
      new Translation2d(SwerveConstants.WHEEL_BASE / 2, -SwerveConstants.TRACK_WIDTH / 2),
      new Translation2d(SwerveConstants.WHEEL_BASE / 2, SwerveConstants.TRACK_WIDTH / 2),
      new Translation2d(-SwerveConstants.WHEEL_BASE / 2, -SwerveConstants.TRACK_WIDTH / 2),
      new Translation2d(-SwerveConstants.WHEEL_BASE / 2, SwerveConstants.TRACK_WIDTH / 2));

      navX = new AHRS(SPI.Port.kMXP);

      navX.reset();

      fieldCentric = true;
  }

  /**
   * Gets the single instance of drivetrain
   * @author Aiden Sing
   * @return the instance of the drivetrain
   */
  public static Drivetrain getInstance() {
    if (instance == null) {
      instance = new Drivetrain();
    }
    return instance;
  }

  /**
   * Making a drive function to make the speed for drive a fraction of total
   * @author Aiden Sing
   * @param xSpeed speed of the robot left to right
   * @param ySpeed speed of robot foward to back
   * @param rotSpeed speed of robot turning
   */
  public void drive(double xSpeed, double ySpeed, double rotSpeed) {
    xSpeed *= SwerveConstants.TOP_SPEED;
    ySpeed *= SwerveConstants.TOP_SPEED;
    rotSpeed *= SwerveConstants.TOP_ANGULAR_SPEED;

    ChassisSpeeds speeds = new ChassisSpeeds(xSpeed, ySpeed, rotSpeed);
    
    SwerveModuleState[] states = driveKinematics.toSwerveModuleStates(speeds);
    SwerveDriveKinematics.desaturateWheelSpeeds(states, SwerveConstants.TOP_SPEED);

    // setting the state for each module as an array
    for(int i = 0; i < modules.length; i++) {
      modules[i].setState(states[i]);
    }
  }

  public void drive(double xSpeed, double ySpeed, double rotSpeed, boolean fieldCentric) {
    xSpeed *= SwerveConstants.TOP_SPEED;
    ySpeed *= SwerveConstants.TOP_SPEED;
    rotSpeed *= SwerveConstants.TOP_ANGULAR_SPEED;

    ChassisSpeeds speeds = fieldCentric ? 
      ChassisSpeeds.fromFieldRelativeSpeeds(xSpeed, ySpeed, rotSpeed, getHeading()) : 
      new ChassisSpeeds(xSpeed, ySpeed, rotSpeed);
    
    SwerveModuleState[] states = driveKinematics.toSwerveModuleStates(speeds);
    SwerveDriveKinematics.desaturateWheelSpeeds(states, SwerveConstants.TOP_SPEED);

    for(int i = 0; i < modules.length; i++) {
      modules[i].setState(states[i]);
    }
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

  public boolean getFieldCentric() {
    return fieldCentric;
  }

  public void setFieldCentric(boolean fieldCentric) {
    this.fieldCentric = fieldCentric;
  }


  public void updateTelemetry() {
    for(int i = 0; i < modules.length; i++) {
      modules[i].updateTelemetry();
    }

    SmartDashboard.putNumber("Robot Angle", getHeading().getDegrees());
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    updateTelemetry();
  }
}
