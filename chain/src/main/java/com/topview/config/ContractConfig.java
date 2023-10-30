package com.topview.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import www.topview.constant.ContractName;

@Configuration
@ConfigurationProperties(prefix = "fisco.contract.address")
@Data
public class ContractConfig {
    @Value("company")
    private String company;
    @Value("domain")
    private String domain;

    public String contractName2Address(String contractName) {
        if (ContractName.COMPANY.equals(contractName)) {
            return company;
        }else if (ContractName.DOMAIN.equals(contractName)) {
            return domain;
        } else {
            throw new RuntimeException("错误的合约名称");
        }
    }
}
