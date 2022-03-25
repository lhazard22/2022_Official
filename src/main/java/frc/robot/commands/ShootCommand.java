
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Shooter;

public class ShootCommand extends CommandBase {
  Shooter m_shooter;
  double botRPM;
  double distance;
  boolean bool = false;

  public ShootCommand(Shooter _shooter) {
    m_shooter = _shooter;
    addRequirements(m_shooter);
  }

  @Override
  public void initialize() {
    m_shooter.retract();
  }

  @Override
  public void execute() {
    distance = m_shooter.getDistance();
    double topRPM = Constants.topRPM;
    botRPM = /*m_shooter.getEquationRPM(distance)*/ 5000;

    m_shooter.shoot(topRPM, botRPM);
    if (m_shooter.shooterReady(topRPM, botRPM)) {
      m_shooter.ballUp();
    }
    
  }

  @Override 
  public void end(boolean interrupted) {
    m_shooter.shooterOff();
    m_shooter.retract();
  }

  @Override
  public boolean isFinished() {
    return bool;
  }
}
