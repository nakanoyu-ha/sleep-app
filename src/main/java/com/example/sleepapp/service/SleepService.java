package com.example.sleepapp.service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class SleepService {
        
    //睡眠サイクル時間(90分)を定数として定義
    private static final int CYCLE_MINUTES = 90;

        /**
     * 【機能1: 起床時刻の計算 】
     * 就寝時刻と希望睡眠時間から、起床時刻の候補を計算する。
     * @param bedTime 就寝時刻
     * @param desiredMinutes 希望睡眠時間 (分単位)
     * @return 起床時刻の候補リスト
     */
    public List<LocalTime> calcWakeUpTimeCandidates(LocalTime bedTime,int desiredMinutes) {
        List<LocalTime> wakeUpCandidates = new ArrayList<>();
        
        //90分単位で加算しながら、起床時刻候補を探す
        for (int i = 3; i <=8; i++) {   //4.5h(3*90m)~12h(8*90m)の間を想定
            int totalMinutes = CYCLE_MINUTES * i;
            LocalTime candidate = bedTime.plusMinutes(totalMinutes);

            //希望睡眠時間との差を比較
            int diff = Math.abs(totalMinutes-desiredMinutes);
            //+- 90分以内なら採用
            if(diff <= 90){
                wakeUpCandidates.add(candidate);
            }
            
        }
        //候補のリストを返す
        return wakeUpCandidates;
    }

        /**
     * 【機能2: 就寝時刻の計算】
     * 目標起床時刻と希望睡眠時間から、就寝時刻の候補を計算する。
     * @param targetWakeUpTime 目標起床時刻
     * @param desiredMinutes 希望睡眠時間 (分単位)
     * @return 就寝時刻の候補リスト
     */
    public List<LocalTime> calcBedTimeCandidates(LocalTime targetWakeUpTime,int desiredMinutes) {
        List<LocalTime> bedTimeCandidates = new ArrayList<>();
        
        //90分単位で加算しながら、就寝時刻候補を探す
        for (int i = 3; i <=8; i++) {   //4.5h(3*90m)~12h(8*90m)の間を想定
            int totalMinutes = CYCLE_MINUTES * i;
            LocalTime candidate = targetWakeUpTime.minusMinutes(totalMinutes);

            //希望睡眠時間との差を比較
            int diff = Math.abs(totalMinutes-desiredMinutes);
            //+- 90分以内なら採用
            if(diff <= 90){
                bedTimeCandidates.add(candidate);
            }
            
        }
        
        //候補のリストを返す
        return bedTimeCandidates;
    }

}



