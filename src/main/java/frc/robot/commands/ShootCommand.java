
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
    distance = Shooter.getDistance();
    double topRPM = Constants.topRPM; // erase to add number Constants.rpm to --- number 
    botRPM = m_shooter.getEquationRPM(distance);

    m_shooter.shoot(Constants.topRPM, botRPM);
    if (m_shooter.shooterReady(Constants.topRPM, botRPM)) {
      m_shooter.ballUp();
    }
    
  }

  @Override 
  public void end(boolean interrupted) {
    bool = false;
  }

  @Override
  public boolean isFinished() {
    return bool;
  }
}
