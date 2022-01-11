package com.eypg.action;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eypg.dao.Pagination;
import com.eypg.pojo.Product;
import com.eypg.pojo.ProductJSON;
import com.eypg.pojo.Spellbuyproduct;
import com.eypg.service.ProductService;
import com.eypg.service.ProducttypeService;
import com.eypg.util.ApplicationListenerImpl;
import com.eypg.util.PaginationUtil;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("unused")
@Component("ProductList")
public class ProductList extends ActionSupport {

    private static final long serialVersionUID = -8133635872882545829L;


    @Autowired
    ProducttypeService producttypeService;
    @Autowired
    ProductService productService;

    private List<Product> productList;
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
            Pagination page = productService.ProductListByTypeIdList(typeId, "hot", pageNo, pageSize);
            productList = (List<Product>) page.getList();

            resultCount = page.getResultCount();
            if (typeId != null && !typeId.equals("")) {
                pageString = PaginationUtil.getPaginationHtml(resultCount, pageSize, pageNo, 2, 5, ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/productList/" + id + "/" + typeId + "/p_");
            } else {
                pageString = PaginationUtil.getPaginationHtml(resultCount, pageSize, pageNo, 2, 5, ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/productList/" + id + "/p_");
            }
        }

        return "index";
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public ProductJSON getProductJSON() {
        return productJSON;
    }

    public void setProductJSON(ProductJSON productJSON) {
        this.productJSON = productJSON;
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

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
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


}
