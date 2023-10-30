package www.topview.asset.client;

import cn.hutool.core.date.DateUtil;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;
import www.topview.util.StringUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * ipfs客户端
 *
 * @author lql
 * @date 2023/10/30
 */
@RefreshScope
@Configuration
public class IpfsClient {
    @NacosValue("${ipfs.server-addr")
    private String serverAddr;

    @NacosValue("${ipfs.gateway")
    private String gateway;

    private static IPFS ipfsClient;

    @PostConstruct
    public void init(){
        ipfsClient = new IPFS(serverAddr);
    }

    /**
     * 上传文件到ipfs
     *
     * @param file 文件
     * @return 返回cid
     * @throws IOException 文件传输异常
     */
    public String upload(MultipartFile file) throws IOException {
        if(file.isEmpty()){
            throw new RuntimeException("文件为空");
        }
        String fileName = file.getOriginalFilename();
        if(StringUtils.isEmpty(fileName)){
            throw new RuntimeException("文件名为空");
        }
        // 添加标识
        int index = fileName.lastIndexOf('.');
        File realFile = addIdentification(file, fileName.substring(0, index), fileName.substring(index));

        return upload(realFile);
    }

    /**
     * 获取http访问地址
     * @param cid 文件cid
     * @return 返回uri
     */
    public String getUri(String cid){
        return gateway + "/ipfs/" + cid;
    }

    /**
     * 添加唯一标识
     *
     * @param prefix 文件名缀
     * @param suffix 文件后缀
     * @param file 原文件
     * @return 文件
     * @throws IOException 文件传输异常
     */
    private File addIdentification(MultipartFile file,String prefix,String suffix) throws IOException {
        File f = File.createTempFile(prefix + System.currentTimeMillis(),suffix);
        file.transferTo(f);
        return f;
    }

    /**
     * 上传文件
     *
     * @param file 文件
     * @return cid
     * @throws IOException 文件传输异常
     */
    private String upload(File file) throws IOException {
        NamedStreamable.FileWrapper fileBytes = new NamedStreamable.FileWrapper(file);
        MerkleNode addResult = ipfsClient.add(fileBytes).get(0);
        return addResult.hash.toString();
    }
}
