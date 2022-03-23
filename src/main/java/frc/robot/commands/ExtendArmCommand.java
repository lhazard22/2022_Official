
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants;
import frc.robot.subsystems.Endgame;

public class ExtendArmCommand extends CommandBase {
  Endgame m_endgame;
  JoystickButton m_button;

  public ExtendArmCommand(Endgame _Endgame, JoystickButton _button) {
    m_endgame = _Endgame;
    m_button = _button;
    addRequirements(m_endgame);
  }

  @Override
  public void initialize() {

  }

  @Override
  public void execute() {
    m_endgame.barTwoArmExtend();
    
  }

  @Override
  public void end(boolean interrupted) {

  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
