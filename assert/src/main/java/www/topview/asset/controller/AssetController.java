package www.topview.asset.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import www.topview.asset.domain.dto.CreateAssetDTO;
import www.topview.asset.domain.vo.AssetDetailsVO;
import www.topview.asset.domain.bo.CreateAssetBO;
import www.topview.asset.domain.vo.AssetVO;
import www.topview.asset.service.AssetService;
import www.topview.result.CommonResult;

import javax.annotation.Resource;
import java.util.List;

/**
 * 资产controller
 *
 * @author lql
 * @date 2023/10/29
 */
@RestController
@RequestMapping("asset")
public class AssetController {
    @Resource
    private AssetService assetService;

    @PostMapping("/")
    public CommonResult<Boolean> create(@Validated @RequestBody CreateAssetBO createAssetBO){
        CreateAssetDTO createAssetDTO = new CreateAssetDTO();
        // TODO 通过token获取userID
        createAssetDTO.setUserId(1L);
        createAssetDTO.setCreateAssetBO(createAssetBO);
        return CommonResult.operateSuccess("创建资产成功",assetService.createAsset(createAssetDTO));
    }

    @GetMapping("/{id}")
    public CommonResult<AssetDetailsVO> getAssetInfo(@PathVariable Integer id){
        return CommonResult.operateSuccess("查询成功",assetService.getAssetMessage(id));
    }

    @GetMapping("/list")
    public CommonResult<List<AssetVO>> getAssetList(){
        return CommonResult.operateSuccess("查询成功",assetService.getAssetList());
    }
}
