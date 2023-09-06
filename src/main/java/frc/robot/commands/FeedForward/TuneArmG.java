package frc.robot.commands.FeedForward;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.WristSubsystem;

public class TuneArmG extends CommandBase {
    private WristSubsystem wrist;

    double[] encoderPositions;
    double[] motorOutputs;

    double startPosition; // TODO

    double kG = 0;

    double kS;

    public TuneArmG(WristSubsystem wrist, double kS) {
        this.wrist = wrist;
        this.kS = kS;
    }

    @Override
    public void initialize() {
        startPosition = wrist.getPositionRad();
        kG = kS;
    }

    @Override
    public void execute() {
        wrist.setVolts(kG);
        kG += 0.001;
        SmartDashboard.putNumber("kG", kG - kS);
    }

    @Override
    public void end(boolean interrupted) {
        wrist.stop();
        SmartDashboard.putNumber("kG", kG - kS);
    }

    @Override
    public boolean isFinished() {
        return wrist.getPositionRad() > Math.abs(startPosition - 20);
    }
}
