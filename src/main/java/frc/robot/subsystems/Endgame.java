
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Endgame extends SubsystemBase {
  WPI_TalonFX barTwoWinch = new WPI_TalonFX(Constants.barTwoWinchChannel);
  WPI_TalonFX barThreeWinch = new WPI_TalonFX(Constants.barThreeWinchChannel);
  WPI_TalonFX barFourWinch = new WPI_TalonFX(Constants.barFourWinchChannel);
  //DoubleSolenoid barTwoArmSolenoid = new DoubleSolenoid(PneumaticsModuleType.REVPH, Constants.barTwoArmUp, Constants.barTwoArmDown);
  //DoubleSolenoid barTwoClampSolenoid = new DoubleSolenoid(PneumaticsModuleType.REVPH, Constants.barTwoClamp, Constants.barTwoNoClamp);
  //DoubleSolenoid barThreeArmSolenoid = new DoubleSolenoid(PneumaticsModuleType.REVPH, Constants.barThreeArmUp, Constants.barThreeArmDown);
  //DoubleSolenoid barThreeClampSolenoid = new DoubleSolenoid(PneumaticsModuleType.REVPH, Constants.barThreeClamp, Constants.barThreeNoClamp);
  //DoubleSolenoid barFourArmSolenoid = new DoubleSolenoid(PneumaticsModuleType.REVPH, Constants.barFourArmUp, Constants.barFourArmDown);
  //DigitalOutput barTwoLimitSwitch = new DigitalOutput(4);
  //DigitalOutput barThreeLimitSwitch = new DigitalOutput(5);
  //DigitalOutput barFourLimitSwitch = new DigitalOutput(6);
  Compressor compressor = new Compressor(20,PneumaticsModuleType.REVPH);


  public Endgame() {
    barTwoWinch.setNeutralMode(NeutralMode.Brake);
    barThreeWinch.setNeutralMode(NeutralMode.Brake);
    barFourWinch.setNeutralMode(NeutralMode.Brake);
    barTwoWinch.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
    barThreeWinch.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
    barFourWinch.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
  }

  /*public boolean barTwoPresence() {
    if (barTwoLimitSwitch.get()) {
      return true;
    } else {
      return false;
    }
  }

  public boolean barThreePresence() {
    if (barThreeLimitSwitch.get()) {
      return true;
    } else {
      return false;
    }
  }

  public boolean barFourPresence() {
    if (barFourLimitSwitch.get()) {
      return true;
    } else {
      return false;
    }
  }*/

  public void winchBarTwo(double winchSpeed) {
    barTwoWinch.set(winchSpeed);
  }

  public void winchBarThree(double winchSpeed) {
    barThreeWinch.set(winchSpeed);
  }

  public void winchBarFour(double winchSpeed) {
    barFourWinch.set(winchSpeed);
  }

  public void winchBarTwoOff() {
    barTwoWinch.set(0);
  }

  public void winchBarThreeOff() {
    barThreeWinch.set(0);
  }

  public void winchBarFourOff() {
    barFourWinch.set(0);
  }

  public void barTwoArmExtend() {
    //barTwoArmSolenoid.set(Value.kForward);
  }

  public void barThreeArmExtend() {
    //barThreeArmSolenoid.set(Value.kForward);
  }

  public void barFourArmExtend() {
    //barFourArmSolenoid.set(Value.kForward);
  }

  public void barTwoArmRetract() {
    //barTwoArmSolenoid.set(Value.kReverse);
  }

  public void barThreeArmRetract() {
    //barThreeArmSolenoid.set(Value.kReverse);
  }

  public void barFourArmRetract() {
    //barFourArmSolenoid.set(Value.kReverse);
  }

  public void barTwoClamp() {
    //barTwoClampSolenoid.set(Value.kForward);
  }

  public void barThreeClamp() {
    //barThreeClampSolenoid.set(Value.kForward);
  }

  public void barTwoUnClamp() {
    //barTwoClampSolenoid.set(Value.kReverse);
  }

  public void barThreeUnClamp() {
    //barThreeClampSolenoid.set(Value.kReverse);
  }

  public double getBarTwoWinchPosition() {
    return barTwoWinch.getSelectedSensorPosition();
  }

  public double getBarThreeWinchPosition() {
    return barThreeWinch.getSelectedSensorPosition();
  }

  public double getBarFourWinchPosition() {
    return barFourWinch.getSelectedSensorPosition();
  }

  public void startCompressor() {
    compressor.enableDigital();
  }

  public void compressorOff() {
    compressor.disable();
  }

  @Override
  public void periodic() {
  
  }
}
