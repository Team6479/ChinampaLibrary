package com.team6479.lib.pneumatics;

import edu.wpi.first.wpilibj.SendableBase;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;

/**
 * SolenoidGroup
 */
public class SolenoidGroup extends SendableBase {
  private final Solenoid[] solenoids;
  private static int instances;

  public SolenoidGroup(Solenoid... solenoids) {
    this.solenoids = solenoids;

    for (Solenoid solenoid : solenoids) {
      addChild(solenoid);
    }

    instances++;
    setName("SolenoidGroup", instances);
  }

  public void close() {
    for (Solenoid solenoid : solenoids) {
      solenoid.close();
    }
  }

  public void set(boolean on) {
    for (Solenoid solenoid : solenoids) {
      solenoid.set(on);
    }
  }

  public boolean get() {
    return solenoids[0].get();
  }

  public void setPulseDuration(double durationSeconds) {
    for (Solenoid solenoid : solenoids) {
      solenoid.setPulseDuration(durationSeconds);
    }
  }

  public void startPulse() {
    for (Solenoid solenoid : solenoids) {
      solenoid.startPulse();
    }
  }

  @Override
  public void initSendable(SendableBuilder builder) {
    builder.setSmartDashboardType("Solenoid");
    builder.setActuator(true);
    builder.setSafeState(() -> set(false));
    builder.addBooleanProperty("Value", this::get, this::set);
  }
}
