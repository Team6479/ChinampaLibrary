/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/

package com.team6479.lib.commands;

import com.team6479.lib.subsystems.MecanumDrive;
import edu.wpi.first.wpilibj2.command.CommandBase;
import java.util.function.DoubleSupplier;

public class TeleopMechanumDrive extends CommandBase {
  private final MecanumDrive drivetrain;
  private final DoubleSupplier speedLR;
  private final DoubleSupplier rotation;
  private final DoubleSupplier speedFB;

  public TeleopMechanumDrive(
      MecanumDrive drivetrain,
      DoubleSupplier speedLR,
      DoubleSupplier rotation,
      DoubleSupplier speedFB) {
    this.drivetrain = drivetrain;
    this.speedLR = speedLR;
    this.rotation = rotation;
    this.speedFB = speedFB;
    addRequirements(this.drivetrain);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {
    drivetrain.mecanumDrive(speedLR.getAsDouble(), rotation.getAsDouble(), speedFB.getAsDouble());
  }

  // Called once after isFinished returns true
  @Override
  public void end(boolean interrupted) {
    drivetrain.stop();
  }
}
