package com.eypg.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.eypg.pojo.Product;
import com.eypg.pojo.ProductCart;
import com.eypg.pojo.Spellbuyproduct;
import com.eypg.service.LatestlotteryService;
import com.eypg.service.ProductService;
import com.eypg.service.SpellbuyproductService;
import com.eypg.service.SpellbuyrecordService;
import com.eypg.util.NewLotteryUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContext*.xml"})
@Repository
@Service("updateLatestlottery")
public class UpdateLatestlottery {

    @Autowired
    private SpellbuyrecordService spellbuyrecordService;
    @Autowired
    private SpellbuyproductService spellbuyproductService;
    @Autowired
    private LatestlotteryService latestlotteryService;
    @Autowired
    private ProductService productService;
    @Autowired
    private NewLotteryUtil newLotteryUtil;

    private ProductCart productCart;
    private Product product;


    @Test
    public void go() throws InterruptedException {
        List<Spellbuyproduct> spellbuyproductList = spellbuyproductService.UpdateLatestlotteryGetList();
        for (Spellbuyproduct spellbuyproduct : spellbuyproductList) {
            productCart = new ProductCart();
            List<Object[]> proList = spellbuyproductService.findByProductId(spellbuyproduct.getSpellbuyProductId());
            product = (Product) proList.get(0)[0];
            spellbuyproduct = (Spellbuyproduct) proList.get(0)[1];
            productCart.setHeadImage(product.getHeadImage());
            productCart.setProductId(spellbuyproduct.getSpellbuyProductId());
            productCart.setProductName(product.getProductName());
            productCart.setProductPrice(product.getProductPrice());
            productCart.setProductTitle(product.getProductTitle());
            productCart.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
            productCart.setProductPeriod(spellbuyproduct.getProductPeriod());

            newLotteryUtil.lottery(productCart);
        }

    }
}
