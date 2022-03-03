
package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;
  //Compressor compressor = new Compressor(PneumaticsModuleType.REVPH);
  
  
  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();
    //compressor.disable();
    //compressor.enableDigital(); 
  }


  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
    //System.out.println(compressor.getPressureSwitchValue());
    /*if(compressor.getPressureSwitchValue()) {
      compressor.disable();
    }*/
  }

  @Override
  public void disabledInit() {

  }

  @Override
  public void disabledPeriodic() {

  }

  @Override
  public void autonomousInit() {

    //m_robotContainer.m_DriveTrain.gyro.reset();

    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {

  }

  @Override
  public void teleopInit() {

    //m_robotContainer.m_DriveTrain.gyro.reset();
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    
  }

  @Override
  public void teleopPeriodic() {

  }

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {

  }

  @Override
  public void simulationInit() {
    
  }

  @Override
  public void simulationPeriodic() {

  }
}
