package com.common.litao.mvp.util;

import com.alibaba.fastjson.JSON;
import com.common.litao.mvp.bean.MainDto;

import java.util.ArrayList;
import java.util.List;

/**
 * author: litao29
 * date: 2018/2/8
 * mail: litao29@jd.com
 * desc: 模拟测试数据类
 */

public class Test {

    public static String getData(){
        List<MainDto> list = new ArrayList<>();
        for(int i = 0;i < 10;i++){
            MainDto mainDto = new MainDto();
            mainDto.setName("马驹桥"+i);
            mainDto.setDesc("鸟不拉屎的地方"+i);
            list.add(mainDto);
        }
        return JSON.toJSONString(list);
    }
}
