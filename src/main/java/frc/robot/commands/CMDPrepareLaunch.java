package frc.robot.commands;

import static frc.robot.Constants.LauncherConstants.*;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.SUBShooter;

public class CMDPrepareLaunch extends Command {
    public CMDPrepareLaunch(SUBShooter launcher){
    addRequirements( RobotContainer.m_sUBShooter
);
    }
    @Override
    public void initialize() {
         RobotContainer.m_sUBShooter.setLaunchweel(kLauncherSpeed);
    }
    @Override
    public void execute(){
    
    }
    @Override
    public void end(boolean interrupted){}
    @Override
    public boolean isFinished(){
        return false;
    }
 }


    
