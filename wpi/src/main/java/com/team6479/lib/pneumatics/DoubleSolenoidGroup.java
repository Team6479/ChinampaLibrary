package com.team6479.lib.pneumatics;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.SendableBase;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;

/**
 * Allows multiple {@link DoubleSolenoid} objects to be linked together.
 */
public class DoubleSolenoidGroup extends SendableBase {
  private final DoubleSolenoid[] doubleSolenoids;
  private static int instances;

  /**
   * Create a new DoubleSolenoidGroup with the provided {@link DoubleSolenoid} objects.
   *
   * @param doubleSolenoids The {@link DoubleSolenoid} objects to add
   */
  public DoubleSolenoidGroup(DoubleSolenoid... doubleSolenoids) {
    this.doubleSolenoids = doubleSolenoids;

    for (DoubleSolenoid doubleSolenoid : doubleSolenoids) {
      addChild(doubleSolenoid);
    }

    instances++;
    setName("DoubleSolenoidGroup", instances);
  }

  @Override
  public synchronized void close() {
    for (DoubleSolenoid doubleSolenoid : doubleSolenoids) {
      doubleSolenoid.close();
    }
  }

  /**
   * Set the value of a solenoid.
   *
   * @param value The {@link Value} to set (Off, Forward, Reverse)
   */
  public void set(final Value value) {
    for (DoubleSolenoid doubleSolenoid : doubleSolenoids) {
      doubleSolenoid.set(value);
    }
  }

  /**
   * Read the current value of the solenoid.
   *
   * @return The current value of the solenoid.
   */
  public Value get() {
    return doubleSolenoids[0].get();
  }

  @Override
  public void initSendable(SendableBuilder builder) {
    builder.setSmartDashboardType("Double Solenoid");
    builder.setActuator(true);
    builder.setSafeState(() -> set(Value.kOff));
    builder.addStringProperty("Value", () -> get().name().substring(1), value -> {
      if ("Forward".equals(value)) {
        set(Value.kForward);
      } else if ("Reverse".equals(value)) {
        set(Value.kReverse);
      } else {
        set(Value.kOff);
      }
    });
  }
}
