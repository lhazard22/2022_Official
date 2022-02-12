
package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.ShootCommand;
import frc.robot.commands.SwerveDriveCommand;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public class RobotContainer {
  DriveTrain m_DriveTrain = new DriveTrain();
  Shooter m_Shooter = new Shooter();
  Intake m_Intake = new Intake();
  XboxController driverController = new XboxController(Constants.DriverControllerChannel);
  XboxController manipController = new XboxController(Constants.ManipControllerChannel);
  JoystickButton manipButtonA = new JoystickButton(manipController, Constants.buttonA);
  JoystickButton manipButtonY = new JoystickButton(manipController, Constants.buttonY);
  public RobotContainer() {
    configureButtonBindings();
    m_DriveTrain.setDefaultCommand(new SwerveDriveCommand (() -> driverController.getLeftX(),
     () -> driverController.getLeftY(), () -> driverController.getRightX(), m_DriveTrain));
  }

  private void configureButtonBindings() {
    manipButtonA.whileHeld(new ShootCommand(Constants.topRPM, Constants.botRPM, m_Shooter));
    manipButtonY.whileHeld(new IntakeCommand(m_Intake));
  }

  public Command getAutonomousCommand() {
    return null;
  }
}
