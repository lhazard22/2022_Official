// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Endgame;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class BarTwoExtendCommand extends SequentialCommandGroup {
  Endgame m_endgame;
  public BarTwoExtendCommand(Endgame _Endgame) {
    m_endgame = _Endgame;
    addCommands(
      new ExtendArmCommand(m_endgame, null),
      new WaitCommand(1.5),
      new ClampCommand(m_endgame)
    );
  }
}
