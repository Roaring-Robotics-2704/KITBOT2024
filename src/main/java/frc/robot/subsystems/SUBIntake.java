package frc.robot.subsystems;
import static frc.robot.Constants.LauncherConstants.*;

import java.util.logging.ConsoleHandler;

import frc.robot.Constants;

import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SUBIntake extends SubsystemBase {

    private CANSparkMax feedWheel1;
    private CANSparkMax feedWheel2;

    public SUBIntake(){
        feedWheel1 = new CANSparkMax(kFeeder1ID, MotorType.kBrushless);
        feedWheel1.setSmartCurrentLimit(kFeedCurrentLimit);
        feedWheel1.setIdleMode(IdleMode.kBrake);
        
        feedWheel2 = new CANSparkMax(kFeeder2ID, MotorType.kBrushless);
        feedWheel2.setSmartCurrentLimit(kFeedCurrentLimit);
        feedWheel2.setIdleMode(IdleMode.kBrake);
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