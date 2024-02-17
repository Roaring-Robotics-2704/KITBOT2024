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
public class CMDDriveToDIstance extends Command {
    PIDController translationPID = new PIDController(0.1, 0, 0);
       PIDController rotationPID = new PIDController(0.1, 0, 0);
        DriveSubsystem m_drive = RobotContainer.m_robotDrive;
        SUBVision m_vision =  RobotContainer.m_SUBVision;


          Double CAMERA_HEIGHT_METERS = Units.inchesToMeters(17.5);
   double TARGET_HEIGHT_METERS = Units.inchesToMeters(21);
   double CAMERA_PITCH_RADIANS = Units.degreesToRadians(-5);

   /** Creates a new SUBVision. */
       PhotonCamera camera = m_vision.camera;
    public CMDDriveToDIstance(){
        addRequirements(RobotContainer.m_SUBVision,RobotContainer.m_robotDrive);
    }
    @Override
    public void execute(){
        if (camera.getLatestResult().hasTargets()) {
            double y = -translationPID.calculate(m_vision.getDistance(),2);
            //double x = translationPID.calculate(m_vision.getSkew(),0)
            double z = rotationPID.calculate(camera.getLatestResult().getBestTarget().getYaw(),0);
            m_drive.driveRobotRelative(new ChassisSpeeds(y,0,z));
            }
            if (camera.getLatestResult().hasTargets()) {
            SmartDashboard.putNumber("Distance",  PhotonUtils.calculateDistanceToTargetMeters(
                                        CAMERA_HEIGHT_METERS,
                                        TARGET_HEIGHT_METERS,
                                        CAMERA_PITCH_RADIANS,
                                        Units.degreesToRadians(camera.getLatestResult().getBestTarget().getPitch()))); 
                SmartDashboard.putNumber("Angle", camera.getLatestResult().getBestTarget().getYaw()); 
        
            }
            SmartDashboard.putBoolean("has target", camera.getLatestResult().hasTargets());
    }
}
