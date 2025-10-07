package com.example.sleepapp.model;

import java.time.LocalTime;
/**
 * 機能１：起床時刻計算　用モデル
 * ユーザの就寝時刻と希望睡眠時間を保持
 */
public class WakeUpRequest {

    //ユーザの就寝時刻(入力１)
    private LocalTime bedTime;

    //希望睡眠時間　※時間単位（入力２）
    private double desiredHours;

    // --Getter  and  Setter--
    public LocalTime getBedTime(){
        return bedTime;
    }

    public void setBedTime(LocalTime bedTime){
        this.bedTime = bedTime;
    }

    public double getDesiredHours(){
        return desiredHours;
    }

    public void setDesiredHours(double desiredHours){
        this.desiredHours = desiredHours;
    }




    
}
