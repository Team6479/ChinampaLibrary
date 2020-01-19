package com.team6479.lib.motors.ctre;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

/**
 * This class is a thin wrapper around the {@link TalonSRX} that reduces CAN bus / CPU overhead by skipping
 * duplicate set commands. (By default the Talon flushes the Tx buffer on every set call).
 */
public class LazyTalonSRX extends TalonSRX {
  protected double prevSet = 0;
  protected ControlMode prevControlMode = null;

  public LazyTalonSRX(int deviceNumber) {
    super(deviceNumber);
  }

  public double getPreviousSet() {
    return prevSet;
  }

  @Override
  public void set(ControlMode mode, double value) {
    if (value != prevSet || mode != prevControlMode) {
      prevSet = value;
      prevControlMode = mode;
      super.set(mode, value);
    }
  }
}
