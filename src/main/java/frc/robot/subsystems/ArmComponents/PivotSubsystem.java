package frc.robot.subsystems.ArmComponents;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.ArmSubsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

public class PivotSubsystem extends ArmSubsystem {
    private TalonFX motor = new TalonFX(13, "Canivore");
    private TalonFX otherMotor = new TalonFX(14, "Canivore");

    // TODO: Encoder

    public PivotSubsystem() {
        motor.setInverted(InvertType.None);

        otherMotor.follow(motor);
        otherMotor.setInverted(InvertType.OpposeMaster);

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

    public void zero() {}

    public double getVelRadS() {
        return motor.getSelectedSensorVelocity() / 2048 * 2 * Math.PI * 10 * (8.0 / 78);
    }

    public void periodic() {
        SmartDashboard.putNumber("Pivot Encoder Value", getPositionRad());
    }
}
