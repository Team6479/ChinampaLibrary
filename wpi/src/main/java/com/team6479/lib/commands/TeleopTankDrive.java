package com.team6479.lib.commands;

import com.team6479.lib.subsystems.TankDrive;
import edu.wpi.first.wpilibj2.command.CommandBase;
import java.util.function.DoubleSupplier;

public class TeleopTankDrive extends CommandBase {
  private final TankDrive drivetrain;
  private final DoubleSupplier speed;
  private final DoubleSupplier rotation;

  public TeleopTankDrive(TankDrive drivetrain, DoubleSupplier speed,
                         DoubleSupplier rotation) {
    this.drivetrain = drivetrain;
    this.speed = speed;
    this.rotation = rotation;
    addRequirements(this.drivetrain);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {
    drivetrain.arcadeDrive(speed.getAsDouble(), rotation.getAsDouble());
  }

  // Called once after isFinished returns true
  @Override
  public void end(boolean interrupted) {
    drivetrain.stop();
  }
}
