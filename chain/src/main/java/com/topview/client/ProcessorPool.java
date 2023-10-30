package com.topview.client;

import org.fisco.bcos.sdk.v3.crypto.CryptoSuite;
import org.fisco.bcos.sdk.v3.model.CryptoType;
import org.fisco.bcos.sdk.v3.transaction.manager.AssembleTransactionProcessor;
import org.fisco.bcos.sdk.v3.transaction.manager.TransactionProcessorFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 处理器池
 *
 * @author ashinnotfound
 * @date 2023/10/30
 */
@Component
public class ProcessorPool {

    @Resource
    private FiscoBcos fiscoBcos;

    @Value("${fisco.processor-num-max}")
    private Integer processorNumMax;

    @Value("${fisco.eliminate-num}")
    private Integer eliminateNum;

    // TODO byd怎么必须绝对路径啊？
    @Value("${fisco.abi-file-path}")
    private String abiFilePath;

    private AssembleTransactionProcessor adminProcessor;

    private ProcessorCache<Integer, AssembleTransactionProcessor> processorCache;

    @PostConstruct
    public void init () throws IOException {
        processorCache = new ProcessorCache<>(processorNumMax);
        processorCache.put(0, TransactionProcessorFactory.createAssembleTransactionProcessor(fiscoBcos.getClient(), fiscoBcos.getAdminKeyPair(), abiFilePath, ""));
    }

    /**
     * 创建处理器
     *
     * @param userId     用户 ID
     * @param privateKey 私钥
     * @throws IOException ioexception
     */
    public void createProcessor(int userId, String privateKey) throws IOException {
        AssembleTransactionProcessor assembleTransactionProcessor = processorCache.get(userId);
        if (assembleTransactionProcessor == null) {
            assembleTransactionProcessor = TransactionProcessorFactory.createAssembleTransactionProcessor(fiscoBcos.getClient(), new CryptoSuite(CryptoType.ECDSA_TYPE).getKeyPairFactory().createKeyPair(privateKey), abiFilePath, "");

            if (processorCache.size() >= processorNumMax) {
                // LRU淘汰processor
                int count = 0;
                for (Iterator<Integer> iterator = processorCache.keySet().iterator(); iterator.hasNext() && count < eliminateNum; count++) {
                    iterator.next();
                    iterator.remove();
                }
            }

            processorCache.put(userId, assembleTransactionProcessor);
        }
    }

    /**
     * 获取处理器
     *
     * @param userId     用户 ID
     * @return {@code AssembleTransactionProcessor}
     */
    public AssembleTransactionProcessor getProcessor(int userId){
        return userId == 0 ? getAdminProcessor() : processorCache.get(userId);
    }

    /**
     * 获取admin处理器
     *
     * @return {@code AssembleTransactionProcessor}
     */
    private AssembleTransactionProcessor getAdminProcessor() {
        return adminProcessor;
    }

    private static class ProcessorCache<K, V> extends LinkedHashMap<K, V> {
        private final int maxSize;

        public ProcessorCache(int maxSize) {
            super(maxSize + 1, 1.1f, true);
            this.maxSize = maxSize;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            return size() > maxSize;
        }
    }
}
