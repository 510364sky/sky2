// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import javax.naming.PartialResultException;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.ResetMode;
import com.revrobotics.PersistMode;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ManualSystem extends SubsystemBase {
  private final SparkMax LFMotor;
  private final SparkMax LBMotor;
  private final SparkMax RFMotor;
  private final SparkMax RBMotor;

  private final SparkMaxConfig LFConfig;
  private final SparkMaxConfig LBConfig;
  private final SparkMaxConfig RFConfig;
  private final SparkMaxConfig RBConfig;

  private final DifferentialDrive drive;
  public ManualSystem() {
    LFMotor = new SparkMax(1,MotorType.kBrushless);
    LBMotor = new SparkMax(2,MotorType.kBrushless);
    RFMotor = new SparkMax(3,MotorType.kBrushless);
    RBMotor = new SparkMax(4,MotorType.kBrushless);
    
    LFConfig = new SparkMaxConfig();
    LBConfig = new SparkMaxConfig();
    RFConfig = new SparkMaxConfig();
    RBConfig = new SparkMaxConfig();

    drive = new DifferentialDrive(LFMotor,RFMotor);

    LFConfig.inverted(true).idleMode(IdleMode.kBrake).smartCurrentLimit(60);
    LBConfig.inverted(true).idleMode(IdleMode.kBrake).smartCurrentLimit(60).follow(LFMotor.getDeviceId());
    RFConfig.inverted(false).idleMode(IdleMode.kBrake).smartCurrentLimit(60);
    RBConfig.inverted(false).idleMode(IdleMode.kBrake).smartCurrentLimit(60).follow(RFMotor.getDeviceId());

    LFMotor.configure(LBConfig,ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
    LBMotor.configure(LBConfig, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
    RFMotor.configure(LBConfig, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
    RBMotor.configure(LBConfig, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
  }

  public void ManualSystem(double xspeed,double turnspeed){
    drive.arcadeDrive(xspeed, turnspeed);
  }  
  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
