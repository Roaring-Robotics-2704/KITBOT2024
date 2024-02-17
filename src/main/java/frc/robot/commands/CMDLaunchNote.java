package frc.robot.commands;
import static frc.robot.Constants.LauncherConstants.*;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.SUBShooter;

public class CMDLaunchNote extends Command {
    public CMDLaunchNote(SUBShooter launcher){
    addRequirements(RobotContainer.m_sUBShooter);
    }
    @Override
    public void initialize(){
        RobotContainer.m_sUBShooter.setLaunchweel(kLauncherSpeed);
        RobotContainer.m_sUBShooter.setFeedWeel(kLaunchFeederSpeed);
    }
    @Override 
    public void execute(){
    }
    @Override
    public boolean isFinished(){
        return false;
    }
    @Override
    public void end(boolean interrupted){
        RobotContainer.m_sUBShooter.stop();
    }
}
