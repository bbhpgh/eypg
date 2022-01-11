package com.eypg.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.eypg.dao.Pagination;
import com.eypg.pojo.Product;
import com.eypg.pojo.ProductJSON;
import com.eypg.pojo.Spellbuyproduct;
import com.eypg.service.SpellbuyrecordService;
import com.eypg.util.ApplicationListenerImpl;
import com.eypg.util.PaginationUtil;
import com.eypg.util.Struts2Utils;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("unused")
@Component("SearchAction")
public class SearchAction extends ActionSupport {

    private static final long serialVersionUID = -6415908765367001524L;

    @Autowired
    @Qualifier("spellbuyrecordService")
    private SpellbuyrecordService spellbuyrecordService;

    private List<ProductJSON> ProductList;
    private Product product;
    private Spellbuyproduct spellbuyproduct;
    private ProductJSON productJSON;
    private String id;
    private int pageNo;
    private String pages;
    private String pageString;
    private int pageSize = 20;
    private int pageCount;
    private int resultCount;
    private String keyword;

    public String index() {
        if (pageNo == 0) {
            pageNo = 1;
        }
        if (pages != null) {
            pageNo = Integer.parseInt(pages.split("_")[1]);
        }
        if (id.equals("hot20")) {
            Pagination hotPage = spellbuyrecordService.searchProduct(keyword, "hot", pageNo, pageSize);
            List<Object[]> HotList = (List<Object[]>) hotPage.getList();
            ProductList = new ArrayList<ProductJSON>();
            for (int i = 0; i < HotList.size(); i++) {
                productJSON = new ProductJSON();
                product = (Product) HotList.get(i)[0];
                spellbuyproduct = (Spellbuyproduct) HotList.get(i)[1];
                productJSON.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
                productJSON.setHeadImage(product.getHeadImage());
                productJSON.setProductId(spellbuyproduct.getSpellbuyProductId());
                productJSON.setProductName(product.getProductName());
                productJSON.setProductPrice(spellbuyproduct.getSpellbuyPrice());
                productJSON.setProductTitle(product.getProductTitle());
                productJSON.setProductStyle(product.getStyle());
                ProductList.add(productJSON);
            }
            resultCount = hotPage.getResultCount();
            pageString = PaginationUtil.getPaginationHtml(resultCount, pageSize, pageNo, 2, 5, ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/search/" + id + "/" + keyword + "/p_");
        } else if (id.equals("date20")) {
            Pagination datePage = spellbuyrecordService.searchProduct(keyword, "date", pageNo, pageSize);
            List<Object[]> dateList = (List<Object[]>) datePage.getList();
            ProductList = new ArrayList<ProductJSON>();
            for (int i = 0; i < dateList.size(); i++) {
                productJSON = new ProductJSON();
                product = (Product) dateList.get(i)[0];
                spellbuyproduct = (Spellbuyproduct) dateList.get(i)[1];
                productJSON.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
                productJSON.setHeadImage(product.getHeadImage());
                productJSON.setProductId(spellbuyproduct.getSpellbuyProductId());
                productJSON.setProductName(product.getProductName());
                productJSON.setProductPrice(spellbuyproduct.getSpellbuyPrice());
                productJSON.setProductTitle(product.getProductTitle());
                productJSON.setProductStyle(product.getStyle());
                ProductList.add(productJSON);
            }
            resultCount = datePage.getResultCount();
            pageString = PaginationUtil.getPaginationHtml(resultCount, pageSize, pageNo, 2, 5, ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/search/" + id + "/" + keyword + "/p_");
        } else if (id.equals("price20")) {
            Pagination pricePage = spellbuyrecordService.searchProduct(keyword, "price", pageNo, pageSize);
            List<Object[]> priceList = (List<Object[]>) pricePage.getList();
            ProductList = new ArrayList<ProductJSON>();
            for (int i = 0; i < priceList.size(); i++) {
                productJSON = new ProductJSON();
                product = (Product) priceList.get(i)[0];
                spellbuyproduct = (Spellbuyproduct) priceList.get(i)[1];
                productJSON.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
                productJSON.setHeadImage(product.getHeadImage());
                productJSON.setProductId(spellbuyproduct.getSpellbuyProductId());
                productJSON.setProductName(product.getProductName());
                productJSON.setProductPrice(spellbuyproduct.getSpellbuyPrice());
                productJSON.setProductTitle(product.getProductTitle());
                productJSON.setProductStyle(product.getStyle());
                ProductList.add(productJSON);
            }
            resultCount = pricePage.getResultCount();
            pageString = PaginationUtil.getPaginationHtml(resultCount, pageSize, pageNo, 2, 5, ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/search/" + id + "/" + keyword + "/p_");
        } else if (id.equals("priceAsc20")) {
            Pagination pricePage = spellbuyrecordService.searchProduct(keyword, "priceAsc", pageNo, pageSize);
            List<Object[]> priceList = (List<Object[]>) pricePage.getList();
            ProductList = new ArrayList<ProductJSON>();
            for (int i = 0; i < priceList.size(); i++) {
                productJSON = new ProductJSON();
                product = (Product) priceList.get(i)[0];
                spellbuyproduct = (Spellbuyproduct) priceList.get(i)[1];
                productJSON.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
                productJSON.setHeadImage(product.getHeadImage());
                productJSON.setProductId(spellbuyproduct.getSpellbuyProductId());
                productJSON.setProductName(product.getProductName());
                productJSON.setProductPrice(spellbuyproduct.getSpellbuyPrice());
                productJSON.setProductTitle(product.getProductTitle());
                productJSON.setProductStyle(product.getStyle());
                ProductList.add(productJSON);
            }
            resultCount = pricePage.getResultCount();
            pageString = PaginationUtil.getPaginationHtml(resultCount, pageSize, pageNo, 2, 5, ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/search/" + id + "/" + keyword + "/p_");
        } else if (id.equals("about20")) {
            Pagination pricePage = spellbuyrecordService.searchProduct(keyword, "about", pageNo, pageSize);
            List<Object[]> priceList = (List<Object[]>) pricePage.getList();
            ProductList = new ArrayList<ProductJSON>();
            for (int i = 0; i < priceList.size(); i++) {
                productJSON = new ProductJSON();
                product = (Product) priceList.get(i)[0];
                spellbuyproduct = (Spellbuyproduct) priceList.get(i)[1];
                productJSON.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
                productJSON.setHeadImage(product.getHeadImage());
                productJSON.setProductId(spellbuyproduct.getSpellbuyProductId());
                productJSON.setProductName(product.getProductName());
                productJSON.setProductPrice(spellbuyproduct.getSpellbuyPrice());
                productJSON.setProductTitle(product.getProductTitle());
                productJSON.setProductStyle(product.getStyle());
                ProductList.add(productJSON);
            }
            resultCount = pricePage.getResultCount();
            pageString = PaginationUtil.getPaginationHtml(resultCount, pageSize, pageNo, 2, 5, ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/search/" + id + "/" + keyword + "/p_");
        } else if (id.equals("surplus20")) {
            Pagination pricePage = spellbuyrecordService.searchProduct(keyword, "surplus", pageNo, pageSize);
            List<Object[]> priceList = (List<Object[]>) pricePage.getList();
            ProductList = new ArrayList<ProductJSON>();
            for (int i = 0; i < priceList.size(); i++) {
                productJSON = new ProductJSON();
                product = (Product) priceList.get(i)[0];
                spellbuyproduct = (Spellbuyproduct) priceList.get(i)[1];
                productJSON.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
                productJSON.setHeadImage(product.getHeadImage());
                productJSON.setProductId(spellbuyproduct.getSpellbuyProductId());
                productJSON.setProductName(product.getProductName());
                productJSON.setProductPrice(spellbuyproduct.getSpellbuyPrice());
                productJSON.setProductTitle(product.getProductTitle());
                productJSON.setProductStyle(product.getStyle());
                ProductList.add(productJSON);
            }
            resultCount = pricePage.getResultCount();
            pageString = PaginationUtil.getPaginationHtml(resultCount, pageSize, pageNo, 2, 5, ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/search/" + id + "/" + keyword + "/p_");
        }
        return "index";
    }

    public String ajaxPage() {
        if (pageNo == 0) {
            pageNo = 1;
        } else {
            pageNo += 1;
        }
        if (id.equals("hot20")) {
            Pagination hotPage = spellbuyrecordService.searchProduct(keyword, "hot", pageNo, pageSize);
            List<Object[]> HotList = (List<Object[]>) hotPage.getList();
            ProductList = new ArrayList<ProductJSON>();
            for (int i = 0; i < HotList.size(); i++) {
                productJSON = new ProductJSON();
                product = (Product) HotList.get(i)[0];
                spellbuyproduct = (Spellbuyproduct) HotList.get(i)[1];
                productJSON.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
                productJSON.setHeadImage(product.getHeadImage());
                productJSON.setProductId(spellbuyproduct.getSpellbuyProductId());
                productJSON.setProductName(product.getProductName());
                productJSON.setProductPrice(spellbuyproduct.getSpellbuyPrice());
                productJSON.setProductTitle(product.getProductTitle());
                productJSON.setProductStyle(product.getStyle());
                ProductList.add(productJSON);
            }
            Struts2Utils.renderJson(ProductList);

        } else if (id.equals("date20")) {
            Pagination datePage = spellbuyrecordService.searchProduct(keyword, "date", pageNo, pageSize);
            List<Object[]> dateList = (List<Object[]>) datePage.getList();
            ProductList = new ArrayList<ProductJSON>();
            for (int i = 0; i < dateList.size(); i++) {
                productJSON = new ProductJSON();
                product = (Product) dateList.get(i)[0];
                spellbuyproduct = (Spellbuyproduct) dateList.get(i)[1];
                productJSON.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
                productJSON.setHeadImage(product.getHeadImage());
                productJSON.setProductId(spellbuyproduct.getSpellbuyProductId());
                productJSON.setProductName(product.getProductName());
                productJSON.setProductPrice(spellbuyproduct.getSpellbuyPrice());
                productJSON.setProductTitle(product.getProductTitle());
                productJSON.setProductStyle(product.getStyle());
                ProductList.add(productJSON);
            }
            Struts2Utils.renderJson(ProductList);
        } else if (id.equals("price20")) {
            Pagination pricePage = spellbuyrecordService.searchProduct(keyword, "price", pageNo, pageSize);
            List<Object[]> priceList = (List<Object[]>) pricePage.getList();
            ProductList = new ArrayList<ProductJSON>();
            for (int i = 0; i < priceList.size(); i++) {
                productJSON = new ProductJSON();
                product = (Product) priceList.get(i)[0];
                spellbuyproduct = (Spellbuyproduct) priceList.get(i)[1];
                productJSON.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
                productJSON.setHeadImage(product.getHeadImage());
                productJSON.setProductId(spellbuyproduct.getSpellbuyProductId());
                productJSON.setProductName(product.getProductName());
                productJSON.setProductPrice(spellbuyproduct.getSpellbuyPrice());
                productJSON.setProductTitle(product.getProductTitle());
                productJSON.setProductStyle(product.getStyle());
                ProductList.add(productJSON);
            }
            Struts2Utils.renderJson(ProductList);
        } else if (id.equals("priceAsc20")) {
            Pagination pricePage = spellbuyrecordService.searchProduct(keyword, "priceAsc", pageNo, pageSize);
            List<Object[]> priceList = (List<Object[]>) pricePage.getList();
            ProductList = new ArrayList<ProductJSON>();
            for (int i = 0; i < priceList.size(); i++) {
                productJSON = new ProductJSON();
                product = (Product) priceList.get(i)[0];
                spellbuyproduct = (Spellbuyproduct) priceList.get(i)[1];
                productJSON.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
                productJSON.setHeadImage(product.getHeadImage());
                productJSON.setProductId(spellbuyproduct.getSpellbuyProductId());
                productJSON.setProductName(product.getProductName());
                productJSON.setProductPrice(spellbuyproduct.getSpellbuyPrice());
                productJSON.setProductTitle(product.getProductTitle());
                productJSON.setProductStyle(product.getStyle());
                ProductList.add(productJSON);
            }
            Struts2Utils.renderJson(ProductList);
        }
        return null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ProductJSON> getProductList() {
        return ProductList;
    }

    public void setProductList(List<ProductJSON> productList) {
        ProductList = productList;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Spellbuyproduct getSpellbuyproduct() {
        return spellbuyproduct;
    }

    public void setSpellbuyproduct(Spellbuyproduct spellbuyproduct) {
        this.spellbuyproduct = spellbuyproduct;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getResultCount() {
        return resultCount;
    }

    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public ProductJSON getProductJSON() {
        return productJSON;
    }

    public void setProductJSON(ProductJSON productJSON) {
        this.productJSON = productJSON;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getPageString() {
        return pageString;
    }

    public void setPageString(String pageString) {
        this.pageString = pageString;
    }


}
