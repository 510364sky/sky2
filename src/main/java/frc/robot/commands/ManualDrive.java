// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ManualSystem;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class ManualDrive extends Command {
  /** Creates a new ManualDrive. */
  private final ManualSystem m_ManualSystem;
  private final DoubleSupplier xspeedfunc;
  private final DoubleSupplier turnspeedfunc;
  private double xspeed;
  private double turnspeed;

  public ManualDrive(ManualSystem driveSystem,DoubleSupplier xspeedfunc, DoubleSupplier turnspeedfunc) {
    this.m_ManualSystem = driveSystem;
    this.xspeedfunc = xspeedfunc;
    this.turnspeedfunc = turnspeedfunc;
    addRequirements(m_ManualSystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    xspeed = xspeedfunc.getAsDouble();
    turnspeed = turnspeedfunc.getAsDouble();
    m_ManualSystem.ManualSystem(xspeed,turnspeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_ManualSystem.ManualSystem(0.0,0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
