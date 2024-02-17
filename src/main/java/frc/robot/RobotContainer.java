package frc.robot;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;
import com.pathplanner.lib.path.PathPlannerPath;
import com.pathplanner.lib.util.HolonomicPathFollowerConfig;
import com.pathplanner.lib.util.PIDConstants;
import com.pathplanner.lib.util.ReplanningConfig;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PS4Controller.Button;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.LauncherConstants;
import frc.robot.Constants.OIConstants;
 import frc.robot.commands.CMDAlign;
 import frc.robot.commands.CMDDrive;
import frc.robot.commands.CMDShooter;
import frc.robot.commands.CMDLaunchNote;
import frc.robot.commands.CMDPrepareLaunch;
 import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.SUBShooter;
import frc.robot.subsystems.SUBVision;
import frc.robot.subsystems.SUBShooter.*;
import frc.utils.RoaringUtils;
import frc.utils.RoaringUtils.DeadzoneUtils;

/*
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems
  public final static DriveSubsystem m_robotDrive = new DriveSubsystem();
    public static final CMDDrive driveRobotCommand = new CMDDrive();
    public final static SUBShooter m_sUBShooter = new SUBShooter();
    public static final CMDPrepareLaunch m_CMDPrepareLaunch = new CMDPrepareLaunch(m_sUBShooter);
   public static final SUBShooter m_SUBShooter = new SUBShooter();
   public static final SUBVision m_SUBVision = new SUBVision();
    public static final CMDAlign m_CMDAlign = new CMDAlign();

SendableChooser<String> PathPlannerautoChooser = new SendableChooser<String>();
    SendableChooser<String> ChoreoautoChooser = new SendableChooser<String>();
  
    public static SendableChooser<Boolean> fieldOrientedChooser = new SendableChooser<Boolean>();
    public static SendableChooser<String> pathChooser = new SendableChooser<String>();
  
    public static SendableChooser<Boolean> rateLimitChooser = new SendableChooser<Boolean>();

  // The driver's controller
   public static XboxController m_driverController = new XboxController(OIConstants.kDriverControllerPort);
  public static CommandXboxController OIDriverController2 = new CommandXboxController(OIConstants.kDriver2ControllerPort);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    AutoBuilder.configureHolonomic(
                m_robotDrive::getPose, // Robot pose supplier
                m_robotDrive::resetOdometry, // Method to reset odometry (will be called if your auto has a starting pose)
                m_robotDrive::getspeed, // ChassisSpeeds supplier. MUST BE ROBOT RELATIVE
                m_robotDrive::driveRobotRelative, // Method that will drive the robot given ROBOT RELATIVE ChassisSpeeds
                new HolonomicPathFollowerConfig( // HolonomicPathFollowerConfig, this should likely live in your Constants class
                        new PIDConstants(1.5, 0.0, 0.0), // Translation PID constants
                        new PIDConstants(12, 0.0, 0.0), // Rotation PID constants
                        3, // Max module speed, in m/s
                        Units.inchesToMeters(18.2), // Drive base radius in meters. Distance from robot center to furthest module.
                        new ReplanningConfig() // Default path replanning config. See the API for the options here
                ),
                () -> {
                    // Boolean supplier that controls when the path will be mirrored for the red alliance
                    // This will flip the path being followed to the red side of the field.
                    // THE ORIGIN WILL REMAIN ON THE BLUE SIDE

                    var alliance = DriverStation.getAlliance();
                    if (alliance.isPresent()) {
                        return alliance.get() == DriverStation.Alliance.Red;
                    }
                    return false;
                },
                m_robotDrive // Reference to this subsystem to set requirements
        );
    // Configure the button bindings
    NamedCommands.registerCommand("Intake", m_SUBShooter.getIntakeCommand().withTimeout(1));
 
    fieldOrientedChooser.setDefaultOption("Field Oriented", true);
    fieldOrientedChooser.addOption("Robot Oriented", false);
    rateLimitChooser.setDefaultOption("False", false);
    rateLimitChooser.addOption("True", true);
    SmartDashboard.putData("Rate limit",rateLimitChooser);
    SmartDashboard.putData("Field oriented",fieldOrientedChooser);



    configureButtonBindings();
   
    SmartDashboard.putData("Path planner chooser", PathPlannerautoChooser);
    pathChooser.setDefaultOption("Choreo", "choreo");
    pathChooser.addOption("PathPlanner", "pathplanner");
    pathChooser.addOption("No auto", "none");
    SmartDashboard.putData("Path follower", pathChooser);
    // Configure default commands
    m_robotDrive.setDefaultCommand(
        // The left stick controls translation of the robot.
        // Turning is controlled by the X axis of the right stick.
        /*new RunCommand(
            () -> m_robotDrive.drive(
                -MathUtil.applyDeadband(m_driverController.getLeftY(), OIConstants.kDriveDeadband),
                -MathUtil.applyDeadband(m_driverController.getLeftX(), OIConstants.kDriveDeadband),
                -MathUtil.applyDeadband(m_driverController.getRightX(), OIConstants.kDriveDeadband),
                true, true),
            m_robotDrive));*/
            driveRobotCommand);
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by
   * instantiating a {@link edu.wpi.first.wpilibj.GenericHID} or one of its
   * subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then calling
   * passing it to a
   * {@link JoystickButton}.
   */
  private void configureButtonBindings() {
    new JoystickButton(m_driverController, Button.kR1.value)
        .whileTrue(new RunCommand(
            () -> m_robotDrive.setX(),
            m_robotDrive));
  OIDriverController2
  .rightBumper()
  .whileTrue(
      new CMDPrepareLaunch(m_SUBShooter)
          .withTimeout(LauncherConstants.kLauncherDelay)
          .andThen(new CMDLaunchNote(m_SUBShooter))
          .handleInterrupt(() -> m_SUBShooter.stop()));

OIDriverController2.leftBumper().whileTrue(m_SUBShooter.getIntakeCommand());
}
  

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // Create config for trajectory
          if (pathChooser.getSelected() == "choreo") {
      PathPlannerPath ChoreoTraj = PathPlannerPath.fromChoreoTrajectory(ChoreoautoChooser.getSelected());
      return AutoBuilder.followPath(ChoreoTraj);
    } else if (pathChooser.getSelected() == "pathplanner") {
      return AutoBuilder.buildAuto(PathPlannerautoChooser.getSelected());
    }
    else return null;
  }
}