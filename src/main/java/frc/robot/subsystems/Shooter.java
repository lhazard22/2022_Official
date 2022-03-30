
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Other.SampleSmoother;

public class Shooter extends SubsystemBase {
  WPI_TalonFX topShooter = new WPI_TalonFX(Constants.topShooterMotorChannel);
  WPI_TalonFX botShooter = new WPI_TalonFX(Constants.botShooterMotorChannel);
  WPI_TalonFX upperIntakeMotor = new WPI_TalonFX(Constants.upperIntakeMotorChannel);
  DoubleSolenoid shooterSolenoid = new DoubleSolenoid(20, PneumaticsModuleType.REVPH, Constants.ballUp, Constants.ballDown);
  //NetworkTable table = NetworkTableInstance.getDefault().getTable("Limelight");
  //NetworkTableEntry ty;
  //NetworkTableEntry tx;
  //double x;
  //double y;
  //double distance;

  public Shooter() {
    topShooter.configFactoryDefault();
    topShooter.setNeutralMode(NeutralMode.Coast);
    topShooter.setInverted(true);
    topShooter.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
    topShooter.configNominalOutputForward(0, Constants.kTimeoutMs);
		topShooter.configNominalOutputReverse(0, Constants.kTimeoutMs);
		topShooter.configPeakOutputForward(1, Constants.kTimeoutMs);
		topShooter.configPeakOutputReverse(-1, Constants.kTimeoutMs);
		topShooter.config_kF(Constants.kPIDLoopIdx, Constants.kGains_Velocity.kF, Constants.kTimeoutMs);
		topShooter.config_kP(Constants.kPIDLoopIdx, Constants.kGains_Velocity.kP, Constants.kTimeoutMs);
		topShooter.config_kI(Constants.kPIDLoopIdx, Constants.kGains_Velocity.kI, Constants.kTimeoutMs);
		topShooter.config_kD(Constants.kPIDLoopIdx, Constants.kGains_Velocity.kD, Constants.kTimeoutMs);
    botShooter.configFactoryDefault();
    botShooter.setNeutralMode(NeutralMode.Coast);
    botShooter.setInverted(false);
    botShooter.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
    botShooter.configNominalOutputForward(0, Constants.kTimeoutMs);
		botShooter.configNominalOutputReverse(0, Constants.kTimeoutMs);
		botShooter.configPeakOutputForward(1, Constants.kTimeoutMs);
		botShooter.configPeakOutputReverse(-1, Constants.kTimeoutMs);
		botShooter.config_kF(Constants.kPIDLoopIdx, Constants.kGains_Velocity.kF, Constants.kTimeoutMs);
		botShooter.config_kP(Constants.kPIDLoopIdx, Constants.kGains_Velocity.kP, Constants.kTimeoutMs);
		botShooter.config_kI(Constants.kPIDLoopIdx, Constants.kGains_Velocity.kI, Constants.kTimeoutMs);
		botShooter.config_kD(Constants.kPIDLoopIdx, Constants.kGains_Velocity.kD, Constants.kTimeoutMs);
  }

  public void shoot(double topRPM, double botRPM) {
    topShooter.set(ControlMode.Velocity, topRPM * 4096 / 600);
    botShooter.set(ControlMode.Velocity, botRPM * 4096 / 600);
  }

  public void shooterOff() {
    topShooter.set(ControlMode.Velocity, 0);
    botShooter.set(ControlMode.Velocity, 0);
  }

  public double getTopAngularVelocity(){
    return topShooter.getSelectedSensorVelocity() * 600 / 2048;
  }

  public double getBotAngularVelocity(){
    return botShooter.getSelectedSensorVelocity() * 600 / 2048;
  }

  public boolean topShooterReady(double topRPM) {
    double actualTopRPM = getTopAngularVelocity();

    if (actualTopRPM > topRPM - topRPM*Constants.acceptableRpmError 
    && actualTopRPM < topRPM + topRPM*Constants.acceptableRpmError){
      return true; 
    } else {
      return false;
    }
  }

  public boolean botShooterReady(double botRPM) {
    double actualBotRPM = getBotAngularVelocity();

    if (actualBotRPM > botRPM - botRPM*Constants.acceptableRpmError 
    && actualBotRPM < botRPM + botRPM*Constants.acceptableRpmError){
      return true; 
    } else {
      return false;
    }
  }

  public boolean shooterReady(double topRPM, double botRPM) {
    boolean topShooterReady = topShooterReady(topRPM);
    boolean botShooterReady = botShooterReady(botRPM);

    System.out.println("Top " + topShooterReady);
    System.out.println("Bot " + botShooterReady);

    if(topShooterReady && botShooterReady) {
      return true;
    } else {
      return false;
    }
  }
  
  public void ballUp() {
  upperIntakeMotor.set(-0.5);  ;
  }

  public void retract() {
  upperIntakeMotor.set(0.0);
  }

  public double getDistance() {
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry ty = table.getEntry("ty");
    double y = ty.getDouble(0.0); 
    double distance = 77 /(12 * Math.tan(Math.toRadians(y+ 37)));
    
    return distance;
  }

  public double getX() {
    //return table.getEntry("tx").getDouble(0.0);
    return 0;
  }

  public void enableLights() {
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(3);
  }

  public double getEquationRPM(double distance) {
    return 1.333 * Math.pow(distance, 3) - 46.286 * Math.pow(distance, 2) + 719.095 * distance - 523.268; 
  }

  @Override
  public void periodic() {
    //System.out.println("Distance: " + getDistance());
    //System.out.println("Y: " + y);
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry ta = table.getEntry("ta");
//read values periodically
    //double x = tx.getDouble(0.0);
    double y = ty.getDouble(0.0);
    //double area = ta.getDouble(0.0);
    //System.out.println(y);
    getDistance();
  }
}
