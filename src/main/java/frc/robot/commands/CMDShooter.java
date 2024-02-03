package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.OIConstants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.SUBShooter;

public class CMDShooter extends Command {
  SUBShooter m_SubShooter = new SUBShooter();
  public static CommandXboxController xbox = new CommandXboxController(OIConstants.kDriver2ControllerPort);
  /** Creates a new CMDShooter. */
  public CMDShooter() {
    addRequirements(RobotContainer.m_sUBShooter);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double feedvalue = 0;
    if (xbox.getHID().getRightBumper()) {feedvalue=feedvalue+0.2;}
    if (xbox.getHID().getLeftBumper()) {feedvalue=feedvalue-0.2;}

    RobotContainer.m_sUBShooter.setLaunchweel(xbox.getRightTriggerAxis()-xbox.getLeftTriggerAxis());
    RobotContainer.m_sUBShooter.setFeedWeel(feedvalue);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}