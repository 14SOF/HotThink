package skhu.sof14.hotthink.model.dto.post;

import lombok.Setter;


@Setter
public class RealListElementDto extends PostBase{
    String title;
    String content;
    public void setContent(String content){
        int start = content.indexOf("summary=")+8;
        int end = content.indexOf(", outline=");
        this.content = content.substring(start, end);
    }
}
