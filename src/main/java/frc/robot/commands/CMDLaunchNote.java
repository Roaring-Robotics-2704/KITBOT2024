package frc.robot.commands;
import static frc.robot.Constants.LauncherConstants.*;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.SUBShooter;

public class CMDLaunchNote extends Command{
    SUBShooter mlauncher;
    public CMDLaunchNote(SUBShooter launcher){
        m_launcher = launcher;
        addRequirements(mlauncher);
    }
    @Override
    public void initialize(){
        m_launcher.setLaunchWeel(kLaunchSpeed);
        m_launcher.setFeedWeel(kLaunchFeederSpeed);
    }
    @Override
    public void execute(){}
    @Override
    public boolean isFinished(){
        return false;
    }
    @Override
    public void end(boolean interrupted){
        m_launcher.stop();
    }

}
