package frc.robot.commands;
import org.photonvision.PhotonCamera;
 import org.photonvision.PhotonUtils;

 import edu.wpi.first.math.controller.PIDController;
 import edu.wpi.first.math.kinematics.ChassisSpeeds;
 import edu.wpi.first.math.util.Units;
 import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
 import edu.wpi.first.wpilibj2.command.Command;
 import frc.robot.RobotContainer;
 import frc.robot.subsystems.DriveSubsystem;
 import frc.robot.subsystems.SUBVision;
public class CMDPose extends Command {
    Double CAMERA_HEIGHT_METERS = Units.inchesToMeters(17.5);
    double TARGET_HEIGHT_METERS = Units.inchesToMeters(21);
    double CAMERA_PITCH_RADIANS = Units.degreesToRadians(-5);
   
}
