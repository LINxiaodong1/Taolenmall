package lin;


import com.lin.gulimall.product.entity.BrandEntity;
import com.lin.gulimall.product.productApplication;
import com.lin.gulimall.product.service.BrandService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = productApplication.class )
public class applicationTest {
    @Autowired
    private BrandService brandService;
    @Test
    public void brandTest(){
       BrandEntity brandEntity=new BrandEntity();
        brandEntity.setName("小米");
       brandService.save(brandEntity);
       BrandEntity brand=brandService.getById(1);
        System.out.println(brand);





    }
}
