package com.team6479.lib.buttons;

import java.util.ArrayList;
import java.util.Arrays;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class provides a {@link Button} which gets its state from multiple {@link Button} objects
 *
 * @deprecated Use Trigger Composition (https://docs.wpilib.org/en/latest/docs/software/commandbased/binding-commands-to-triggers.html#composing-triggers)
 *
 * @author Thomas Quillan
 */
@Deprecated
public class MultiButton extends Button {
  private final ArrayList<Button> buttons;

  /**
   * Create a multibutton for triggering commands.
   *
   * @param joystick The GenericHID object that has the button (e.g. Joystick, KinectStick, etc)
   * @param buttonNumbers The button numbers (see {@link GenericHID#getRawButton(int) }
   */
  public MultiButton(GenericHID joystick, int... buttonNumbers) {
    // this.joystick = joystick;
    // this.buttonNumbers = buttonNumbers;
    this.buttons = new ArrayList<Button>();
    for (int buttonNumber : buttonNumbers) {
      buttons.add(new JoystickButton(joystick, buttonNumber));
    }
  }

  /**
   * Create a multibutton for triggering commands.
   *
   * @param buttons The {@link Button} objects to be tracked
   */
  public MultiButton(Button... buttons) {
    this.buttons = new ArrayList<Button>(Arrays.asList(buttons));
  }

  @Override
  public boolean get() {
    boolean check = true;
    for (Button button : buttons) {
      check = check & button.get();
    }
    return check;
  }
}
