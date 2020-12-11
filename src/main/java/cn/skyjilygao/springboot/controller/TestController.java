package cn.skyjilygao.springboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController()
@RequestMapping("test")
public class TestController extends BaseController {

    @GetMapping("get")
    public ReturnT ok(){
        return success("sdf");
    }

    @GetMapping("err")
    public ReturnT err(){
        try {
            int a = 2;
            int b = 0;
            return success(a/b);
        }catch (Exception e){
            return error(ReturnTEnum.ADD_DATA_ERROR, e);
//            throw new PwException(ReturnTEnum.ADD_DATA_ERROR, e);
        }

    }
}
