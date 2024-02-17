package frc.robot.subsystems.bullhead;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.hardware.core.CoreTalonFX;
import com.ctre.phoenix6.configs.TalonFXConfiguration;

public class ShooterCoreTalonFX extends CoreTalonFX {

    // Constructor
    public ShooterCoreTalonFX() {
        // Pass the ID and CAN bus values directly to the superclass constructor
        super(21313, "rio");
        configureTalonFX();
    }

    private void configureTalonFX() {
        // Configure the TalonFX for basic use
        TalonFXConfiguration configs = new TalonFXConfiguration();        

        configs.Slot0.kP = 1;
        configs.Slot0.kI = 0;
        configs.Slot0.kD = 10;
        configs.Slot0.kV = 2;  
        
        // Write these configs to the TalonFX
        getConfigurator().apply(configs);
        
        // Set the position to 0 rotations for initial use
        setPosition(0);
    }

    // Get Position
    public StatusSignal<Double> getPosition() {
        return super.getPosition();
    }

    // Get Velocity
    public StatusSignal<Double> getVelocity() {
        return super.getVelocity();
    }
}
