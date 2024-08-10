package frc.robot.subsystems;
import static frc.robot.Constants.LauncherConstants.*;

import java.util.logging.ConsoleHandler;

import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SUBShooter extends SubsystemBase {
WPI_TalonSRX m_launchWeel;
WPI_TalonSRX m_feeder;

    public SUBShooter(){
    m_launchWeel = new WPI_TalonSRX(k_LauncherID);

    }
    public Command getIntakeCommand(){
        return this.startEnd(
            () ->{
                setFeedWeel(kIntakeFeederSpeed);
                setLaunchweel(kIntakeLauncherSpeed);
            },
            () -> {
                stop();
            });
    }
    public Command getLaunchCommand(){
        return this.startEnd(
            () -> {
                setFeedWeel(kLaunchFeederSpeed);
                setLaunchweel(kLauncherSpeed);
            }, () -> {
                stop();
            });
    }
    public void setFeedWeel(double speed){
        m_feeder.set(speed);
    }
    public void setLaunchweel(double speed){
        m_launchWeel.set(speed);
    }
    public void stop(){
        m_launchWeel.set(0);
        m_feeder.set(0);
    }

    
}
