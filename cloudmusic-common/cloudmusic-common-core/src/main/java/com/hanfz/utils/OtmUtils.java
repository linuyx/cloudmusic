package com.hanfz.utils;

import com.hanfz.pojo.param.OtmParam;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Linuyx
 * @Description
 * @Date Created in 2022-03-03 19:50
 */
public class OtmUtils {

    /**
     * 获取一对多映射集合
     *
     * @param ids     id
     * @param oneId   一对多中一的id
     * @param manyIds 一对多中多的id
     * @return
     */
    public static List<OtmParam> getOtms(List<Long> ids, Long oneId, List<Long> manyIds){
        int n = ids.size();
        List<OtmParam> otmParams = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            OtmParam otmParam = OtmParam.builder()
                    .id(ids.get(i))
                    .oneId(oneId)
                    .manyId(manyIds.get(i))
                    .build();

            otmParams.add(otmParam);
        }

        return otmParams;
    }



}
