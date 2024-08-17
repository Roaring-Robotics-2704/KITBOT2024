package frc.robot.subsystems;
import static frc.robot.Constants.LauncherConstants.*;

import java.util.logging.ConsoleHandler;

import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SUBIntake extends SubsystemBase {

    private WPI_TalonSRX feedWheel1;
    private WPI_TalonSRX feedWheel2;

    public SUBIntake(){
        feedWheel1 = new WPI_TalonSRX(kFeeder1ID);
        feedWheel2 = new WPI_TalonSRX(kFeeder2ID);
    }

    public void init() {

    }

    public Command getIntakeCommand() {

        return this.startEnd(

            () -> {
                setFeedWheel(0.7);
            },

            () -> {
                stop();
            }
        );
    }

    public void setFeedWheel(double speed) {
        feedWheel1.set(speed);
        feedWheel2.set(speed);
    }

    public void stop() {
        feedWheel1.set(0);
        feedWheel2.set(0);
    }
}