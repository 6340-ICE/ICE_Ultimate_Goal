package org.firstinspires.ftc.teamcode.drive.Trajectories;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.MecanumDrive6340;

@Autonomous
public class Redparkonly extends LinearOpMode {
       @Override
    public void runOpMode() {
           MecanumDrive6340 drive = new MecanumDrive6340(hardwareMap);

           Pose2d startPose = new Pose2d(-62,-55, Math.toRadians(0));

           drive.setPoseEstimate(startPose);

           Trajectory targetZoneA = drive.trajectoryBuilder(startPose)
               .splineTo(new Vector2d( 8,-55), Math.toRadians(0))
               .build();
           Trajectory aToGoal = drive.trajectoryBuilder(targetZoneA.end(), true )
                   .splineTo(new Vector2d(-27, -10), Math.toRadians(180))
                   .build();
           Trajectory goalToA = drive.trajectoryBuilder(aToGoal.end())
                   .splineTo(new Vector2d(8, -55), Math.toRadians(0))
                   .build();






           waitForStart();

           if(isStopRequested()) return;

           drive.followTrajectory(targetZoneA);
           drive.releaseGoal();
           sleep(500);
           drive.deployArm();
           drive.followTrajectory(aToGoal);           //Go back for second wobble goal
           drive.grabGoal(); //grab wobble goal
           drive.followTrajectory(goalToA);
           drive.releaseGoal();
           //return to a
           //release wobble goal
           //drive forward
           //Shoot preloaded rings

       }
}
