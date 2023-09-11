package frc.robot.commands.Transitions;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmSubsystem;

public class StaticToGravity extends CommandBase {
    private ArmSubsystem arm;

    public StaticToGravity(ArmSubsystem arm) {
        this.arm = arm;
    }

    @Override
    public void initialize() {
        arm.setVolts(0.1);
    }

    @Override
    public void execute() {}

    @Override
    public void end(boolean interrupted) {
        arm.stop();
    }

    @Override
    public boolean isFinished() {
        return arm.getPositionRad() > 1.3;
    }
}
