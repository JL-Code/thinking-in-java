package org.example.basic.regular;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

/**
 * <p>创建时间: 2021/5/25 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public class Regular {

    /**
     * 提取字符串
     *
     * @param input 待提取的字符串
     * @return
     */
    public static List<String> extract(String input) {
        List<String> list = new ArrayList<>();
        String separatorLeft = "[";
        String separatorRight = "]";

        // \[(\w)*\]
        // test_proc_modifiedViewName [id] [viewName]
        // call test_proc_modifiedViewName([id],[viewName])
//        String regStr = String.format("\\%s(\\S)*\\%s", separatorLeft, separatorRight);
//        String regStr = "\\[\\S[^\\[]+]";
        String regStr = "\\[[^\\[]+]";
        Pattern pattern = Pattern.compile(regStr);

        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
//            list.add(matcher.group().substring(1, matcher.group().length() - 1));
            list.add(matcher.group());
        }
        return list;
    }
}
