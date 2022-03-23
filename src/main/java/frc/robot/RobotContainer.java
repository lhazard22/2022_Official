
package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.AutoCommand;
import frc.robot.commands.BarTwoExtendCommand;
import frc.robot.commands.BarTwoWinchCommand;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.RelayCommand;
import frc.robot.commands.RetractArmCommand;
import frc.robot.commands.RetractCommand;
import frc.robot.commands.ShootSequenceCommand;
import frc.robot.commands.SwerveDriveCommand;
import frc.robot.commands.UnclampCommand;
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
  JoystickButton manipButtonX = new JoystickButton(manipController, Constants.buttonX);
  JoystickButton manipButtonY = new JoystickButton(manipController, Constants.buttonY);
  JoystickButton manipButtonRight = new JoystickButton(manipController, Constants.buttonRight);
  JoystickButton manipButtonLeft = new JoystickButton(manipController, Constants.buttonLeft);
  JoystickButton manipButtonOptions = new JoystickButton(manipController, 7);
  JoystickButton manipButtonStart = new JoystickButton(manipController, 8);

  public RobotContainer() {  
    configureButtonBindings();
    m_DriveTrain.setDefaultCommand(new SwerveDriveCommand (() -> driverController.getLeftY(),
     () -> driverController.getLeftX(), () -> driverController.getRightX(), m_DriveTrain));
  }

  private void configureButtonBindings() { 
    //manipButtonA.whileHeld(new ShootCommand(Constants.topRPM, Constants.botRPM, m_Shooter));
    manipButtonA.whileHeld(new ShootSequenceCommand(m_Shooter));
    manipButtonB.whileHeld(new RelayCommand(m_IntakeRelay));
    manipButtonX.whenPressed(new RetractCommand(m_Shooter));
    manipButtonY.whileHeld(new IntakeCommand(m_IntakeRelay));
    manipButtonRight.whenPressed(new BarTwoExtendCommand(m_Endgame));
    manipButtonLeft.whenPressed(new RetractArmCommand(m_Endgame));
    manipButtonOptions.whileHeld(new BarTwoWinchCommand(m_Endgame));
    manipButtonStart.whenPressed(new UnclampCommand(m_Endgame));
  }

  public Command getAutonomousCommand() {
    return new AutoCommand(m_DriveTrain);
  }
}
