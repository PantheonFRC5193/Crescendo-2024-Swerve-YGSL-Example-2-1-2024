package frc.robot.subsystems.bullhead;

import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.configs.MagnetSensorConfigs;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.ParentDevice;
import com.ctre.phoenix6.signals.AbsoluteSensorRangeValue;
import com.ctre.phoenix6.signals.SensorDirectionValue;
import com.ctre.phoenix6.StatusSignal;


public class PivotCANCoder extends ParentDevice {
    
    final int pivotCANcoderId = 21313;
    final String pivotCANcoderCANbus = null;
    CANcoder pivotcancoder;
    
    // Constructor 
    // CANcoder ID here
    public PivotCANCoder() {
        super(4, "CoreCANcoder", null);
        pivotcancoder = new CANcoder(pivotCANcoderId, pivotCANcoderCANbus);
        configureCANCoder();
    }

    private void configureCANCoder() {
        // Configure the CANcoder for basic use
        CANcoderConfiguration configs = new CANcoderConfiguration();
        
        // Modify the MagnetSensor parameters
        MagnetSensorConfigs magnetSensorConfigs = new MagnetSensorConfigs()
            .withAbsoluteSensorRange(AbsoluteSensorRangeValue.Signed_PlusMinusHalf)
            .withMagnetOffset(0.26)
            .withSensorDirection(SensorDirectionValue.Clockwise_Positive);
        
        // Apply the MagnetSensor configurations
        configs.withMagnetSensor(magnetSensorConfigs);
        
        // Write these configs to the CANcoder
        pivotcancoder.getConfigurator().apply(configs);
        
        // Set the position to 0 rotations for initial use
        pivotcancoder.setPosition(0);
    }

    // Get Position
    public StatusSignal<Double> getPosition() {
        return pivotcancoder.getPosition();
    }
       // Get Position
    public StatusSignal<Double> getVelocity() {
        return pivotcancoder.getVelocity();
    }
}


