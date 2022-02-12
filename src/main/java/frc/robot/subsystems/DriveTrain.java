
package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {
  public CANSparkMax flDrive = new CANSparkMax(Constants.flDriveMotorChannel, MotorType.kBrushless); //3
  public CANSparkMax flSteer = new CANSparkMax(Constants.flSteerMotorChannel, MotorType.kBrushless); //4
  public CANSparkMax frDrive = new CANSparkMax(Constants.frDriveMotorChannel, MotorType.kBrushless); //6
  public CANSparkMax frSteer = new CANSparkMax(Constants.frSteerMotorChannel, MotorType.kBrushless); //5
  public CANSparkMax blDrive = new CANSparkMax(Constants.blDriveMotorChannel, MotorType.kBrushless); //2
  public CANSparkMax blSteer = new CANSparkMax(Constants.blSteerMotorChannel, MotorType.kBrushless); //1
  public CANSparkMax brDrive = new CANSparkMax(Constants.brDriveMotorChannel, MotorType.kBrushless); //7
  public CANSparkMax brSteer = new CANSparkMax(Constants.brSteerMotorChannel, MotorType.kBrushless); //8
  public DutyCycleEncoder blEncoder = new DutyCycleEncoder(Constants.blEncoderChannel);
  public DutyCycleEncoder flEncoder = new DutyCycleEncoder(Constants.flEncoderChannel);
  public DutyCycleEncoder frEncoder = new DutyCycleEncoder(Constants.frEncoderChannel);
  public DutyCycleEncoder brEncoder = new DutyCycleEncoder(Constants.brEncoderChannel);

  public DriveTrain() {
    flDrive.setInverted(false);
    flSteer.setInverted(true);
    frDrive.setInverted(false);
    frSteer.setInverted(true);
    blDrive.setInverted(false);
    blSteer.setInverted(true);
    brDrive.setInverted(false);
    brSteer.setInverted(true);
    brSteer.setIdleMode(CANSparkMax.IdleMode.kBrake);
    blSteer.setIdleMode(CANSparkMax.IdleMode.kBrake);
    frSteer.setIdleMode(CANSparkMax.IdleMode.kBrake);
    flSteer.setIdleMode(CANSparkMax.IdleMode.kBrake);
  }
  
  public double getPosition(double rawAngle, double offset) {
    double offsetRot = offset / 360;
    double angle = rawAngle - offsetRot;
    double angleDeg = (angle % 1) * 360;
    if (angleDeg < 0) {
      angleDeg = angleDeg + 360;
    }

    return angleDeg;
  }

  public double getRevPosition(double angle) {
    if (angle >= 180) {
      return angle - 180;
    } else {
      return angle + 180;
    }
  }

  public double getOptimalRoute(double dif1, double dif2, double dif3, double dif4) {
    if (dif1 <= dif2 && dif1 <= dif3 && dif1 <= dif4) {
      return 1;
    } else if (dif2 <= dif1 && dif2 <= dif3 && dif2 <= dif4) {
      return 2;
    } else if (dif3 <= dif1 && dif3 <= dif2 && dif3 <= dif4) {
      return 3;
    } else if (dif4 <= dif1 && dif4 <= dif2 && dif4 <= dif3) {
      return 4;
    } else {
      return 0;
    }
  }

  public void drive(CANSparkMax driveMotor, double speed,CANSparkMax steerMotor, double spinTolerance, double value, double dif1, double dif2, double dif3, double dif4) {
    if (value == 1) {
      driveMotor.set(speed);
      if (dif1 > spinTolerance) {
        driveMotor.set(0.07);
      } else {
        driveMotor.set(0);
      }
    } else if (value == 2) {
      driveMotor.set(speed);
      if (dif2 > spinTolerance) {
        driveMotor.set(-0.07);
      } else {
        driveMotor.set(0);
      }
    } else if (value == 3) {
      driveMotor.set(-speed);
      if (dif3 > spinTolerance) {
        driveMotor.set(0.07);
      } else {
        driveMotor.set(0);
      }
    } else if (value == 4) {
      driveMotor.set(-speed);
      if (dif4 > spinTolerance) {
        driveMotor.set(-0.07);
      } else {
        driveMotor.set(0);
      }
    }
  }

  @Override
  public void periodic() {

  }
}
