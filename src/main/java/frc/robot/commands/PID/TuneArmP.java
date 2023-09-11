package frc.robot.commands.PID;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmComponents.WristSubsystem;

public class TuneArmP extends CommandBase {
    private WristSubsystem wrist;

    double[] encoderPositions;
    double[] motorOutputs;

    double startPosition; // TODO

    double S = 0;

    public TuneArmP(WristSubsystem wrist) {
        this.wrist = wrist;
    }

    @Override
    public void initialize() {
        startPosition = wrist.getPositionRad();
    }

    @Override
    public void execute() {
        wrist.setVolts(S);
        S += 0.01;
    }

    @Override
    public void end(boolean interrupted) {
        wrist.stop();
        SmartDashboard.putNumber("kS", S);
    }

    @Override
    public boolean isFinished() {
        return wrist.getPositionRad() < startPosition - 0.1;
    }
}
