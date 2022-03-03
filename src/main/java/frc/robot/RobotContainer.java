
package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.EndgameCommand;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.RelayCommand;
import frc.robot.commands.ShootCommand;
import frc.robot.commands.SwerveDriveCommand;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Endgame;
import frc.robot.subsystems.IntakeRelay;
import frc.robot.subsystems.Shooter;

public class RobotContainer {
  DriveTrain m_DriveTrain = new DriveTrain();
  Shooter m_Shooter = new Shooter();
  IntakeRelay m_IntakeRelay = new IntakeRelay();
  Endgame m_Endgame = new Endgame();
  XboxController driverController = new XboxController(Constants.DriverControllerChannel);
  XboxController manipController = new XboxController(Constants.ManipControllerChannel);
  JoystickButton manipButtonA = new JoystickButton(manipController, Constants.buttonA);
  JoystickButton manipButtonB = new JoystickButton(manipController, Constants.buttonB);
  JoystickButton manipButtonY = new JoystickButton(manipController, Constants.buttonY);
  JoystickButton manipButtonRight = new JoystickButton(manipController, Constants.buttonRight);
  JoystickButton manipButtonLeft = new JoystickButton(manipController, Constants.buttonLeft);
  public RobotContainer() {
    System.out.println("A");
    configureButtonBindings();
    m_DriveTrain.setDefaultCommand(new SwerveDriveCommand (() -> driverController.getLeftY(),
     () -> driverController.getLeftX(), () -> driverController.getRightX(), m_DriveTrain));
  }

  private void configureButtonBindings() {
    System.out.println("A");
    manipButtonA.whileHeld(new ShootCommand(Constants.topRPM, Constants.botRPM, m_Shooter));
    manipButtonB.whileHeld(new RelayCommand(m_IntakeRelay));
    manipButtonY.whileHeld(new IntakeCommand(m_IntakeRelay));
    manipButtonRight.whenPressed(new EndgameCommand(m_Endgame, manipButtonLeft));
  }

  public Command getAutonomousCommand() {
    return null;
  }
}
