package skhu.sof14.hotthink.model.dto.post;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class PostTitleAndTypeDto extends PostBase{
    String title;
    String type;

    public void setType(String type){
        switch (type){
            case "프리":
                this.type = "프리띵크";
                break;
            case "핫":
                this.type = "핫띵크";
                break;
            case "리얼":
                this.type = "리얼띵크";
                break;
            case "QNA":
                this.type = "QNA";
                break;
            default:
                this.type = "자유게시판";
        }
    }
}
