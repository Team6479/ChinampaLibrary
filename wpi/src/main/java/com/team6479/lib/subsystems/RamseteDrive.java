package com.team6479.lib.subsystems;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.RamseteCommand;

public interface RamseteDrive {
  public Pose2d getPose();
  public DifferentialDriveWheelSpeeds getWheelSpeeds();
  public void tankDriveVolts(double leftVolts, double rightVolts);
  public void resetEncoders();
  public double getLeftEncoderPos();
  public double getRightEncoderPos();
  public double getLeftEncoderVel();
  public double getRightEncoderVel();
  public double getHeading();
  public RamseteCommand getRamseteCommand(Trajectory trajectory);
}