package frc.robot.subsystems.bullhead;

//import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
//import com.ctre.phoenix.motorcontrol.ControlMode;
//import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
//import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
//import com.revrobotics.SparkAbsoluteEncoder;
import com.revrobotics.SparkRelativeEncoder;
import com.revrobotics.CANSparkBase.ControlType;



//import com.ctre.phoenix6.Utils;
// import com.ctre.phoenix6.configs.CANcoderConfiguration;
// import com.ctre.phoenix6.configs.TalonFXConfiguration;
// import com.ctre.phoenix6.controls.DutyCycleOut;
// import com.ctre.phoenix6.controls.Follower;
// import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
// import com.ctre.phoenix6.signals.InvertedValue;
// import com.ctre.phoenix.motorcontrol.can.BaseMotorController;
// import com.ctre.phoenix6.hardware.core.CoreCANcoder;

// import edu.wpi.first.wpilibj.XboxController;

public class Bullhead extends SubsystemBase {

    private int intakeID = 312; 
    private int elevatorID = 132;
    private int pivotID = 231; 
    private int shooterID = 213; 

    private CANSparkMax intake = new CANSparkMax(intakeID, MotorType.kBrushless);
    private CANSparkMax elevator = new CANSparkMax(elevatorID, MotorType.kBrushless);
    private TalonFX pivot = new TalonFX(pivotID);
    private TalonFX shooter = new TalonFX(shooterID);

    private RelativeEncoder elevatorEncoder = elevator.getEncoder(SparkRelativeEncoder.Type.kHallSensor, 42);
    private RelativeEncoder intakeEncoder = intake.getEncoder(SparkRelativeEncoder.Type.kHallSensor, 42);
    private PivotCANCoder pivotcancoder = new PivotCANCoder();
    private ShooterCoreTalonFX shooterTalonFX = new ShooterCoreTalonFX(); 

    // pivot
    public double getPivotPosition(){
        return pivotcancoder.getPosition().getValue();
    }

    public void setPivotPosition(double position){
        pivot.setPosition(position);
    }

    public void resetPivotEncoder(){
        pivot.setPosition(0);
    }

    public void setPowerPivot(double power){
        pivot.set(power);
    }

    // elevator 
    public double getElevatorPosition(){
        return elevatorEncoder.getPosition();
    }  

    public void resetElevatorEncoder(){
        elevatorEncoder.setPosition(0);
    }

    public void setPowerElevator(double power){
        elevator.set(power);
    }

    public void setElevatorPosition(double position) {
        elevator.getPIDController().setReference(position, ControlType.kPosition);
    }


    // intake 
    public void resetIntakeEncoder(){
        intakeEncoder.setPosition(0);
    }  

    public void setPowerIntake(double power){
        intake.set(power);
    }

    // shooter 
    public double getShooterPosition(){
        return shooterTalonFX.getPosition().getValue();
    }

    public void resetShooterEncoder() {
        shooter.setPosition(0);
    }

    public void setPowerShooter(double power){
        shooter.set(power);
    }

    public double getShooterVelocity() {
        return shooterTalonFX.getVelocity().getValue();
    }

}

