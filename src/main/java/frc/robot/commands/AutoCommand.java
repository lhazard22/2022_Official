
package frc.robot.commands;

import java.util.function.DoubleSupplier;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Shooter;

public class AutoCommand extends CommandBase {
  DriveTrain m_DriveTrain;
  Shooter m_Shooter;
  DoubleSupplier leftY;
  DoubleSupplier leftX;
  DoubleSupplier rightX;
  double counter;

  public AutoCommand(DriveTrain _DriveTrain, Shooter _Shooter) {
    m_DriveTrain = _DriveTrain;
    m_Shooter = _Shooter;
    addRequirements(m_DriveTrain, m_Shooter);
  }

  @Override
  public void initialize() {
    m_DriveTrain.brDrive.getEncoder().setPosition(0);
    m_DriveTrain.brDrive.setIdleMode(CANSparkMax.IdleMode.kBrake);
    m_DriveTrain.frDrive.setIdleMode(CANSparkMax.IdleMode.kBrake);
    m_DriveTrain.blDrive.setIdleMode(CANSparkMax.IdleMode.kBrake);
    m_DriveTrain.flDrive.setIdleMode(CANSparkMax.IdleMode.kBrake);
    counter = 0;
  }

  @Override
  public void execute() {
    leftY = () -> -1;
    leftX = () -> 0;
    rightX = () -> 0;

    double distance = m_Shooter.getDistance();
    double botRPM = /* m_Shooter.getEquationRPM(distance) */3750;

    if (Math.abs(m_DriveTrain.brDrive.getEncoder().getPosition()) < 52) {
      m_DriveTrain.drive(leftY, leftX, rightX);
    } else {
      m_DriveTrain.drive(() -> 0, () -> 0, () -> 0);
      m_Shooter.shoot(1750, botRPM);
      if (m_Shooter.shooterReady(1750, botRPM)) {
        m_Shooter.ballUp();
        counter = counter + 1;
      }
    }

  }

  @Override
  public void end(boolean interrupted) {
    m_Shooter.shooterOff();
    m_Shooter.retract();
  }

  @Override
  public boolean isFinished() {
    if(counter < 40) {
      return false;
    } else {
      return true;
    }
  }
}
