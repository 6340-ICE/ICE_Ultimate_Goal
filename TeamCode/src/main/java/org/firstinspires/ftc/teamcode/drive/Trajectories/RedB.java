package org.firstinspires.ftc.teamcode.drive.Trajectories;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.MecanumDrive6340;

@Autonomous
public class RedB extends LinearOpMode {
       @Override
    public void runOpMode() {
           MecanumDrive6340 drive = new MecanumDrive6340(hardwareMap);

           Pose2d startPose = new Pose2d(-62,-55, Math.toRadians(0));

           drive.setPoseEstimate(startPose);

           Trajectory startToB = drive.trajectoryBuilder(startPose)
               .splineTo(new Vector2d( 35,-52), Math.toRadians(0))
               .build();
           Trajectory shiftLeft = drive.trajectoryBuilder(startToB.end())
                   .strafeLeft(new Vector2d(35, -30), Math.toRadians(0))
                   .build();
           Trajectory bToGoal = drive.trajectoryBuilder(shiftLeft.end(),  true)
                   .splineTo(new Vector2d(-33 -22), Math.toRadians(180))
                   .build();
           Trajectory goalToB = drive.trajectoryBuilder(bToGoal.end())
                   .splineTo(new Vector2d(30, -22), Math.toRadians(90))
                   .build();
           Trajectory bLine1 = drive.trajectoryBuilder(goalToB.end())
                  .splineto(new Vector2d(30, -12), Math.toRadians(90))
                  .build();
                  Trajectory bLine2 = drive.trajectoryBuilder(goalToB.end())
                   .splineTo(new Vector2d( 8, -6 ), Math.toRadians(0))
                   .build();








           waitForStart();

           if(isStopRequested()) return;

           drive.followTrajectory(startToB);
           drive.releaseGoal();
           sleep(1000);
           drive.deployArm();
           drive.followTrajectory(shiftLeft);
           drive.followTrajectory(bToGoal);//Go back for second wobble goal
           drive.grabGoal(); //grab wobble goal
           drive.followTrajectory(goalToB);
           drive.releaseGoal();
           sleep(1000);
           drive.followTrajectory(aLine);
           drive.followTrajectory(bLine);


           //Shoot preloaded rings

       }
}
