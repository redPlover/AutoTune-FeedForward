package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

public class WristSubsystem extends SubsystemBase {
    private TalonFX motor = new TalonFX(17);

    public WristSubsystem() {
        motor.configFactoryDefault();
        motor.setNeutralMode(NeutralMode.Brake);
    }

    public void setVolts(double volts) {
        motor.set(ControlMode.PercentOutput, volts/12);
    }

    public void stop() {
        motor.set(ControlMode.PercentOutput, 0);
    }

    public double getPositionRad() {
        return motor.getSelectedSensorPosition() / 2048 * 2 * Math.PI * 8.0 / 78;
    }

    public void zero() {
        motor.setSelectedSensorPosition(2.81 / (2 * Math.PI) * 2048 / (8.0 / 78));
    }

    public double getVelRadS() {
        return motor.getSelectedSensorVelocity() / 2048 * 2 * Math.PI * 10 * (8.0 / 78);
    }

    public void periodic() {
        SmartDashboard.putNumber("Encoder Value", getPositionRad());
        SmartDashboard.putNumber("Get Velocity", getVelRadS());
    }
}
