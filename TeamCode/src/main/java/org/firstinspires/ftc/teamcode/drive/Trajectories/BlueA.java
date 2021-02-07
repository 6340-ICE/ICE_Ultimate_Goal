package org.firstinspires.ftc.teamcode.drive.Trajectories;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.MecanumDrive6340;

@Autonomous
public class BlueA extends LinearOpMode {
       @Override
    public void runOpMode() {
           MecanumDrive6340 drive = new MecanumDrive6340(hardwareMap);

           Pose2d startPose = new Pose2d(-62,55, Math.toRadians(0));

           drive.setPoseEstimate(startPose);

           Trajectory startToA = drive.trajectoryBuilder(startPose)
               .splineTo(new Vector2d(8, 55), Math.toRadians(0))
               .build();


              Trajectory aToGoal = drive.trajectoryBuilder(startToA.end(),true)
                      .splineTo(new Vector2d(-33,22), Math.toRadians(-180))
                      .build();

              Trajectory goalToA = drive.trajectoryBuilder(aToGoal.end())
                      .splineTo(new Vector2d(8,35), Math.toRadians(-90))
                      .build();
              Trajectory aToLine1 = drive.trajectoryBuilder(goalToA.end())
                      .splineTo(new Vector2d(8,30), Math.toRadians(0))
                      .build();





           waitForStart();

           if(isStopRequested()) return;

           drive.followTrajectory(startToA);
           //Deploy Wobble Goal by setting servo to open
           //Deploy Arm
           drive.followTrajectory(aToGoal);
           drive.followTrajectory(goalToA);
           drive.followTrajectory(aToLine1);


           //Grab Goal by setting servo to close
           //drive.followTrajectory(goalToA);
           //Release Goal by setting servo to open

       }
}
