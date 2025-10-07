package com.example.sleepapp.model;

import java.time.LocalTime;

public class BedTimeRequest {
    
    /**
 * 機能２：就寝時刻計算　用モデル
 * ユーザの目標起床時刻と希望睡眠時間を保持
 */

    //ユーザの起床時刻(入力１)
    private LocalTime targetWakeUpTime;

    //希望睡眠時間　※時間単位（入力２）
    private double desiredHours;

    // --Getter  and  Setter--
    
    public LocalTime getTargetWakeUpTime(){
        return targetWakeUpTime;
    }

    public void setTargetWakeUpTime(LocalTime targetWakeUpTime){
        this.targetWakeUpTime = targetWakeUpTime;
    }

    public double getDesiredHours(){
        return desiredHours;
    }

    public void setDesiredHours(double desiredHours){
        this.desiredHours = desiredHours;
    }

}
