package com.plcs.web.wsxd.businessoperation.businessoperation.utils;

import java.util.ArrayList;
import java.util.List;

public class CompareUtils {
    public static String getMaxDataScope(List<String> dataScopes) {
        // 数字字符串数组转数值数组
        List<Integer> tmpDataScope = new ArrayList<>();
        for (String dataScope : dataScopes) {
            tmpDataScope.add(Integer.parseInt(dataScope));
        }

        Integer maxDataScope = tmpDataScope.get(0);
        int length = tmpDataScope.size();

        for (int i = 0; i < length; i++) {
            if (tmpDataScope.get(i) < maxDataScope) {
                maxDataScope = tmpDataScope.get(i);
            }
        }
        return String.valueOf(maxDataScope);
    }
}
