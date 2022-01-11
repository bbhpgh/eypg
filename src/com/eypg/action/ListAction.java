package com.eypg.action;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.eypg.dao.Pagination;
import com.eypg.pojo.Product;
import com.eypg.pojo.ProductInfo;
import com.eypg.pojo.ProductJSON;
import com.eypg.pojo.Producttype;
import com.eypg.pojo.Spellbuyproduct;
import com.eypg.pojo.Spellbuyrecord;
import com.eypg.pojo.User;
import com.eypg.service.ProducttypeService;
import com.eypg.service.SpellbuyproductService;
import com.eypg.service.SpellbuyrecordService;
import com.eypg.util.ApplicationListenerImpl;
import com.eypg.util.PaginationUtil;
import com.eypg.util.Struts2Utils;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("unused")
@Component("ListAction")
public class ListAction extends ActionSupport {

    private static final long serialVersionUID = 8452833122481904678L;

    @Autowired
    @Qualifier("spellbuyrecordService")
    private SpellbuyrecordService spellbuyrecordService;
    @Autowired
    @Qualifier("spellbuyproductService")
    private SpellbuyproductService spellbuyproductService;
    @Autowired
    ProducttypeService producttypeService;

    private List<ProductJSON> ProductList;
    private ProductJSON productJSON;
    private Product product;
    private Spellbuyproduct spellbuyproduct;
    private String id;
    private String typeId;
    private String typeName;
    private int pageNo;
    private String pages;
    private String pageString;
    private int pageSize = 20;
    private int pageCount;
    private int resultCount;

//	public static ResourceBundle url = ResourceBundle.getBundle("config");

    public String index() {
        if (pageNo == 0) {
            pageNo = 1;
        }
        if (pages != null) {
            pageNo = Integer.parseInt(pages.split("_")[1]);
        }
        if (typeId != null && !typeId.equals("")) {
            typeName = producttypeService.findById(typeId).getTypeName();
        } else {
            typeName = producttypeService.findById("1000").getTypeName();
        }
        if (id.equals("hot20")) {
            Pagination hotPage = spellbuyrecordService.ProductByTypeIdList(typeId, "hot", pageNo, pageSize);
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
            if (typeId != null && !typeId.equals("")) {
                pageString = PaginationUtil.getPaginationHtml(resultCount, pageSize, pageNo, 2, 5, ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/list/" + id + "/" + typeId + "/p_");
            } else {
                pageString = PaginationUtil.getPaginationHtml(resultCount, pageSize, pageNo, 2, 5, ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/list/" + id + "/p_");
            }
        } else if (id.equals("date20")) {
            Pagination datePage = spellbuyrecordService.ProductByTypeIdList(typeId, "date", pageNo, pageSize);
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
            if (typeId != null && !typeId.equals("")) {
                pageString = PaginationUtil.getPaginationHtml(resultCount, pageSize, pageNo, 2, 5, ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/list/" + id + "/" + typeId + "/p_");
            } else {
                pageString = PaginationUtil.getPaginationHtml(resultCount, pageSize, pageNo, 2, 5, ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/list/" + id + "/p_");
            }
        } else if (id.equals("price20")) {
            Pagination pricePage = spellbuyrecordService.ProductByTypeIdList(typeId, "price", pageNo, pageSize);
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
            if (typeId != null && !typeId.equals("")) {
                pageString = PaginationUtil.getPaginationHtml(resultCount, pageSize, pageNo, 2, 5, ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/list/" + id + "/" + typeId + "/p_");
            } else {
                pageString = PaginationUtil.getPaginationHtml(resultCount, pageSize, pageNo, 2, 5, ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/list/" + id + "/p_");
            }
        } else if (id.equals("priceAsc20")) {
            Pagination pricePage = spellbuyrecordService.ProductByTypeIdList(typeId, "priceAsc", pageNo, pageSize);
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
            if (typeId != null && !typeId.equals("")) {
                pageString = PaginationUtil.getPaginationHtml(resultCount, pageSize, pageNo, 2, 5, ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/list/" + id + "/" + typeId + "/p_");
            } else {
                pageString = PaginationUtil.getPaginationHtml(resultCount, pageSize, pageNo, 2, 5, ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/list/" + id + "/p_");
            }
        } else if (id.equals("about20")) {
            Pagination pricePage = spellbuyrecordService.ProductByTypeIdList(typeId, "about", pageNo, pageSize);
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
            if (typeId != null && !typeId.equals("")) {
                pageString = PaginationUtil.getPaginationHtml(resultCount, pageSize, pageNo, 2, 5, ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/list/" + id + "/" + typeId + "/p_");
            } else {
                pageString = PaginationUtil.getPaginationHtml(resultCount, pageSize, pageNo, 2, 5, ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/list/" + id + "/p_");
            }
        } else if (id.equals("surplus20")) {
            Pagination pricePage = spellbuyrecordService.ProductByTypeIdList(typeId, "surplus", pageNo, pageSize);
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
            if (typeId != null && !typeId.equals("")) {
                pageString = PaginationUtil.getPaginationHtml(resultCount, pageSize, pageNo, 2, 5, ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/list/" + id + "/" + typeId + "/p_");
            } else {
                pageString = PaginationUtil.getPaginationHtml(resultCount, pageSize, pageNo, 2, 5, ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/list/" + id + "/p_");
            }
        }
        return "index";
    }

    /**
     * 查询商品是否满员
     */
    public void isStatus() {
        spellbuyproduct = spellbuyproductService.findById(id);
        if (spellbuyproduct.getSpStatus() == 1) {
            Struts2Utils.renderText("false");
        } else {
            if (spellbuyproduct.getSpellbuyPrice() - spellbuyproduct.getSpellbuyCount() == 0) {
                Struts2Utils.renderText("false");
            } else {
                Struts2Utils.renderText(String.valueOf(spellbuyproduct.getSpellbuyPrice() - spellbuyproduct.getSpellbuyCount()));
            }
        }
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<ProductJSON> getProductList() {
        return ProductList;
    }

    public void setProductList(List<ProductJSON> productList) {
        ProductList = productList;
    }

    public ProductJSON getProductJSON() {
        return productJSON;
    }

    public void setProductJSON(ProductJSON productJSON) {
        this.productJSON = productJSON;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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
