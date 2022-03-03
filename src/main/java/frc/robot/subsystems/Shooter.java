
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
  WPI_TalonFX topShooter = new WPI_TalonFX(Constants.topShooterMotorChannel);
  WPI_TalonFX botShooter = new WPI_TalonFX(Constants.botShooterMotorChannel);
  //public DoubleSolenoid shooterSolenoid = new DoubleSolenoid(PneumaticsModuleType.REVPH, Constants.ballUp, Constants.ballDown);
  
  public Shooter() {
    topShooter.configFactoryDefault();
    topShooter.setNeutralMode(NeutralMode.Coast);
    topShooter.setInverted(false);
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
    topShooter.set(ControlMode.Velocity, topRPM * 2048 / 600);
    botShooter.set(ControlMode.Velocity, botRPM * 2048 / 600);
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

    if(topShooterReady && botShooterReady) {
      return true;
    } else {
      return false;
    }
  }
  
  public void ballUp() {
    //shooterSolenoid.set(Value.kForward);
  }

  public void retract() {
    //shooterSolenoid.set(Value.kReverse);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
