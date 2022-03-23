// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class RetractCommand extends CommandBase {
  Shooter m_Shooter;
  Boolean bool = false;
  public RetractCommand(Shooter _Shooter) {
    m_Shooter = _Shooter;
  }

  @Override
  public void initialize() {
    m_Shooter.retract();
    bool = true;
  }

  @Override
  public void execute() {

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
