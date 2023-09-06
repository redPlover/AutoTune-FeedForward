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
import frc.robot.subsystems.WristSubsystem;

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

    private final CommandXboxController masher = new CommandXboxController(2);
    // private final XboxController masher = new XboxController(2);

    private double wristManualVolts = 0.0;

    @Override
    public void robotInit() {
        masher.a().onTrue(new TuneArmS(wrist));
        
        masher.b().onTrue(new TuneArmG(wrist, 0.725));

        masher.y().onTrue(new TuneArmV(wrist, 5, 0.725));

        // masher.y()
        //     .onTrue(new InstantCommand(() -> wrist.setVolts(wristManualVolts)))
        //     .onFalse(new InstantCommand(() -> wrist.stop()));

        masher.x()
            .onTrue(new InstantCommand(() -> wrist.setVolts(wristManualVolts)))
            .onFalse(new InstantCommand(() -> wrist.stop()));

        SmartDashboard.putNumber("Wrist Volts", 0.0);

        // masher.y().onTrue(new InstantCommand(() -> wrist.setVolts(1)));
        // new JoystickButton(masher, 3)
        //     .onTrue(new TuneArmS(wrist));
    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
        wristManualVolts = SmartDashboard.getNumber("Wrist Volts", 0.0);
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
        // new JoystickButton(masher, 3)
        //     .onTrue()
        // new SequentialCommandGroup(new TuneWristS(wrist)).schedule();
        // new TuneArmS(wrist).schedule();
        // new TuneArmG(wrist).schedule();
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
