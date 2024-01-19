package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SwerveDrive {

    public final double L = 24.5; // length between axles
    public final double W = 26.5; // width between axles

    private SwerveModule backRight;
    private SwerveModule backLeft;
    private SwerveModule frontRight;
    private SwerveModule frontLeft;
    private int posneg;
    private int posnegTwo; 

    public SwerveDrive(SwerveModule backRight, SwerveModule backLeft, SwerveModule frontRight, SwerveModule frontLeft) {
        this.backRight = backRight;
        this.backLeft = backLeft;
        this.frontRight = frontRight;
        this.frontLeft = frontLeft;
    }

    public void drive(double x1, double y1, double x2) { // front/back, side to side, rotation

        double r = Math.sqrt((L * L) + (W * W)); // This looks like it assumes all wheels are equidistant from center
      //  y1 *= -1; // ?
    
        x1 = x1 * -1;
        x2 = x2 * -1;
        y1 = y1 * -1;

        if (x1 <=-0.05 && y1 <=-0.05){
            y1 = y1 * -1;
        } 
         else if(x1 <= .05 && y1 >= .05 ){
              y1 = y1 * -1;
          }

        double a = (x1 - x2) * (L / r); // needs more descriptive variable names
        double b = (x1 + x2) * (L / r);
        double c = (y1 - x2) * (W / r);
        double d = (y1 + x2) * (W / r);


        double backRightSpeed = -(Math.sqrt((a * a) + (d * d)));
        if (x2>=0 && x1==0){
            backRightSpeed = (Math.sqrt((a * a) + (d * d)));
        }
        double backLeftSpeed = Math.sqrt((a * a) + (c * c));
        double frontRightSpeed; 
        frontRightSpeed = (Math.sqrt((b * b) + (d * d)));
        if(x2<=-0.01){
                    frontRightSpeed = -(Math.sqrt((b * b) + (d * d)));
        }
        double frontLeftSpeed = (Math.sqrt((b * b) + (c * c)));

        double backRightAngle = (Math.atan2(a, -d) / Math.PI)*20*.1; // in terms of -1 to 1: *180 to get to degrees [-180,180]
        double backLeftAngle = (Math.atan2(a, c) / Math.PI)*20*.1;
        double frontRightAngle = (Math.atan2(b, d) / Math.PI)*20*.1;
        double frontLeftAngle = (Math.atan2(b, c) / Math.PI)*20*.1;

        SmartDashboard.putNumber("Back right angle test?", backRightAngle);
        SmartDashboard.putNumber("A val", a);
        SmartDashboard.putNumber("B val", b);
        SmartDashboard.putNumber("C val", c);
        SmartDashboard.putNumber("D Val", d);

        if (x2>=0){
            posnegTwo = 1;
        
        } else if (x2<= 0){
            posnegTwo = 0;
        }
        if(x1>=0){
            posneg=1;
        } else if(x1<=0){
            posneg=0;
        }

        SmartDashboard.putNumber("Direction test", posneg);

        backRight.drive(backRightSpeed, backRightAngle, posneg);
        backLeft.drive(backLeftSpeed, backLeftAngle, posneg);
        frontRight.drive(frontRightSpeed, frontRightAngle, posneg);
        frontLeft.drive(frontLeftSpeed, frontLeftAngle, posneg);

    }
} 