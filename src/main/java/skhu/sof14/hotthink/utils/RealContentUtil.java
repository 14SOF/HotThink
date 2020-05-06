package skhu.sof14.hotthink.utils;

public class RealContentUtil {
    public static String[] getRealContent(String content){
        String[] strings = new String[8];
        for(int i=0; i<8; i++){
            int start = 0;
            int end = 0;
            switch (i){
                case 0:
                    start = content.indexOf("summary=")+8;
                    end = content.indexOf(", outline=");
                    break;
                case 1:
                    start = content.indexOf("outline=")+8;
                    end = content.indexOf(", content=");
                    break;
                case 2:
                    start = content.indexOf("content=")+8;
                    end = content.indexOf(", effect=");
                    break;
                case 3:
                    start = content.indexOf("effect=")+7;
                    end = content.indexOf(", similar=");
                    break;
                case 4:
                    start = content.indexOf("similar=")+8;
                    end = content.indexOf(", difference=");
                    break;
                case 5:
                    start = content.indexOf("difference=")+11;
                    end = content.indexOf(", patent=");
                    break;
                case 6:
                    start = content.indexOf("patent=")+7;
                    end = content.indexOf(", price=");
                    break;
                case 7:
                    start = content.indexOf("price=")+6;
                    end = content.length()-1;
                    break;
            }
            strings[i] = content.substring(start, end);
        }
        return strings;
    }
}
