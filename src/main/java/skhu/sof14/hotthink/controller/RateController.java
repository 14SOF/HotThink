package skhu.sof14.hotthink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import skhu.sof14.hotthink.model.dto.rate.RateCreateDto;
import skhu.sof14.hotthink.service.RateService;

@RestController
public class RateController {

    @Autowired
    RateService rateService;

    @PostMapping("/create/rate")
    public boolean createRate(@RequestBody RateCreateDto dto) {
        rateService.save(dto);
        return true;
    }
}
