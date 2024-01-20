package frc.robot.commands;

import static frc.robot.Constants.LauncherConstants.*;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.SUBShooter;

public class CMDPrepareLaunch extends Command {
    SUBShooter m_launcher;
    public CMDPrepareLaunch(SUBShooter launcher){
        addRequirements(m_launcher);
    }
    @Override
    public void initialize(){
        m_launcher.setLaunchWeel(kLauncherSpeed);
    }
    @Override
    public void execute(){}
    @Override
    public void end(boolean interrupted){}
    @Override
    public boolean isFinished(){
        return false;
    }
}
