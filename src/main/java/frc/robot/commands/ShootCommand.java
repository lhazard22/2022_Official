
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class ShootCommand extends CommandBase {
  Shooter m_shooter;
  double topRPM;
  double botRPM;
  public ShootCommand(double _topRPM, double _botRPM, Shooter _shooter) {
    topRPM = _topRPM;
    botRPM = _botRPM;
    m_shooter = _shooter;
    addRequirements(m_shooter);
  }

  @Override
  public void initialize() {

  }

  @Override
  public void execute() {
    m_shooter.shoot(topRPM, botRPM);
  }

  @Override
  public void end(boolean interrupted) {
    m_shooter.shooterOff();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
