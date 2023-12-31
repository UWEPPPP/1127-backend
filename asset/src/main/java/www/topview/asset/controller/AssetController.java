package www.topview.asset.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import www.topview.asset.domain.dto.CreateAssetDTO;
import www.topview.asset.domain.vo.AssetDetailsVO;
import www.topview.asset.domain.bo.CreateAssetBO;
import www.topview.asset.domain.vo.AssetVO;
import www.topview.asset.service.AssetService;
import www.topview.result.CommonResult;
import www.topview.util.JwtUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
    public CommonResult<Boolean> create(@Validated @RequestBody CreateAssetBO createAssetBO, HttpServletRequest request){
        CreateAssetDTO createAssetDTO = new CreateAssetDTO();
        createAssetDTO.setUserId(JwtUtil.getUserId(request));
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

    @PostMapping("/group")
    public CommonResult<Boolean> updateGroup(){
        return CommonResult.operateSuccess("修改成功",true);
    }
}
