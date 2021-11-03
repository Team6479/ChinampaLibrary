package com.team6479.lib.util;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import java.util.HashMap;
import java.util.Map;

/**
 * Class to simplify interacting with the Limelight
 *
 * @author Thomas Quillan
 * @author Leo Wilson
 */
@Deprecated
public final class Limelight {
  public enum LEDState {
    /** Use the LED Mode set in the current pipeline */
    Auto(0),
    /** Force off */
    Off(1),
    /** Force blink */
    Blink(2),
    /** Force on */
    On(3);

    public int value;
    private static Map<Integer, LEDState> map = new HashMap<>();

    private LEDState(int value) {
      this.value = value;
    }

    static {
      for (LEDState ledState : LEDState.values()) {
        map.put(ledState.value, ledState);
      }
    }

    public static LEDState valueOf(int ledState) {
      return (LEDState) map.get(ledState);
    }
  }

  public enum CamMode {
    /** Vision processor */
    VisionProcessor(0),
    /** Increases exposure, disables vision processing */
    DriverCamera(1);

    public int value;
    private static Map<Integer, CamMode> map = new HashMap<>();

    private CamMode(int value) {
      this.value = value;
    }

    static {
      for (CamMode camMode : CamMode.values()) {
        map.put(camMode.value, camMode);
      }
    }

    public static CamMode valueOf(int camMode) {
      return (CamMode) map.get(camMode);
    }
  }

  public enum Pipeline {
    P0(0),
    P1(1),
    P2(2),
    P3(3),
    P4(4),
    P5(5),
    P6(6),
    P7(7),
    P8(8),
    P9(9);

    public int value;
    private static Map<Integer, Pipeline> map = new HashMap<>();

    private Pipeline(int value) {
      this.value = value;
    }

    static {
      for (Pipeline pipeline : Pipeline.values()) {
        map.put(pipeline.value, pipeline);
      }
    }

    public static Pipeline valueOf(int pipeline) {
      return (Pipeline) map.get(pipeline);
    }
  }

  public enum StreamMode {
    /** Side-by-side streams if a webcam is attached to Limelight */
    Standard(0),
    /**
     * The secondary camera stream is placed in the lower-right corner of the primary camera stream
     */
    PiPMain(1),
    /**
     * The primary camera stream is placed in the lower-right corner of the secondary camera stream
     */
    PiPSecondary(2);

    public int value;
    private static Map<Integer, StreamMode> map = new HashMap<>();

    private StreamMode(int value) {
      this.value = value;
    }

    static {
      for (StreamMode streamMode : StreamMode.values()) {
        map.put(streamMode.value, streamMode);
      }
    }

    public static StreamMode valueOf(int streamMode) {
      return (StreamMode) map.get(streamMode);
    }
  }

  public enum SnapshotState {
    /** Stop taking snapshots */
    Off(0),
    /** Take two snapshots per second */
    Snapshot(1);

    public int value;
    private static Map<Integer, SnapshotState> map = new HashMap<>();

    private SnapshotState(int value) {
      this.value = value;
    }

    static {
      for (SnapshotState snapshotState : SnapshotState.values()) {
        map.put(snapshotState.value, snapshotState);
      }
    }

    public static SnapshotState valueOf(int snapshotState) {
      return (SnapshotState) map.get(snapshotState);
    }
  }

  private static NetworkTable limelightTable =
      NetworkTableInstance.getDefault().getTable("limelight");

  /** Don't let anyone instantiate this class. */
  private Limelight() {}

  /**
   * Whether the limelight has any valid targets
   *
   * @return True if the Limelight has a valid target
   */
  public static boolean hasTarget() {
    return limelightTable.getEntry("tv").getDouble(0) == 1;
  }

  /**
   * Retrieves the Horizontal Offset From Crosshair To Target from the Limelight
   *
   * @return Horizontal Offset From Crosshair To Target (LL1: -27 degrees to 27 degrees | LL2: -29.8
   *     to 29.8 degrees)
   */
  public static double getXOffset() {
    return limelightTable.getEntry("tx").getDouble(0);
  }

  /**
   * Retrieves the Vertical Offset From Crosshair To Target from the Limelight
   *
   * @return Vertical Offset From Crosshair To Target (LL1: -20.5 degrees to 20.5 degrees | LL2:
   *     -24.85 to 24.85 degrees)
   */
  public static double getYOffset() {
    return limelightTable.getEntry("ty").getDouble(0);
  }

  /**
   * Retrieves the Target Area from the Limelight
   *
   * @return Target Area (0% of image to 100% of image)
   */
  public static double getArea() {
    return limelightTable.getEntry("ta").getDouble(0);
  }

  /**
   * Retrieves the Skew from the Limelight
   *
   * @return Skew or rotation (-90 degrees to 0 degrees)
   */
  public static double getSkew() {
    return limelightTable.getEntry("ts").getDouble(0);
  }

  /**
   * Sets limelight’s LED state
   *
   * @param state {@link LEDState} integer value of state to set
   */
  public static void setLEDState(LEDState state) {
    limelightTable.getEntry("ledMode").setDouble(state.value);
  }

  /**
   * Gets limelight’s LED state
   *
   * @return current LED state
   */
  public static LEDState getLEDState() {
    return LEDState.valueOf((int) limelightTable.getEntry("ledMode").getDouble(-1));
  }

  /**
   * Sets limelight’s operation mode
   *
   * @param mode {@link CamMode} to set
   */
  public static void setCamMode(CamMode mode) {
    limelightTable.getEntry("camMode").setDouble(mode.value);
  }

  /**
   * Sets limelight’s current pipeline.
   *
   * @param pipeline the {@link Pipeline} to be set. Should be a value of 0-9
   */
  public static void setPipeline(Pipeline pipeline) {
    limelightTable.getEntry("pipeline").setDouble(pipeline.value);
  }

  /**
   * Sets limelight’s operation mode
   *
   * @param mode {@link StreamMode} to set
   */
  public static void setStreamMode(StreamMode mode) {
    limelightTable.getEntry("stream").setDouble(mode.value);
  }

  /**
   * Sets limelight’s operation mode
   *
   * @param state {@link SnapshotState} to set
   */
  public static void setSnapshotState(SnapshotState state) {
    limelightTable.getEntry("snapshot").setDouble(state.value);
  }
}
