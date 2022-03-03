
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.IntakeRelay;

public class IntakeCommand extends CommandBase {
  IntakeRelay m_Intake = new IntakeRelay();

  public IntakeCommand(IntakeRelay _Intake) {
    m_Intake = _Intake;
    addRequirements(m_Intake);
  }

  @Override
  public void initialize() {

  }

  @Override
  public void execute() {
    System.out.println("A");
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
