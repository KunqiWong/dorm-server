package com.kaiyu.utils;
import java.lang.reflect.Field;

public class Compare {

    public static String compareAndGenerateLog(Object oldData, Object newData) {
        StringBuilder changes = new StringBuilder();
        try {
            // 获取对象的所有字段
            Field[] fields = oldData.getClass().getDeclaredFields();

            for (Field field : fields) {
                field.setAccessible(true); // 设置字段可访问

                Object oldValue = field.get(oldData);
                Object newValue = field.get(newData);

                if(field.getName().equals("updateBy")){
                    continue;
                }
                // 比较字段值是否变化
                if ((oldValue != null && !oldValue.equals(newValue)) || (oldValue == null && newValue != null)) {
                    // 添加修改字段信息到日志
                    changes.append(Transform.getFieldName(field.getName()))
                            .append("：'").append(oldValue.toString().length() == 0 ? "空" : oldValue)
                            .append("'=>'").append(newValue.toString().length() == 0 ? "空" : newValue)
                            .append("';  ");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return changes.toString();
    }
    

}
