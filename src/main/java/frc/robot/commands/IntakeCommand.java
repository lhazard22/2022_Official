
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Intake;

public class IntakeCommand extends CommandBase {
  Intake m_Intake = new Intake();
  public IntakeCommand(Intake _Intake) {
    m_Intake = _Intake;
    addRequirements(m_Intake);
  }

  @Override
  public void initialize() {

  }

  @Override
  public void execute() {
    m_Intake.intake(Constants.spinSpeed);
  }

  @Override
  public void end(boolean interrupted) {

  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
