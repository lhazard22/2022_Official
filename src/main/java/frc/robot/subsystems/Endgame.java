
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Endgame extends SubsystemBase {
  public WPI_TalonFX winchMotor = new WPI_TalonFX(Constants.barTwoWinchChannel);


  public Endgame() {
    winchMotor.setNeutralMode(NeutralMode.Brake);
    winchMotor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
  } 

  public void winch() {
    winchMotor.set(Constants.winchSpeed);

  }

  public void winchReverse() {
    winchMotor.set(-Constants.winchSpeed);

  }

  @Override
  public void periodic() {
  
  }
}
