
package frc.robot.commands;

import java.util.function.DoubleSupplier;

import com.revrobotics.CANEncoder;
import com.revrobotics.RelativeEncoder;

import org.ejml.dense.block.MatrixOps_DDRB;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Shooter;

public class AutoCommand extends CommandBase {
  DriveTrain m_DriveTrain;
  Shooter m_Shooter;
  CANEncoder encoder;
  DoubleSupplier leftY;
  DoubleSupplier leftX;
  DoubleSupplier rightX;
  public AutoCommand(DriveTrain _DriveTrain, Shooter _Shooter) {
    m_DriveTrain = _DriveTrain;
    m_Shooter = _Shooter;
    addRequirements(m_DriveTrain, m_Shooter);
  }

  @Override
  public void initialize() {
    encoder = m_DriveTrain.brDrive.getEncoder();
  }

  @Override
  public void execute() {
    leftY = () -> -1;
    leftX = () -> 0;
    rightX = () -> 0;

    double distance = Shooter.getDistance();

    if(Math.abs(m_DriveTrain.brDrive.getEncoder().getPosition()) < 25) {
      m_DriveTrain.drive(leftY, leftX, rightX);
    } else {
      
    }


  }
  @Override
  public void end(boolean interrupted) {

  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
