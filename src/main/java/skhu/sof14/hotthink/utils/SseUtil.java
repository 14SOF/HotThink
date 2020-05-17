package skhu.sof14.hotthink.utils;

import skhu.sof14.hotthink.model.dto.message.MessageDto;

public class SseUtil {
    public static String messageDtoToString(MessageDto dto){
        return "Message/sender:"+dto.getSender().getId()+"/"
                +"content:"+dto.getContent()+"/"
                +"dateTime:"+dto.getDateTime().toString();
    }
}
