package moe.haozi.qingyunxiang.apiServer.Tools;

import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PathRegex {
    public String sourcePath;
    public String regexText;
    public Pattern pattern;
    public HashMap<String, Integer> params = new HashMap<>();

    public PathRegex(String rule) {
        try {
            sourcePath = rule;
            pretreatment();
            // 处理 /a/:id
            Pattern pattern1 = Pattern.compile(":((.+?)($|/))");
            Matcher res1 = pattern1.matcher(regexText);
            int i = 0;
            while (res1.find()) {
                params.put(res1.group(2), new Integer(++i));
            }
            regexText = "^" + regexText.replaceAll("(/.+?/?)", "$0")
                    .replaceAll(":(.+?($|/))", "([^/]+)$2");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pattern = Pattern.compile(regexText);
        }

    }


    /**
     * 预处理转义字符
     * @return
     */
    public PathRegex pretreatment() {
        if(StringUtils.isBlank(this.sourcePath)) {
            this.regexText = this.sourcePath;
        } else {
            this.regexText = this.sourcePath
                    .replace("\\", "\\\\").replace("*", "\\*")
                    .replace("+", "\\+").replace("|", "\\|")
                    .replace("{", "\\{").replace("}", "\\}")
                    .replace("(", "\\(").replace(")", "\\)")
                    .replace("^", "\\^").replace("$", "\\$")
                    .replace("[", "\\[").replace("]", "\\]")
                    .replace("?", "\\?").replace(",", "\\,")
                    .replace(".", "\\.").replace("&", "\\&");
        }

        return this;
    }

    public Boolean exec(String path) {
        return pattern.matcher(path).find();
    }

    public Matcher getResult(String path) {
        return pattern.matcher(path);
    }

    public String getKey(String path, String key) {
        int id = params.get(key).intValue();
        Matcher res = pattern.matcher(path);
//        while (res.find()) {
//            if(id-- == 0) {
//                return res.group();
//            }
//        }
//        return "";
        res.find();
        return res.group(id);
    }

}
