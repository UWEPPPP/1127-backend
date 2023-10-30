package com.topview.util;

import org.fisco.bcos.sdk.v3.codec.wrapper.ABIObject;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 合约实用程序
 *
 * @author ashinnotfound
 * @date 2023/10/30
 */
@Component
public class ContractUtil {

    /**
     * 处理返回结果的工具类
     *
     * @author lql
     * @date 2023/02/08
     */
    public static Map<String,Object> decodeReturnStruct(List<ABIObject> args){
        Map<String,Object> results = new HashMap<>();
        for (ABIObject item: args) {
            switch (item.getValueType()) {
                case UINT:
                    results.put(item.getName(),item.getNumericValue().getValue());
                    break;
                case DBYTES:
                    results.put(item.getName(),new String(item.getDynamicBytesValue().getValue()));
                    break;
                case STRING:
                    results.put(item.getName(),item.getStringValue().getValue());
                    break;
                case BYTES:
                    results.put(item.getName(), item.getBytesValue().getValue());
                    break;
                case ADDRESS:
                    results.put(item.getName(), item.getAddressValue().toString());
                    break;
                default:
                    throw new RuntimeException("参数类型错误");
            }
        }
        return results;
    }
}
