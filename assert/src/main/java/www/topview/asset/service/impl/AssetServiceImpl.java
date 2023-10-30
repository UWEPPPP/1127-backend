package www.topview.asset.service.impl;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import www.topview.asset.client.IpfsClient;
import www.topview.asset.domain.dto.CreateAssetDTO;
import www.topview.asset.domain.po.Asset;
import www.topview.asset.domain.bo.CreateAssetBO;
import www.topview.asset.domain.vo.AssetDetailsVO;
import www.topview.asset.domain.vo.AssetVO;
import www.topview.asset.mapper.AssetMapper;
import www.topview.asset.service.AssetService;
import www.topview.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    public Boolean createAsset(CreateAssetDTO createAssetDTO){
        CreateAssetBO createAssetBO = createAssetDTO.getCreateAssetBO();
        // 判断组名
        if(StringUtils.isEmpty(createAssetBO.getGroupName())){
            createAssetBO.setGroupName("Default");
        }
        // 上传文件
        String cid = ipfsClient.upload(createAssetBO.getFile());

        // TODO 远程调用接口 => 查询用户信息以及对应公司信息

        // TODO 使用公司私钥加密cid
        Digester sha256 = new Digester(DigestAlgorithm.SHA256);
        byte[] key = sha256.digest("公司名字");
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, key);
        String encodeCid = aes.encryptHex(cid);

        // TODO 调用合约获取assetCount
        Long count = 1L;

        // TODO 设置asset信息 => 插入数据库
        Asset asset = new Asset()
                .setId(count + 1L)
                .setName(createAssetBO.getName())
                .setCreatorName("")
                .setGroupName(createAssetBO.getGroupName())
                .setDomainName("")
                .setCompanyName("")
                .setDescription(createAssetBO.getDescription());
        if (assetMapper.insert(asset) != 1){
            throw new RuntimeException("插入数据库失败");
        }

        // TODO 调用合约
        List<Object> args = new ArrayList<Object>();
        // TODO 调用链端接口

        return true;
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
}
