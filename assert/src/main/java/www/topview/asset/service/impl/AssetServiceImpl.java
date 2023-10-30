package www.topview.asset.service.impl;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import org.springframework.stereotype.Service;
import www.topview.asset.client.IpfsClient;
import www.topview.asset.domain.dto.CreateAssetDTO;
import www.topview.asset.domain.po.Asset;
import www.topview.asset.domain.request.CreateAssetRequest;
import www.topview.asset.mapper.AssetMapper;
import www.topview.asset.service.AssetService;
import www.topview.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
        CreateAssetRequest createAssetRequest = createAssetDTO.getCreateAssetRequest();
        // 判断组名
        if(StringUtils.isEmpty(createAssetRequest.getGroupName())){
            createAssetRequest.setGroupName("Default");
        }
        // 上传文件
        String cid = ipfsClient.upload(createAssetRequest.getFile());

        // TODO 远程调用接口 => 查询用户信息以及对应公司信息

        // TODO 使用公司名字加密cid
        Digester sha256 = new Digester(DigestAlgorithm.SHA256);
        byte[] key = sha256.digest("公司名字");
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, key);
        String encodeCid = aes.encryptHex(cid);

        // TODO 调用合约获取assetCount
        Long count = 1L;

        // TODO 设置asset信息 => 插入数据库
        Asset asset = new Asset()
                .setId(count + 1L)
                .setName(createAssetRequest.getName())
                .setCreatorName("")
                .setGroupName(createAssetRequest.getGroupName())
                .setDomainName("")
                .setCompanyName("")
                .setDescription(createAssetRequest.getDescription());
        if (assetMapper.insert(asset) != 1){
            throw new RuntimeException("插入数据库失败");
        }

        // TODO 调用合约
        List<Object> args = new ArrayList<Object>();
        // TODO 调用链端接口

        return true;
    }
}
