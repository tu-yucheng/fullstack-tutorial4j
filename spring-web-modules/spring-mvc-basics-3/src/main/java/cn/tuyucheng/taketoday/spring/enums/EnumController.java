package cn.tuyucheng.taketoday.spring.enums;

import cn.tuyucheng.taketoday.spring.model.Modes;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enums")
public class EnumController {

    @GetMapping("/mode2str")
    public String getStringToMode(@RequestParam("mode") Modes mode) {
        return "good";
    }

    @GetMapping("/findbymode/{mode}")
    public String findByEnum(@PathVariable Modes mode) {
        return "good";
    }
}