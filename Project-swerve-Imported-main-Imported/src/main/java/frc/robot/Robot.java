package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.motorcontrol.Spark;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkAbsoluteEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.AnalogEncoder;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;


import edu.wpi.first.wpilibj.Encoder;

import frc.robot.Subsystems.*;

public class Robot extends TimedRobot {
	
private final XboxController m_DriveController = new XboxController(1);
private final XboxController m_xboxcontroller = new XboxController(0);
    private Command m_auto;
    private final SendableChooser<Command> m_autoChooser = new SendableChooser<Command>();
	

	private Encoder m_BRightEncoder = new Encoder(6, 7);
	private Encoder m_BLeftEncoder = new Encoder(0, 1);
	private Encoder m_FLeftEncoder = new Encoder(2, 3);
	private Encoder m_FRightEncoder = new Encoder(4, 5);

	private Spark m_leftFront = new Spark(7);
	private Spark m_leftBack = new Spark(0);
	private Spark m_RightFront = new Spark(8);
	private Spark m_RightBack = new Spark(2);

	private CANSparkMax m_ALeftFront = new CANSparkMax(4, MotorType.kBrushless);
	private CANSparkMax m_ALeftBack = new CANSparkMax(1, MotorType.kBrushless);
	private CANSparkMax m_ARightFront = new CANSparkMax(3, MotorType.kBrushless);
	private CANSparkMax m_ARightBack = new CANSparkMax(2, MotorType.kBrushless);



	private RelativeEncoder m_ALeftBackE = m_ALeftBack.getEncoder();
	private RelativeEncoder m_ALeftFrontE = m_ALeftFront.getEncoder();
	private RelativeEncoder m_ARightBackE = m_ARightBack.getEncoder();
	private RelativeEncoder m_ARightFrontE = m_ARightFront.getEncoder();

	private DigitalInput m_SLeftBack = new DigitalInput(13);
	private DigitalInput m_SLeftFront = new DigitalInput(12);
	private DigitalInput m_SRightBack = new DigitalInput(10);
	private DigitalInput m_SRightFront = new DigitalInput(11);


// all this is untested

    private SwerveModule backLeft = new SwerveModule(m_ALeftBack, m_leftBack, m_BLeftEncoder);//0); //angleMotor Port, speedMotor port, ecoder 1, and 2
    private SwerveModule frontLeft = new SwerveModule(m_ALeftFront, m_leftFront, m_FLeftEncoder);//1);
    private SwerveModule backRight = new SwerveModule(m_ARightBack, m_RightBack, m_BRightEncoder);
    private SwerveModule frontRight = new SwerveModule(m_ARightFront, m_RightFront, m_FRightEncoder);
 
	private SwerveDrive swerveDrive = new SwerveDrive(backRight, backLeft, frontRight, frontLeft);
	
	

	public Robot() {
		// m_autoChooser.setDefaultOption("No_Auto", new NoAutonomous());
		
		

	}

	@Override
	public void robotInit() {
		
		SmartDashboard.putData("Auto_Choice", m_autoChooser);

		m_ALeftBackE.setPosition(0);
		m_ALeftFrontE.setPosition(0);
		m_ARightBackE.setPosition(0);
		m_ARightFrontE.setPosition(0);

		SmartDashboard.putData(m_SLeftBack);
		SmartDashboard.putData(m_SLeftFront);
		SmartDashboard.putData(m_SRightBack);
		SmartDashboard.putData(m_SRightFront);


		



	}

	@Override
	public void robotPeriodic() {
		CommandScheduler.getInstance().run();
		
        double x1 = m_DriveController.getLeftX();
        double y1 = m_DriveController.getLeftY();
        double x2 = m_DriveController.getRightX();

		swerveDrive.drive(x1, y1, x2);


		SmartDashboard.putNumber("Back right encoder not neo", m_BRightEncoder.getDistancePerPulse());

		SmartDashboard.putNumber("Left back Encoder", m_ALeftBackE.getPosition());
		SmartDashboard.putNumber("Left front Encoder", m_ALeftFrontE.getPosition());
		SmartDashboard.putNumber("Right back Encoder", m_ARightBackE.getPosition());
		SmartDashboard.putNumber("Right front Encoder", m_ARightFrontE.getPosition());

	}

	@Override
	public void disabledInit() {
	}

	@Override
	public void disabledPeriodic() {
	}

	@Override
	public void autonomousInit() {
		m_auto = m_autoChooser.getSelected();

		if (m_auto != null) {
			m_auto.schedule();
		}
	}

	/** This function is called periodically during autonomous. */
	@Override
	public void autonomousPeriodic() {
	}

	@Override
	public void teleopInit() {
	}

	/** This function is called periodically during operator control. */
	@Override
	public void teleopPeriodic() {
		

		
		
	}

	@Override
	public void testInit() {
		// Cancels all running commands at the start of test mode.
		CommandScheduler.getInstance().cancelAll();
	}

	/** This function is called periodically during test mode. */
	@Override
	public void testPeriodic() {
	}

	/** This function is called once when the robot is first started up. */
	@Override
	public void simulationInit() {
	}

	/** This function is called periodically whilst in simulation. */
	@Override
	public void simulationPeriodic() {
	}
}
