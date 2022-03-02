
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants;
import frc.robot.subsystems.Endgame;

public class EndgameCommand extends CommandBase {
  Endgame m_endgame;
  JoystickButton m_button;

  public EndgameCommand(Endgame _Endgame, JoystickButton _button) {
    m_endgame = _Endgame;
    m_button = _button;
    addRequirements(m_endgame);
  }

  @Override
  public void initialize() {

  }

  @Override
  public void execute() {
    if (m_button.get()) {
      m_endgame.barTwoArmExtend();
      m_endgame.barTwoClamp();
      while(m_endgame.getBarTwoWinchPosition() < Constants.barTwoWinchEncoderTicks) {
        m_endgame.winchBarTwo(Constants.winchSpeed);
      }
      m_endgame.barThreeArmExtend();
      m_endgame.barThreeClamp();
      if (true/*m_endgame.barTwoPresence()*/) {
        m_endgame.barTwoUnClamp();
        while(m_endgame.getBarThreeWinchPosition() < Constants.barThreeWinchEncoderTicks) {
          m_endgame.winchBarThree(Constants.winchSpeed);
        }
        m_endgame.barFourArmExtend();
        if (true/*m_endgame.barThreePresence()*/) {
          m_endgame.barThreeUnClamp();
          while(m_endgame.getBarFourWinchPosition() < Constants.barFourWinchEncoderTicks) {
            m_endgame.winchBarFour(Constants.winchSpeed);
          }
        }
      }
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
