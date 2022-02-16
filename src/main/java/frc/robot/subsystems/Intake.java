
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
  WPI_TalonSRX intakeMotor = new WPI_TalonSRX(Constants.intakeMotorChannel);
  //DoubleSolenoid intakeSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.intakeDown, Constants.intakeUp);
  
  public Intake() {

  }

  public void intake(double spinSpeed) {
    intakeMotor.set(spinSpeed);
    //intakeSolenoid.set(Value.kForward);
  }

  public void reverseIntake(double spinSpeed) {
    intakeMotor.set(-spinSpeed);
  }

  public void stopIntake() {
    intakeMotor.set(0);
    //intakeSolenoid.set(Value.kReverse);
  }

  @Override
  public void periodic() {
    
  }
}
