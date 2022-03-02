// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.IntakeRelay;

public class RelayCommand extends CommandBase {
  IntakeRelay m_Relay = new IntakeRelay();

  public RelayCommand(IntakeRelay _Relay) {
    m_Relay = _Relay;
    addRequirements(m_Relay);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    m_Relay.runRelay(Constants.spinSpeed);
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
