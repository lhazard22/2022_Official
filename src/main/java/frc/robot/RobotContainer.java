// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ShootCommand;
import frc.robot.commands.SwerveDriveCommand;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Shooter;

public class RobotContainer {
  DriveTrain m_DriveTrain = new DriveTrain();
  Shooter m_Shooter = new Shooter();
  XboxController driverController = new XboxController(Constants.DriverControllerChannel);
  XboxController manipController = new XboxController(Constants.ManipControllerChannel);
  JoystickButton manipButtonA = new JoystickButton(manipController, Constants.buttonA);
  public RobotContainer() {
    configureButtonBindings();
    m_DriveTrain.setDefaultCommand(new SwerveDriveCommand (() -> driverController.getLeftX(),
     () -> driverController.getLeftY(), () -> driverController.getRightX(), m_DriveTrain));
  }

  private void configureButtonBindings() {
    manipButtonA.whileHeld(new ShootCommand(m_Shooter));
  }

  public Command getAutonomousCommand() {
    return null;
  }
}
