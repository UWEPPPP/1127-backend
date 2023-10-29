package com.topview.client;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.sdk.v3.BcosSDK;
import org.fisco.bcos.sdk.v3.client.Client;
import org.fisco.bcos.sdk.v3.config.ConfigOption;
import org.fisco.bcos.sdk.v3.config.exceptions.ConfigException;
import org.fisco.bcos.sdk.v3.config.model.ConfigProperty;
import org.fisco.bcos.sdk.v3.crypto.keypair.CryptoKeyPair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.representer.Representer;

import javax.annotation.PostConstruct;
import java.io.InputStream;

/**
 * FiscoBcos配置类
 *
 * @author ashinnotfound
 * @date 2023/01/25
 */
@Data
@Component
@Slf4j
public class FiscoBcos {
    private BcosSDK sdk;

    private Client client;

    private CryptoKeyPair adminKeyPair;

    @Value("${fisco.file-path}")
    private String fiscoConfigPath;

    @PostConstruct
    public void init(){
        try {
            //初始化sdk
            Representer representer = new Representer();
            representer.getPropertyUtils().setSkipMissingProperties(true);
            Yaml yaml = new Yaml(representer);

            InputStream inputStream = this.getClass().getResourceAsStream(fiscoConfigPath);
            ConfigProperty configProperty = yaml.loadAs(inputStream, ConfigProperty.class);

            ConfigOption configOption = new ConfigOption(configProperty);
            sdk = new BcosSDK(configOption);
            client = sdk.getClient("group0");
            adminKeyPair = client.getCryptoSuite().getCryptoKeyPair();
        } catch (ConfigException e) {
            log.error("sdk初始化失败: {}", e.getMessage(), e);
        }
    }
}
