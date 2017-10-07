// PushBotManual_Connor

package org.firstinspires.ftc.teamcode;

import android.os.CountDownTimer;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.ControlFreaks.*;
/**
 * Created by adevries on 11/6/2015.
 */
@TeleOp(name="Tank 1", group="Manual")  // @Autonomous(...) is the other common choice
//@Disabled
public class CFPushBotManual_Connor extends LinearOpMode {

    /* Declare OpMode members. */
    CFPushBotHardware robot;   // Use a Pushbot's hardware

    private static boolean bothControllersEnabled = false;
    private byte v_neopixels_mode = 0;


    //--------------------------------------------------------------------------
    //
    // loop
    //
    /**
     * Implement a state machine that controls the robot during
     * manual-operation.  The state machine uses gamepad input to transition
     * between states.
     *
     * The system calls this member repeatedly while the OpMode is running.
     */
    float stickdeadzone = .1f;
    boolean buttonXReleased = true;
    boolean buttonBReleased = true;
    boolean buttonAReleased = true;
    boolean buttonDpadUpReleased = true;
    boolean buttonDpadDownReleased = true;
    boolean buttonLeftBumperReleased = true;
    boolean buttonRightBumperReleased = true;
    boolean buttonG2XReleased = true;
    boolean buttonG2YReleased = true;
    boolean buttonG2AReleased = true;
    boolean buttonG2BReleased = true;
    ElapsedTime myFliperRetractElapsedTime;
    @Override
    public void runOpMode() throws InterruptedException {

        try {
            /* Declare OpMode members. */
            robot           = new CFPushBotHardware();   // Use a Pushbot's hardware
            robot.init(this);

            robot.setupManualDrive();
            //robot.vuforia_Init();
            // Wait for the game to start (driver presses PLAY)
            robot.led7seg_timer_init(120);
            robot.run_without_encoders();
            waitForStart();
            robot.debugOff();
            robot.led7seg_timer_start(120);


            // run until the end of the match (driver presses STOP)
            while (opModeIsActive()) {
                try {
                    robot.hardware_loop();
                    if(gamepad1.left_trigger > .2 || gamepad1.right_trigger > .2 ) {
                        robot.set_drive_power(-gamepad1.left_stick_y, -gamepad1.right_stick_y);
                    }else{
                        robot.set_drive_power(-gamepad1.left_stick_y/1.5f, -gamepad1.right_stick_y/1.5f);
                    }
                    if(gamepad1.left_bumper){
                        if(buttonLeftBumperReleased == true) {
                            robot.pushbutton_left_toggle();
                            buttonLeftBumperReleased = false;
                        }
                    }else{
                        buttonLeftBumperReleased = true;
                    }
                    if(gamepad1.right_bumper){
                        if(buttonRightBumperReleased == true) {
                            robot.pushbutton_right_toggle();
                            buttonRightBumperReleased = false;
                        }
                    }else{
                        buttonRightBumperReleased = true;
                    }
                    if(gamepad2.dpad_up){
                        if(buttonDpadUpReleased == true) {
                            robot.lifter_toggle();
                            buttonDpadUpReleased = false;
                        }
                    }else{
                        buttonDpadUpReleased = true;
                    }
                    if(gamepad2.dpad_down){
                        if(buttonDpadDownReleased == true) {
                            robot.lifter_toggle_reverse();
                            buttonDpadDownReleased = false;
                        }
                    }else{
                        buttonDpadDownReleased = true;
                    }
                    if(gamepad1.x ){
                        if(buttonXReleased == true) {
                            robot.blueled_toggle();
                            buttonXReleased = false;
                        }
                    }else{
                        buttonXReleased = true;
                    }
                    if(gamepad1.b){
                        if(buttonBReleased == true) {
                            robot.redled_toggle();
                            buttonBReleased = false;
                        }
                    }else{
                        buttonBReleased = true;
                    }
                    if(gamepad1.a){
                        if(buttonAReleased == true) {
                            robot.elbow_toggle();
                            buttonAReleased = false;
                        }
                    }else{
                        buttonAReleased = true;
                    }
                    if(gamepad2.x){
                        if(buttonG2XReleased == true) {
                            //robot.rackpinion_load();
                            buttonG2XReleased = false;
                        }
                    }else{
                        if(buttonG2XReleased == false) {
                            //if (robot.catapult_load_complete()) {
                            buttonG2XReleased = true;
                            //}
                        }
                    }
                    if(gamepad2.b){
                        if(buttonG2BReleased == true) {
                            //robot.catapult_fire();
                            buttonG2BReleased = false;
                        }
                    }else{
                        if(buttonG2BReleased == false) {
                            //if (robot.catapult_fire_complete()) {
                            buttonG2BReleased = true;
                            // }
                        }
                    }
                    if(gamepad2.y){
                        if(buttonG2YReleased == true) {
                            robot.blockgrabber_extend();
                            //myFliperRetractElapsedTime = null;
                            buttonG2YReleased = false;
                        }
                    }else{
                        buttonG2YReleased = true;
                    }
                    if(gamepad2.a){
                        if(buttonG2AReleased == true) {
                            robot.blockgrabber_retract();
                            buttonG2AReleased = false;
                            //myFliperRetractElapsedTime = new ElapsedTime();
                        }
                    }else{
//                        if(myFliperRetractElapsedTime != null){
//                            if (myFliperRetractElapsedTime.seconds() > 3){
//                                robot.flipper_extend();
//                                myFliperRetractElapsedTime = null;
//                            }
//                        }
                        buttonG2AReleased = true;

                    }
                    //robot.waitForTick(2);
                }catch(Exception ex){
                    robot.set_error_message("Fatal Error "  + ex.toString());
                }
            } // loop

        }catch(Exception ex){
            robot.set_error_message("Fatal Error "  + ex.toString());
        }
    }


}