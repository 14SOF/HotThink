package skhu.sof14.hotthink.model.dto.post;

import skhu.sof14.hotthink.service.PostService;

import java.util.Arrays;

public class RealReadDto {
    String summary;
    String outline;
    String content;
    String effect;
    String similar;
    String difference;
    String patent;
    int price;
    public RealReadDto(String content){
        String[] strings = PostService.getRealContent(content);
        this.summary = strings[0];
        this.outline = strings[1];
        this.content = strings[2];
        this.effect = strings[3];
        this.similar = strings[4];
        this.difference = strings[5];
        this.patent = strings[6];
        this.price = Integer.parseInt(strings[7]);
    }
}
