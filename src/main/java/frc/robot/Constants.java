
package frc.robot;

import frc.robot.Other.Gains;

public final class Constants {

    
    //Controller
    public static int DriverControllerChannel = 0;
    public static int ManipControllerChannel = 1;
    public static int buttonA = 1;
    public static double deadzone;
    
    //Drive Train
    public static int blSteerMotorChannel = 1;
    public static int blDriveMotorChannel = 2;
    public static int flDriveMotorChannel = 3;
    public static int flSteerMotorChannel = 4;
    public static int frSteerMotorChannel = 5;
    public static int frDriveMotorChannel = 6;
    public static int brDriveMotorChannel = 7;
    public static int brSteerMotorChannel = 8;
    public static int blEncoderChannel = 0;
    public static int flEncoderChannel = 1;
    public static int frEncoderChannel = 2;
    public static int brEncoderChannel = 3;
    public static double spinTolerance = 5;
    public static double length = 0.47;
    public static double width = 0.47;

    //Shooter
    public static int topShooterMotorChannel = 9;
    public static int botShooterMotorChannel = 10;
    public static double topRPM;
    public static double botRPM;
    public static int kPIDLoopIdx = 0;
    public static int kTimeoutMs = 30;
    public static Gains kGains_Velocity = new Gains( 0.05, 0, 0, 0, 0,  1.00);
}
