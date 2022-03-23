
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Shooter;

public class ShootSequenceCommand extends SequentialCommandGroup {
  Shooter m_Shooter;
  public ShootSequenceCommand(Shooter _Shooter) {
    m_Shooter = _Shooter;
    addCommands(
      new ShootCommand(m_Shooter),
      new WaitCommand(1),
      new RetractCommand(m_Shooter),
      new StopShootCommand(m_Shooter)
    );
  }
}
