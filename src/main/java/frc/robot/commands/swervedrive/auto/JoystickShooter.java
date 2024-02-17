package frc.robot.commands.swervedrive.auto;

//import edu.wpi.first.wpilibj2.command.CommandScheduler;
// import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.function.DoubleSupplier;
import java.util.function.BooleanSupplier;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.bullhead.Bullhead;

public class JoystickShooter extends Command {
    
    private final Bullhead bullhead;
    private final XboxController controller1;
    private final DoubleSupplier leftY;
    private final DoubleSupplier leftX;
    private final DoubleSupplier rightY;
    private final DoubleSupplier rightX;
    private final BooleanSupplier leftBumper;
    private final BooleanSupplier rightBumper;
    private final DoubleSupplier leftTrigger;
    private final DoubleSupplier rightTrigger;

    private final JoystickButton aButton;
    private final JoystickButton xButton;
    private final JoystickButton bButton;
    
    /**
     * Constructs a JoystickShooter command.
     * 
     * @param bullhead The Bullhead subsystem.
     * @param controller1 The XboxController.
     * @param leftY The DoubleSupplier for the left Y axis.
     * @param leftX The DoubleSupplier for the left X axis.
     * @param rightY The DoubleSupplier for the right Y axis.
     * @param rightX The DoubleSupplier for the right X axis.
     * @param leftBumper The BooleanSupplier for the left bumper.
     * @param rightBumper The BooleanSupplier for the right bumper.
     * @param leftTrigger The DoubleSupplier for the left trigger.
     * @param rightTrigger The DoubleSupplier for the right trigger.
     * @param aButton The JoystickButton for the A button.
     * @param xButton The JoystickButton for the X button.
     * @param bButton The JoystickButton for the B button.
     * Currently A, X, B are used not for this subsystem but for Swerve
     *  (A button) triggers an instant command to zero the gyro.
     *  (X button) triggers an instant command to add a fake vision reading.
     *  (B button) triggers a deferred command to drive to a specific pose.
     */
    public JoystickShooter(Bullhead bullhead, XboxController controller1, 
                        DoubleSupplier leftY, DoubleSupplier leftX, 
                        DoubleSupplier rightY, DoubleSupplier rightX, 
                        BooleanSupplier leftBumper, BooleanSupplier rightBumper,
                        DoubleSupplier leftTrigger, DoubleSupplier rightTrigger,
                        JoystickButton aButton, JoystickButton xButton, JoystickButton bButton) {
        this.bullhead = bullhead;
        this.controller1 = controller1;
        this.leftY = leftY;
        this.leftX = leftX; 
        this.rightY = rightY;
        this.rightX = rightX;
        this.leftBumper = leftBumper;
        this.rightBumper = rightBumper;
        this.leftTrigger = leftTrigger;
        this.rightTrigger = rightTrigger;
        this.aButton = aButton;
        this.xButton = xButton;
        this.bButton = bButton;
        
        addRequirements(bullhead); // Ensures that the command requires the Bullhead subsystem
    }
                    
    
    public void initialize(){
        bullhead.resetElevatorEncoder();
        bullhead.resetIntakeEncoder();
        bullhead.resetPivotEncoder();
        bullhead.resetShooterEncoder();
    }
    

    @Override
    public void execute() {
        
        if (controller1.getAButton()) {
            // Condition 1: If the pivot position is greater than 45, set the power to a negative value
            if (bullhead.getPivotPosition() > 45) {
                bullhead.setPowerPivot(-0.2);
            }
            // Condition 2: If the pivot position is less than 45, set the power to a positive value
            else if (bullhead.getPivotPosition() < 45) {
                bullhead.setPowerPivot(0.2); 
            }
            // Condition 3: If the pivot position is exactly 45, stop the pivot motor
            else {
                bullhead.setPowerPivot(0);
            }
        }
    

    // for smart dashboard
    SmartDashboard.putNumber("Elevator Encoder: ", bullhead.getElevatorPosition());
    SmartDashboard.putNumber("Shooter Encoder: ", bullhead.getShooterPosition());
    SmartDashboard.putNumber("Pivot Encoder: ", bullhead.getPivotPosition());
    
    
    
    }
}

