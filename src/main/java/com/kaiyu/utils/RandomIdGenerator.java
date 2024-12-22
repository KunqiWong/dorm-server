package com.kaiyu.utils;
import java.util.Random;
import java.util.UUID;

public class RandomIdGenerator {

    // 用于生成随机ID的字符集
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final Random RANDOM = new Random();

    // 生成随机ID（例如：123456）
    public static String generateRandomId(int length) {
        StringBuilder stringBuilder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            stringBuilder.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        return stringBuilder.toString();
    }

    // 生成随机APPID（UUID）
    public static String generateRandomAppId() {
        return UUID.randomUUID().toString().toUpperCase(); // 使用UUID生成AppID，格式为 "xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx"
    }

    // 生成完整的INSERT语句
    public static String generateInsertStatement(String name) {
        String id = generateRandomId(6); // 随机ID，假设长度为6
        String appid = generateRandomAppId(); // 生成随机APPID
        return String.format("INSERT INTO ECOLOGY_BIZ_EC(ID, APPID, NAME) VALUES('%s', '%s', '%s');", id, appid, name);
    }

    public static void main(String[] args) {
        // 你可以将公司名称传递给generateInsertStatement方法
        String name = "上海泛微网络科技股份有限公司";
        String insertStatement = generateInsertStatement(name);
        System.out.println(insertStatement);
    }
}
