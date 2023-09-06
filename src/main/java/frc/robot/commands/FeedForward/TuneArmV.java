package frc.robot.commands.FeedForward;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.WristSubsystem;

public class TuneArmV extends CommandBase {
    private WristSubsystem wrist;

    private double volts;

    private ArrayList<Double> velocities;

    double startPosition; // TODO

    double kS;
    double average = 0;
    double vel = 0;

    public TuneArmV(WristSubsystem wrist, double volts, double kS) {
        this.wrist = wrist;
        this.volts = volts;
        this.kS = kS;
    }

    @Override
    public void initialize() {
        SmartDashboard.putBoolean("Ended", false);
        wrist.setVolts(-volts);
        velocities = new ArrayList<Double>();
    }

    @Override
    public void execute() {
        vel = wrist.getVelRadS();
        SmartDashboard.putNumber("Velocity", vel);
        if(Math.abs(wrist.getPositionRad()) < 0.6) {
            velocities.add(vel);
        }
    }

    @Override
    public void end(boolean interrupted) {
        SmartDashboard.putBoolean("Ended", true);
        wrist.stop();

        for(double v : velocities) {
            average += v;
        }

        average /= velocities.size();

        SmartDashboard.putNumber("kV", (volts - kS) / average);
    }

    @Override
    public boolean isFinished() {
        return wrist.getPositionRad() < -1;
    }
}
