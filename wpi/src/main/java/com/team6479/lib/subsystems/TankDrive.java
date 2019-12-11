package com.team6479.lib.subsystems;

public interface TankDrive extends DriveSubsystem {
  public void arcadeDrive(double speed, double rotation);

  public void tankDrive(double leftSpeed, double rightSpeed);
}
