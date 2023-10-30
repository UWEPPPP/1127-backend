package www.topview.asset.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import www.topview.asset.domain.dto.CreateAssetDTO;
import www.topview.asset.domain.vo.AssetDetailsVO;
import www.topview.asset.domain.request.CreateAssetRequest;
import www.topview.asset.service.AssetService;
import www.topview.result.CommonResult;

import javax.annotation.Resource;

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
    public CommonResult<Boolean> create(@Validated @RequestBody CreateAssetRequest createAssetRequest){
        CreateAssetDTO createAssetDTO = new CreateAssetDTO();
        // TODO 通过token获取userID
        createAssetDTO.setUserId(1L);
        createAssetDTO.setCreateAssetRequest(createAssetRequest);
        return CommonResult.operateSuccess("创建资产成功",assetService.createAsset(createAssetDTO));
    }

    @GetMapping("/{id}")
    public CommonResult<AssetDetailsVO> getAsset(@PathVariable Integer id){
        return null;
    }
}
