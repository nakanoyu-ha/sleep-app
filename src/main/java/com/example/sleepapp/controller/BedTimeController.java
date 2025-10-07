package com.example.sleepapp.controller;

import com.example.sleepapp.model.BedTimeRequest;
import com.example.sleepapp.service.SleepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalTime;
import java.util.List;

@Controller
public class BedTimeController {
    
    @Autowired
    private SleepService sleepService;

    @GetMapping("/bed")
    public String showForm(Model model) {
        model.addAttribute("bedTimeRequest", new BedTimeRequest());
        model.addAttribute("bedTimeCandidates", null); 
        model.addAttribute("mode", "bed");
        return "sleepForm"; // View (sleeptimeForm.html)を返す
    }

    /**
     * フォームからのデータを受け取り、睡眠時刻の計算を実行する (POST /bed)
     */
    @PostMapping("/bed")
    public String calculateBedTime(@ModelAttribute BedTimeRequest request, Model model) {
        
        // 1. フォームの入力値を取得
        // BedTimeRequest（モデル） の targetWakeUpTime を「起床時刻」として使用
        LocalTime targetWakeUpTime = request.getTargetWakeUpTime(); 
        double desiredHours = request.getDesiredHours();
        
        if (targetWakeUpTime == null || desiredHours <= 0) {
            // エラーハンドリング
            model.addAttribute("error", "起床時刻または睡眠時間が正しくありません");
            return "sleepForm";
            
        }
        
        // 2. 計算のために時間 (double) を分 (int) に変換 キャスト・四捨五入
        int desiredMinutes = (int) Math.round(desiredHours * 60);
        
        // 3. Serviceを呼び出してビジネスロジックを実行 ”就寝時刻”candidatesへ代入
        List<LocalTime> candidates = sleepService.calcBedTimeCandidates(targetWakeUpTime, desiredMinutes);

        // 4. 結果をModelに追加
        model.addAttribute("bedTimeRequest", request);
        model.addAttribute("candidates", candidates); // 計算結果の候補リスト
        model.addAttribute("mode", "bed");
        
        // 5. View (sleepForm.html)を返して結果を表示
        return "sleepForm";
    }
}
