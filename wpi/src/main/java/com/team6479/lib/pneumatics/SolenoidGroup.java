package com.team6479.lib.pneumatics;

import edu.wpi.first.wpilibj.SendableBase;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;

/** Allows multiple {@link Solenoid} objects to be linked together. */
public class SolenoidGroup extends SendableBase {
  private final Solenoid[] solenoids;
  private static int instances;

  /**
   * Create a new SolenoidGroup with the provided {@link Solenoid} objects.
   *
   * @param solenoids The {@link Solenoid} objects to add
   */
  public SolenoidGroup(Solenoid... solenoids) {
    this.solenoids = solenoids;

    for (Solenoid solenoid : solenoids) {
      addChild(solenoid);
    }

    instances++;
    setName("SolenoidGroup", instances);
  }

  @Override
  public void close() {
    for (Solenoid solenoid : solenoids) {
      solenoid.close();
    }
  }

  /**
   * Set the value of a solenoid.
   *
   * @param on True will turn the solenoid output on. False will turn the solenoid output off.
   */
  public void set(boolean on) {
    for (Solenoid solenoid : solenoids) {
      solenoid.set(on);
    }
  }

  /**
   * Read the current value of the solenoid.
   *
   * @return True if the solenoid output is on or false if the solenoid output is off.
   */
  public boolean get() {
    return solenoids[0].get();
  }

  /**
   * Set the pulse duration in the PCM. This is used in conjunction with the startPulse method to
   * allow the PCM to control the timing of a pulse. The timing can be controlled in 0.01 second
   * increments.
   *
   * @param durationSeconds The duration of the pulse, from 0.01 to 2.55 seconds.
   * @see #startPulse()
   */
  public void setPulseDuration(double durationSeconds) {
    for (Solenoid solenoid : solenoids) {
      solenoid.setPulseDuration(durationSeconds);
    }
  }

  /**
   * Trigger the PCM to generate a pulse of the duration set in setPulseDuration.
   *
   * @see #setPulseDuration(double)
   */
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