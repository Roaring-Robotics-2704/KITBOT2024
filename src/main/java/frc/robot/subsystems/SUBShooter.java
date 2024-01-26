package frc.robot.subsystems;
import static frc.robot.Constants.LauncherConstants.*;
//import frc.robot.Constants.LauncherConstants;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SUBShooter extends SubsystemBase {
    WPI_TalonSRX m_launchWeel;
    WPI_TalonSRX m_feeder;

    public SUBShooter (){
        m_launchWeel = new WPI_TalonSRX(1);
        m_feeder = new WPI_TalonSRX(2);

    }
    public Command getIntakeCommand(){
        return this.startEnd(
            ()->{
                setFeedWeel(kIntakeFeederSpeed);
                setLaunchWeel(kIntakeLauncherSpeed);
            },
            ()->{
                stop();
            }
        );
    }

    public Command getlaunchCommand(){
        return this.startEnd(
            ()->{
                setFeedWeel(kLaunchFeederSpeed);
                setLaunchWeel(kLauncherSpeed);
            }, ()->{
                stop();
            }
        );
    }
    public void setFeedWeel(double speed){
        m_feeder.set(speed);
    }
    public void setLaunchWeel(double speed){
        m_launchWeel.set(speed);
    }
    public void stop(){
         m_launchWeel.set(0);
        m_feeder.set(0);
    }
}
