// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.FeedForward.TuneArmG;
import frc.robot.commands.FeedForward.TuneArmS;
import frc.robot.commands.FeedForward.TuneArmV;
import frc.robot.commands.Transitions.StaticToGravity;
import frc.robot.subsystems.ArmComponents.PivotSubsystem;
import frc.robot.subsystems.ArmComponents.WristSubsystem;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the
 * name of this class or
 * the package after creating this project, you must also update the
 * build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
    /**
     * This function is run when the robot is first started up and should be used
     * for any
     * initialization code.
     */
    private WristSubsystem wrist = new WristSubsystem();
    private PivotSubsystem pivot = new PivotSubsystem();

    private double kV = 0.0;

    private final CommandXboxController masher = new CommandXboxController(2);

    @Override
    public void robotInit() {
        masher.a().onTrue(new TuneArmS(wrist));
        
        masher.b().onTrue(new TuneArmG(wrist, 0.725));

        masher.y().onTrue(new TuneArmV(wrist, 5, 0.725, 0.0));

        masher.x()
            .onTrue(new InstantCommand(() -> wrist.setVolts(3)))
            .onFalse(new InstantCommand(() -> wrist.stop()));

        SmartDashboard.putNumber("Wrist Volts", 0.0);

        // masher.y().onTrue(new InstantCommand(() -> wrist.setVolts(1)));
        // new JoystickButton(masher, 3)
        //     .onTrue(new TuneArmS(wrist));
    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
    }

    @Override
    public void autonomousInit() {
    }

    @Override
    public void autonomousPeriodic() {
    }

    @Override
    public void teleopInit() {
        wrist.zero();

        new SequentialCommandGroup( new TuneArmS(wrist),
                                    new StaticToGravity(wrist),
                                    new TuneArmG(wrist, SmartDashboard.getNumber("kS", 0.0))
        ).schedule();

        new SequentialCommandGroup( new TuneArmS(pivot),
                                    new StaticToGravity(pivot),
                                    new TuneArmG(pivot, SmartDashboard.getNumber("kS", 0.0)),
                                    new TuneArmV(pivot, -2, SmartDashboard.getNumber("kS", 0.0), 0.0),
                                    new TuneArmV(pivot, 3, SmartDashboard.getNumber("kS", 0.0), SmartDashboard.getNumber("kV", 0.0)),
                                    new TuneArmV(pivot, -4, SmartDashboard.getNumber("kS", 0.0), SmartDashboard.getNumber("kV", 0.0))
        ).schedule();
    }

    @Override
    public void teleopPeriodic() {}

    @Override
    public void disabledInit() {
    }

    @Override
    public void disabledPeriodic() {
    }

    @Override
    public void testInit() {
    }

    @Override
    public void testPeriodic() {
    }

    @Override
    public void simulationInit() {
    }

    @Override
    public void simulationPeriodic() {
    }
}
