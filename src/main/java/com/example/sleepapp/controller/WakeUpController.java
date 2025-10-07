package com.example.sleepapp.controller;

import com.example.sleepapp.model.WakeUpRequest;
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
public class WakeUpController {

    @Autowired
    private SleepService sleepService;

    /**
     * 入力フォームを表示 (GET /wakeup)
     */
    @GetMapping("/wakeup")
    public String showForm(Model model) {
        model.addAttribute("wakeUpRequest", new WakeUpRequest());
        model.addAttribute("wakeUpCandidates", null);
        model.addAttribute("mode", "wakeup");   //起床時刻計算or就寝時刻計算を区別
        return "sleepForm"; // View (sleepForm.html)を返す
    }

    /**
     * フォームからのデータを受け取り、起床時刻の計算を実行する (POST /wakeup)
     */
    @PostMapping("/wakeup")
    public String calculateWakeupTime(@ModelAttribute WakeUpRequest request, Model model) {
        
        // 1. フォームの入力値を取得
        // WakeUpRequest（モデル） の bedTime を「就寝時刻」として使用
        LocalTime bedTime = request.getBedTime(); 
        double desiredHours = request.getDesiredHours();
        
        if (bedTime == null || desiredHours <= 0) {
            // エラーハンドリング
            model.addAttribute("error", "就寝時刻または睡眠時間が正しくありません");
            return "sleepForm";
            
        }
        
        // 2. 計算のために時間 (double) を分 (int) に変換 キャスト・四捨五入
        int desiredMinutes = (int) Math.round(desiredHours * 60);
        
        // 3. Serviceを呼び出してビジネスロジックを実行　”起床時刻”candidatesへ代入
        List<LocalTime> candidates = sleepService.calcWakeUpTimeCandidates(bedTime, desiredMinutes);

        // 4. 結果をModelに追加
        model.addAttribute("wakeUpRequest", request);
        model.addAttribute("candidates", candidates); // 計算結果の候補リスト
        model.addAttribute("mode", "wakeup");
        
        // 5. View (sleepForm.html)を返して結果を表示
        return "sleepForm";
    }
}