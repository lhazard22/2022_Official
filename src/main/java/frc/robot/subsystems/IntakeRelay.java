
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeRelay extends SubsystemBase {
  public WPI_TalonFX intakeMotor = new WPI_TalonFX(Constants.intakeMotorChannel);
  DoubleSolenoid intakeSolenoid = new DoubleSolenoid(20,PneumaticsModuleType.REVPH, Constants.intakeDown, Constants.intakeUp);

  public IntakeRelay() {
  }

  public void intake(double spinSpeed) {
    intakeMotor.set(spinSpeed);
    intakeSolenoid.set(Value.kReverse);
  }

  public void reverseIntake(double spinSpeed) {
    intakeMotor.set(-spinSpeed);
  }

  public void stopIntake() {
    intakeMotor.set(0);
    intakeSolenoid.set(Value.kForward);
  }

  public void runRelay(double spinSpeed) {
    intakeMotor.set(spinSpeed);
  }

  public void stopRelay() {
    intakeMotor.set(0);
  }

  @Override
  public void periodic() {
    
  }
}
