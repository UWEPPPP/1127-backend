package www.topview.asset.controller;

import org.springframework.web.bind.annotation.*;
import www.topview.asset.service.AssetService;

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
    public void create(){

    }

    @GetMapping("/{id}")
    public void getAsset(@PathVariable Integer id){

    }
}
