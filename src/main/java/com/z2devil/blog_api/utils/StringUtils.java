package com.z2devil.blog_api.utils;

import java.util.Random;

/**
 * @program: blog_api
 * @description: 字符串工具类
 * @author: z2devil
 * @create: 2021-10-13
 **/
public class StringUtils {

    private static final char SEPARATOR = '_';

    private static final String UNKNOWN = "unknown";

    /**
     * 驼峰命名法工具
     *
     * @return toCamelCase(" hello_world ") == "helloWorld"
     * toCapitalizeCamelCase("hello_world") == "HelloWorld"
     * toUnderScoreCase("helloWorld") = "hello_world"
     */
    public static String toCamelCase(String s) {
        if (s == null) {
            return null;
        }

        s = s.toLowerCase();

        StringBuilder sb = new StringBuilder(s.length());
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == SEPARATOR) {
                upperCase = true;
            } else if (upperCase) {
                sb.append(Character.toUpperCase(c));
                upperCase = false;
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    /**
     * 随机昵称
     * @params []
     * @return String
     * @author z2devil
     * @date 2021/12/6
     */
    public static String randomNickname() {
        String[][] wordsLibrary = {
                {"勇敢","聪明","机智","善良","潇洒","美丽","无敌","可爱","真正","完美","迷人","乐观","奇妙","动人","伟大","幸福"},
                {"的","之"},
                {"狼","熊","龙","虎","橘猫","麻雀","长颈鹿","狮子","树懒","金丝猴","考拉","穿山甲","犀牛","猩猩","水獭","熊猫",
                        "树懒","袋鼠","北极熊","刺猬","河马","鲸鱼","鲶鱼","章鱼","泥鳅","金枪鱼","鳄鱼","鲤鱼","鲑鱼","鲤鱼",
                        "老鹰","白鹭","企鹅","啄木鸟","鸵鸟","天鹅","信天翁","海鸥","鸸鹋","蝴蝶","蜻蜓","隼","白金之星"}
        };
        StringBuffer res = new StringBuffer();
        Random random = new Random();
        res.append(wordsLibrary[0][random.nextInt(wordsLibrary[0].length)]);
        res.append(wordsLibrary[1][random.nextInt(wordsLibrary[1].length)]);
        res.append(wordsLibrary[2][random.nextInt(wordsLibrary[2].length)]);
        return res.toString();
    }
}
