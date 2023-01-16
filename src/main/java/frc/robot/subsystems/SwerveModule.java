package frc.robot.subsystems;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxAbsoluteEncoder.Type;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import frc.robot.Constants.SwerveDriveConstants;
import frc.robot.Constants.SwerveTurnConstants;

public class SwerveModule {
    // MOTORS!!!
    private final CANSparkMax driveMotor;
    private final CANSparkMax turnMotor;
    
    // ENCODERS!!!
    private final RelativeEncoder driveEncoder;
    private final AbsoluteEncoder turnEncoder;

    // ANGLE OFFSET!!! (distance from zero)
    private final double angleOffset;

    // PID controller for turning
    private final SparkMaxPIDController turnController;

    public SwerveModule(int driveMotorID, int turnMotorID, double angleOffset) {
        // initializing the drive and turn motors
        driveMotor = new CANSparkMax(driveMotorID, MotorType.kBrushless);
        turnMotor = new CANSparkMax(turnMotorID, MotorType.kBrushless);

        // getting the drive and turn encoders
        driveEncoder = driveMotor.getEncoder();
        turnEncoder = turnMotor.getAbsoluteEncoder(Type.kDutyCycle);

        // converting the drive factors to meters and the turn factors to radians
        driveEncoder.setVelocityConversionFactor(((Math.PI * SwerveDriveConstants.WHEEL_DIAMETER) / SwerveDriveConstants.GEER_RATTIOLI) / 60);
        driveEncoder.setPositionConversionFactor((Math.PI * SwerveDriveConstants.WHEEL_DIAMETER) / SwerveDriveConstants.GEER_RATTIOLI);
        turnEncoder.setVelocityConversionFactor(((Math.PI * 2) / SwerveTurnConstants.GEER_RATTIOLI) / 60);
        turnEncoder.setPositionConversionFactor((Math.PI * 2) / SwerveTurnConstants.GEER_RATTIOLI);

        // offset from zero
        this.angleOffset = angleOffset;

        // Getting PID (not pelvic inflamitory disease)
        turnController = turnMotor.getPIDController();
        turnController.setFeedbackDevice(turnEncoder);
    }

    /**
     * Gets the state of the swerve module
     * @author Aiden Sing
     * @return the velocity
     */ 
    public SwerveModuleState getState() {
        return new SwerveModuleState(driveEncoder.getVelocity(), new Rotation2d(turnEncoder.getPosition() - angleOffset));
    }

    /**
     * Gets the position of the swerve module
     * @author Aiden Sing
     * @return the position
     */ 
    public SwerveModulePosition getPosition() {
        return new SwerveModulePosition(driveEncoder.getPosition(), new Rotation2d(turnEncoder.getPosition() - angleOffset));
    }
    
    /**
     * Moves the swerve module
     * @author Aiden Sing
     * @param desiredState Where the module should go
     */
    public void setState(SwerveModuleState desiredState) {
        // updating the desired state using the angle offset
        desiredState.angle.plus(Rotation2d.fromRadians(angleOffset));
        
        // optimizing the state of the angle
        SwerveModuleState optimizedState = SwerveModuleState.optimize(desiredState, getState().angle);

        // running the optimized state
        driveMotor.set(optimizedState.speedMetersPerSecond / SwerveDriveConstants.TOP_SPEED);
        turnController.setReference(optimizedState.angle.getRadians(), ControlType.kPosition);
    }

    /**
     * Resets the drive encoder
     * @author Aiden Sing
     */
    public void resetEncoder() {
        driveEncoder.setPosition(0.0);
    }
}
