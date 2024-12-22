package com.kaiyu.common.utils;

import java.util.Random;
import java.util.UUID;

public class IdUtils {

    /**
     * 生成一个随机 UUID 字符串
     * @return 带有连字符的 UUID 字符串
     */
    public static String randomUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 生成一个无连字符的 UUID 字符串
     * @return 无连字符的 UUID 字符串
     */
    public static String simpleUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 快速生成 UUID 的简化实现（等同于 randomUUID）
     * @return 带有连字符的 UUID 字符串
     */
    public static String fastUUID() {
        return randomUUID();
    }

    /**
     * 快速生成无连字符的 UUID 的简化实现
     * @return 无连字符的 UUID 字符串
     */
    public static String fastSimpleUUID() {
        return simpleUUID();
    }

    /**
     * 生成唯一图片名称
     * @return 当前时间戳 + 三位随机数
     */
    public static String genImageName() {
        long millis = System.currentTimeMillis();
        Random random = new Random();
        int end3 = random.nextInt(1000); // [0, 999]
        return millis + String.format("%03d", end3);
    }

    /**
     * 生成长整型 ID
     * @return 当前纳秒时间戳 + 两位随机数
     */
    public static String genLongId() {
        long millis = System.nanoTime();
        Random random = new Random();
        int end2 = random.nextInt(100); // [0, 99]
        String str = millis + String.format("%02d", end2);
        return str;
    }
}
