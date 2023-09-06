package frc.robot.commands.FeedForward;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.WristSubsystem;

public class TuneArmS extends CommandBase {
    private WristSubsystem wrist;

    double[] encoderPositions;
    double[] motorOutputs;

    double startPosition; // TODO

    double S = 0;

    public TuneArmS(WristSubsystem wrist) {
        this.wrist = wrist;
    }

    @Override
    public void initialize() {
        startPosition = wrist.getPositionRad();
        S = 0;
    }

    @Override
    public void execute() {
        wrist.setVolts(S);
        S += 0.001;
        SmartDashboard.putNumber("kS", S);
    }

    @Override
    public void end(boolean interrupted) {
        wrist.stop();
        SmartDashboard.putNumber("kS", S);
    }

    @Override
    public boolean isFinished() {
        return wrist.getPositionRad() > Math.abs(startPosition - 20);
    }
}
