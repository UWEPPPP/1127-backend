package www.topview.asset.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import org.springframework.stereotype.Service;
import www.topview.asset.client.IpfsClient;
import www.topview.asset.domain.bo.CreateAssetBO;
import www.topview.asset.domain.dto.CreateAssetDTO;
import www.topview.asset.domain.po.Asset;
import www.topview.asset.domain.vo.AssetDetailsVO;
import www.topview.asset.domain.vo.AssetVO;
import www.topview.asset.mapper.AssetMapper;
import www.topview.asset.service.AssetService;
import www.topview.constant.ContractName;
import www.topview.dto.ChainServiceDTO;
import www.topview.dto.CompanyDTO;
import www.topview.dto.UserDTO;
import www.topview.feign.ChainClient;
import www.topview.feign.CompanyClient;
import www.topview.feign.UserClient;
import www.topview.result.CommonResult;
import www.topview.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * AssetService实现类
 *
 * @author lql
 * @date 2023/10/29
 */
@Service
public class AssetServiceImpl implements AssetService {
    @Resource
    private AssetMapper assetMapper;

    @Resource
    private IpfsClient ipfsClient;

    @Resource
    private UserClient userClient;

    @Resource
    private ChainClient chainClient;

    @Resource
    private CompanyClient companyClient;

    public Boolean createAsset(CreateAssetDTO createAssetDTO){
        CreateAssetBO createAssetBO = createAssetDTO.getCreateAssetBO();
        // 判断组名
        if(StringUtils.isEmpty(createAssetBO.getGroupName())){
            createAssetBO.setGroupName("Default");
        }
        // 上传文件
        String cid = ipfsClient.upload(createAssetBO.getFile());

        CommonResult<UserDTO> userMessage = userClient.getUserMessage(createAssetDTO.getUserId());
        if(!userMessage.isSuccess()){
            throw new RuntimeException("获取用户信息失败");
        }
        UserDTO userDTO = userMessage.getData();
        // 获取公司信息
        CommonResult<CompanyDTO> companyMessage = companyClient.getCompanyMessage(userDTO.getWeId());
        if(!companyMessage.isSuccess()){
            throw new RuntimeException("获取公司信息失败");
        }
        CompanyDTO companyDTO = companyMessage.getData();

        Digester sha256 = new Digester(DigestAlgorithm.SHA256);
        byte[] key = sha256.digest(companyDTO.getCompanyName());
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, key);
        String encodeCid = aes.encryptHex(cid);

        CommonResult<Object> callResult = chainClient.call(new ChainServiceDTO()
                .setUserId(userDTO.getId())
                .setContractAddress(companyDTO.getContractAddress())
                .setContractName(ContractName.COMPANY)
                .setFunctionName("getAssetCount()")
                .setFunctionParams(Collections.emptyList()));
        if(!callResult.isSuccess()){
            throw new RuntimeException("合约调用失败");
        }
        Integer id = Convert.convert(Integer.class, callResult.getData());

        Asset asset = new Asset()
                .setId(id)
                .setName(createAssetBO.getName())
                .setCreatorName(userDTO.getUsername())
                .setGroupName(createAssetBO.getGroupName())
                .setDomainName("1")
                .setCompanyName(companyDTO.getCompanyName())
                .setDescription(createAssetBO.getDescription());
        if (assetMapper.insert(asset) != 1){
            throw new RuntimeException("插入数据库失败");
        }

        List<Object> args = new ArrayList<Object>();
        args.add(encodeCid);
        args.add(createAssetBO.getName());
        args.add(companyDTO.getCompanyName());

        return chainClient.send(new ChainServiceDTO()
                .setUserId(userDTO.getId())
                .setContractAddress(companyDTO.getContractAddress())
                .setContractName(companyDTO.getCompanyName())
                .setFunctionName("addAsset()")
                .setFunctionParams(args)).isSuccess();
    }

    public AssetDetailsVO getAssetMessage(Integer id) {
        // 先查数据库
        Asset asset = assetMapper.selectById(id);
        // TODO 查合约获取cid
        String encodeCid = "";

        // TODO 对cid进行解密
        Digester sha256 = new Digester(DigestAlgorithm.SHA256);
        byte[] key = sha256.digest("公司名字");
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, key);
        String cid = aes.decryptStr(encodeCid);

        // TODO 设置创建时间和创建人地址
        return new AssetDetailsVO()
                .setId(asset.getId())
                .setOperateTimes(asset.getOperateTimes())
                .setDomainName(asset.getDomainName())
                .setDescription(asset.getDescription())
                .setCreatorName(asset.getCreatorName())
                .setCompanyName(asset.getCompanyName())
                .setName(asset.getName())
                .setGroupName(asset.getGroupName())
                .setUri(ipfsClient.getUri(cid))
                .setCreateTime("")
                .setCreatorAddress("");
    }

    public List<AssetVO> getAssetList() {
        List<Asset> assetList = assetMapper.selectValidList();
        if(assetList.isEmpty()){
            return null;
        }

        return assetList.stream().map(asset -> {
                    return new AssetVO()
                                .setId(asset.getId())
                                .setName(asset.getName())
                                .setCreatorName(asset.getCreatorName())
                                .setDomainName(asset.getDomainName())
                                .setCompanyName(asset.getCompanyName())
                                .setGroupName(asset.getGroupName());
                }).collect(Collectors.toList());
    }

    @Override
    public Boolean updateAssetGroup() {
        return null;
    }
}
