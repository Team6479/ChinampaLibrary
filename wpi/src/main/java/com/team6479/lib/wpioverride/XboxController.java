package com.team6479.lib.wpioverride;

public class XboxController extends edu.wpi.first.wpilibj.XboxController {
  public enum Axis {
    kLeftJoystickX(0),
    kLeftJoystickY(1),
    kRightJoystickX(4),
    kRightJoyStickY(5),
    kLeftTrigger(2),
    kRightTrigger(3);

    @SuppressWarnings({"MemberName", "PMD.SingularField"})
    public final int value;

    Axis(int value) { this.value = value; }
  }

  private double deadZone;

  /**
   * Construct an instance of a joystick. The joystick index is the USB port on
   * the drivers station.
   *
   * @param port The port on the Driver Station that the joystick is plugged
   *     into.
   */
  public XboxController(int port) {
    super(port);
    deadZone = 0.2;
  }

  @Override
  public double getRawAxis(int axis) {
    if (axis == Axis.kLeftTrigger.value || axis == Axis.kRightTrigger.value) {
      return super.getRawAxis(axis);
    }

    double x;
    double y;

    if (axis == Axis.kLeftJoystickX.value ||
        axis == Axis.kLeftJoystickY.value) {
      x = super.getRawAxis(Axis.kLeftJoystickX.value);
      y = super.getRawAxis(Axis.kLeftJoystickY.value);
    } else {
      x = super.getRawAxis(Axis.kRightJoystickX.value);
      y = super.getRawAxis(Axis.kRightJoyStickY.value);
    }

    // Magnitude
    double mag = Math.sqrt((x * x) + (y * y));

    if (mag > deadZone) {
      double range = 1.0 - deadZone;
      double normMag = Math.min(1.0, (mag - deadZone) / range);
      double scale = normMag / mag;
      x = x * scale;
      y = y * scale;
    } else {
      x = 0;
      y = 0;
    }

    if (axis == Axis.kLeftJoystickX.value ||
        axis == Axis.kRightJoystickX.value) {
      return x;
    } else {
      return y;
    }
  }
}
