package com.team6479.lib.subsystems;

public interface MecanumDrive extends DriveSubsystem {
  public void mecanumDrive(double speedLR, double rotation, double speedFB);
}
