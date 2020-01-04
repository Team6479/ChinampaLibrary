package com.team6479.lib.controllers;

import com.team6479.lib.buttons.POVButton;
import com.team6479.lib.wpioverride.XboxController;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import java.util.HashMap;
import java.util.Objects;

/**
 * This class provides a {@link XboxController} that contains various enhancements for command based
 * programming.
 *
 * @author Thomas Quillan
 */
public class CBXboxController extends XboxController {
  private HashMap<Button, edu.wpi.first.wpilibj2.command.button.Button> buttons;
  private HashMap<Integer, edu.wpi.first.wpilibj2.command.button.Button> povButtons;

  /**
   * Create a {@link XboxController} with extensions for command based programming.
   *
   * @param port The port on the Driver Station that the joystick is plugged into.
   */
  public CBXboxController(int port) {
    super(port);
    buttons = new HashMap<Button, edu.wpi.first.wpilibj2.command.button.Button>();
    povButtons = new HashMap<Integer, edu.wpi.first.wpilibj2.command.button.Button>();
  }

  /**
   * Get a {@link Button} object for the supplied button
   *
   * @param button The ID of the button
   * @return A {@link Button} object for the supplied button.
   */
  public edu.wpi.first.wpilibj2.command.button.Button getButton(Button button) {

    if (!buttons.containsKey(button)) {
      buttons.put(button, new JoystickButton(this, button.value));
    }

    return buttons.get(button);
  }

  /**
   * Get a {@link POVButton} object for the supplied arguments
   *
   * @param pov The button number (see {@link GenericHID#getPOV(int) })
   * @param angle The angle to check the POV for
   * @param fuzzy If set to True angle will be checked to be within plus or minus 45 degrees
   * @return A {@link POVButton} object for the supplied arguments.
   */
  public edu.wpi.first.wpilibj2.command.button.Button getPOVButton(
      int pov, int angle, boolean fuzzy) {
    int povHash = Objects.hash(pov, angle, fuzzy);

    if (!povButtons.containsKey(povHash)) {
      povButtons.put(povHash, new POVButton(this, angle, fuzzy));
    }

    return povButtons.get(povHash);
  }

  /**
   * Get a {@link POVButton} object for the supplied arguments
   *
   * @param angle The angle to check the POV for
   * @param fuzzy If set to True angle will be checked to be within plus or minus 45 degrees
   * @return A {@link POVButton} object for the supplied arguments.
   */
  public edu.wpi.first.wpilibj2.command.button.Button getPOVButton(int angle, boolean fuzzy) {
    return this.getPOVButton(0, angle, fuzzy);
  }
}
