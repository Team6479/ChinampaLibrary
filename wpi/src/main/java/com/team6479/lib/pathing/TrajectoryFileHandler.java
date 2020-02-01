package com.team6479.lib.pathing;

import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryUtil;
import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Paths;

/**
 *
 *
 * <h1>TrajectoryFileHandler</h1>
 *
 * Generates trajectories from Pathweaver JSON files
 *
 * @author Aiden Onstott
 */
public class TrajectoryFileHandler {

  /**
   * Returns all files in a given directory with a specified ending
   *
   * @param end File ending to search for (e.g. ".json", ".csv", ".txt")
   * @return A file array of files
   */
  private static File[] getFiles(String end) {
    File filePath = new File(Filesystem.getDeployDirectory() + "/paths/output/");
    return filePath.listFiles(
        new FilenameFilter() {
          @Override
          public boolean accept(File dir, String name) {
            return name.toLowerCase().endsWith(end);
          }
        });
  }

  /**
   * Gets generated trajectories in a {@link SendableChooser}
   *
   * @return The generated trajectories
   */
  public static SendableChooser<Trajectory> getTrajectories() {
    SendableChooser<Trajectory> tChooser = new SendableChooser<Trajectory>();
    try {
      File[] files = getFiles(".json");
      for (File file : files) {
        tChooser.addOption(
            file.getName(), TrajectoryUtil.fromPathweaverJson(Paths.get(file.getAbsolutePath())));
      }
    } catch (Exception e) {
      System.out.println(e.getStackTrace());
    }
    return tChooser;
  }
}
