package frc.robot.Subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.Encoder;;


public class SwerveModule extends SubsystemBase {

	private CANSparkMax angleMotor;
	private Spark speedMotor;
	private PIDController pidController;
	private RelativeEncoder angleEncoder;
	public double setpoint;
	public double setpointtest;
	private final double MAX_VOLTS = 10;
	public double angleprt;
	private Encoder anglEncoder;

	public SwerveModule(CANSparkMax angleMotor, Spark speedMotor, Encoder anglEncoder ) {
		this.angleMotor = angleMotor;
		this.speedMotor = speedMotor; 
		
	//	this.anglEncoder = anglEncoder;

		angleEncoder = this.angleMotor.getEncoder();

		

		pidController = new PIDController(0.1, 0, 0);
		pidController.enableContinuousInput(-180, 180); // this needs to change later TODO
	}
//test234
	@Override
	public void periodic() {
		angleMotor.set(pidController.calculate(angleEncoder.getPosition())); // spins angle motor
		SmartDashboard.putNumber("Setpoint", setpoint);
		SmartDashboard.putNumber("Setpoint test", setpointtest);
		SmartDashboard.putNumber("Angle set point ", angleprt);
	}

	public void drive(double speed, double angle, int test) {
			// speedMotor.set(speed);
		if(test==1){
			speedMotor.set(speed);
					 setpoint = angle * (MAX_VOLTS * 0.5); //+ (MAX_VOLTS * 0.5);
		if (setpoint < 0) {
			setpoint = setpoint;
		} //upload? please please
		 else{
			setpointtest = setpoint;
		}
		angleprt = angle;
		pidController.setSetpoint(setpointtest);
		}else if(test==0){
			speedMotor.set(-speed);
		setpoint = -angle * (MAX_VOLTS * 0.5); //+ (MAX_VOLTS * 0.5);
		if (setpoint < 0) {
			setpoint = setpoint;
		}
		 else{
			setpointtest = setpoint;
		}
		angleprt = angle;
		pidController.setSetpoint(setpointtest);
		}

		//  setpoint = angle * (MAX_VOLTS * 0.5); //+ (MAX_VOLTS * 0.5);
		// if (setpoint < 0) {
		// 	setpoint = setpoint;
		// }
		//  else{
		// 	setpointtest = setpoint;
		// }
		// angleprt = angle;
		// pidController.setSetpoint(setpointtest); // point to spin angle motor
	}

    public void set(SwerveModule backRight, SwerveModule backLeft, SwerveModule frontRight,
            SwerveModule frontLeft) {

				
    }

}
