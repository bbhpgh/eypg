package com.eypg.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eypg.dao.Pagination;
import com.eypg.pojo.Latestlottery;
import com.eypg.pojo.Product;
import com.eypg.pojo.ProductJSON;
import com.eypg.pojo.ShareCommentJSON;
import com.eypg.pojo.ShareInfoPro;
import com.eypg.pojo.ShareJSON;
import com.eypg.pojo.Sharecomments;
import com.eypg.pojo.Shareimage;
import com.eypg.pojo.Shareinfo;
import com.eypg.pojo.Spellbuyproduct;
import com.eypg.pojo.User;
import com.eypg.service.LatestlotteryService;
import com.eypg.service.ShareService;
import com.eypg.service.SpellbuyproductService;
import com.eypg.service.UserService;
import com.eypg.util.DateUtil;
import com.eypg.util.HTMLFilter;
import com.eypg.util.Struts2Utils;
import com.eypg.util.UserNameUtil;
import com.opensymphony.xwork2.ActionSupport;

@Component("ShareShowAction")
public class ShareShowAction extends ActionSupport {
    private static final long serialVersionUID = -5418862771252833639L;

    @Autowired
    private ShareService shareService;
    @Autowired
    private LatestlotteryService latestlotteryService;
    @Autowired
    private SpellbuyproductService spellbuyproductService;
    @Autowired
    private UserService userService;

    private ShareInfoPro shareInfoPro;
    private Sharecomments sharecomments;
    private ShareCommentJSON shareCommentJSON;
    private List<ShareCommentJSON> shareCommentJSONList;
    private Shareinfo shareinfo;
    private User user;
    private List<Shareimage> shareimageList;
    private Latestlottery latestlottery;
    private List<Latestlottery> latestlotteryList;
    private Product product;
    private Spellbuyproduct spellbuyproduct;
    private ProductJSON productJSON;
    private List<ShareJSON> ShareJSONList;
    private ShareJSON shareJSON;
    private Shareimage shareimage;

    private String id;
    private int pageNo;
    private int pageSize = 5;
    private int pageCount;
    private int resultCount;
    private String productId;
    private String shareId;
    private String userId;
    private String shareCommentId;
    private String commentText;
    private String reCommentId;

    HttpServletRequest request = null;
    static HTMLFilter htmlFilter = new HTMLFilter();

    public String index() {
        request = Struts2Utils.getRequest();
        Cookie[] cookies = request.getCookies();
        if (request.isRequestedSessionIdFromCookie()) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookie.getName().equals("userId")) {
                    userId = cookie.getValue();
                    if (userId != null && !userId.equals("")) {
                        user = userService.findById(userId);
                    }
                }
            }
        }
        shareInfoPro = new ShareInfoPro();
        shareimageList = new ArrayList<Shareimage>();
        List<Object[]> objectList = shareService.shareShow(Integer.parseInt(id));
        shareinfo = (Shareinfo) objectList.get(0)[0];
        latestlottery = (Latestlottery) objectList.get(0)[1];
        shareInfoPro.setAnnouncedTime(latestlottery.getAnnouncedTime());
        shareInfoPro.setBuyNumberCount(String.valueOf(latestlottery.getBuyNumberCount()));
        shareInfoPro.setProductId(String.valueOf(latestlottery.getProductId()));
        shareInfoPro.setProductImg(latestlottery.getProductImg());
        shareInfoPro.setProductName(latestlottery.getProductName());
        shareInfoPro.setProductPeriod(String.valueOf(latestlottery.getProductPeriod()));
        shareInfoPro.setProductPrice(String.valueOf(latestlottery.getProductPrice()));
        shareInfoPro.setProductTitle(latestlottery.getProductTitle());
        shareInfoPro.setReplyCount(shareinfo.getReplyCount());
        shareInfoPro.setReward(shareinfo.getReward());
        shareInfoPro.setShareContent(shareinfo.getShareContent());
        shareInfoPro.setShareDate(shareinfo.getShareDate());
        shareInfoPro.setShareId(String.valueOf(shareinfo.getUid()));
        shareInfoPro.setUserId(String.valueOf(shareinfo.getUserId()));
        shareimageList = shareService.getShareimage(String.valueOf(shareinfo.getUid()));
//		String shareImgList = "";
//		for (Shareimage shareimage : shareimageList) {
//			shareImgList += shareimage.getImages().substring(shareimage.getImages().lastIndexOf("/")+1,shareimage.getImages().length())+",";
//		}
//		shareImgList = shareImgList.substring(0, shareImgList.length()-1);
//		shareInfoPro.setShareimageList(shareImgList);
        shareInfoPro.setShareTitle(shareinfo.getShareTitle());
        shareInfoPro.setSpellbuyProductId(String.valueOf(latestlottery.getSpellbuyProductId()));
        shareInfoPro.setUpCount(shareinfo.getUpCount());
        shareInfoPro.setWinRandomNumber(String.valueOf(latestlottery.getRandomNumber()));
        shareInfoPro.setWinUserFace(latestlottery.getUserFace());
        shareInfoPro.setWinUserName(latestlottery.getUserName());

        Pagination datePage = shareService.shareByComment(id, pageNo, pageSize);
        resultCount = datePage.getResultCount();

        /**
         * 最新晒单
         */
        Pagination page = shareService.loadPageShare("new20", pageNo, pageSize);
        List<Object[]> pageList = (List<Object[]>) page.getList();
        ShareJSONList = new ArrayList<ShareJSON>();
        for (int i = 0; i < pageList.size(); i++) {
            shareJSON = new ShareJSON();
            shareinfo = (Shareinfo) pageList.get(i)[0];
            shareimage = (Shareimage) pageList.get(i)[1];
            latestlottery = (Latestlottery) pageList.get(i)[2];
            String userName = "";
            if (latestlottery.getUserName() != null && !latestlottery.getUserName().equals("")) {
                userName = latestlottery.getUserName();
            } else if (latestlottery.getBuyUser() != null && !latestlottery.getBuyUser().equals("")) {
                userName = latestlottery.getBuyUser();
                if (userName.indexOf("@") != -1) {
                    String[] u = userName.split("@");
                    String u1 = u[0].substring(0, 2) + "***";
                    userName = u1 + "@" + u[1];
                } else {
                    userName = userName.substring(0, 4) + "*** " + userName.substring(7);
                }
            }
            shareJSON.setAnnouncedTime(latestlottery.getAnnouncedTime().substring(0, 10));
            shareJSON.setReplyCount(shareinfo.getReplyCount());
            shareJSON.setReward(shareinfo.getReward());
            shareJSON.setShareContent(shareinfo.getShareContent());
            shareJSON.setShareDate(DateUtil.getTime(DateUtil.SDateTimeToDate(shareinfo.getShareDate())));
            shareJSON.setShareImages(shareimage.getImages());
            shareJSON.setShareTitle(shareinfo.getShareTitle());
            shareJSON.setUid(shareinfo.getUid());
            shareJSON.setUpCount(shareinfo.getUpCount());
            shareJSON.setUserName(userName);
            shareJSON.setUserFace(latestlottery.getUserFace());
            shareJSON.setUserId(latestlottery.getUserId() + "");
            ShareJSONList.add(shareJSON);
        }

        return "index";
    }

    /**
     * 商品其他期数获得者
     *
     * @return
     */
    public String productOtherWinUser() {
        latestlotteryList = latestlotteryService.getProductOtherWinUser(productId, shareId);
        Struts2Utils.renderJson(latestlotteryList);
        return null;
    }

    /**
     * 评论列表
     *
     * @return
     */
    public String shareCommentListAjaxPage() {
        if (pageNo == 0) {
            pageNo = 1;
        } else {
            pageNo += 1;
        }
        Pagination datePage = shareService.shareByComment(shareId, pageNo, pageSize);
        List<Object[]> dataList = (List<Object[]>) datePage.getList();
        shareCommentJSONList = new ArrayList<ShareCommentJSON>();
        for (Object[] objects : dataList) {
            user = (User) objects[0];
            sharecomments = (Sharecomments) objects[1];
            shareCommentJSON = new ShareCommentJSON();
            shareCommentJSON.setContent(sharecomments.getContent());
            shareCommentJSON.setCreateDate(sharecomments.getCreateDate());
            shareCommentJSON.setUid(sharecomments.getUid());
            shareCommentJSON.setUserFace(user.getFaceImg());
            shareCommentJSON.setShareInfoId(sharecomments.getShareInfoId());
            String userName = UserNameUtil.userName(user);
            shareCommentJSON.setReCount(sharecomments.getReCount());
            shareCommentJSON.setUserName(userName);
            shareCommentJSON.setUserId(user.getUserId());
            shareCommentJSONList.add(shareCommentJSON);
        }
        Struts2Utils.renderJson(shareCommentJSONList);
        return null;
    }

    /**
     * 获取评论的评论列表
     */
    public void getReCommentList() {
        List<Object[]> objectList = shareService.getReCommentList(shareCommentId);
        shareCommentJSONList = new ArrayList<ShareCommentJSON>();
        for (Object[] objects : objectList) {
            user = (User) objects[0];
            sharecomments = (Sharecomments) objects[1];
            shareCommentJSON = new ShareCommentJSON();
            shareCommentJSON.setContent(sharecomments.getContent());
            shareCommentJSON.setCreateDate(sharecomments.getCreateDate());
            shareCommentJSON.setUid(sharecomments.getUid());
            shareCommentJSON.setUserFace(user.getFaceImg());
            shareCommentJSON.setShareInfoId(sharecomments.getShareInfoId());
            String userName = UserNameUtil.userName(user);
            shareCommentJSON.setReCount(sharecomments.getReCount());
            shareCommentJSON.setUserName(userName);
            shareCommentJSON.setUserId(user.getUserId());
            shareCommentJSONList.add(shareCommentJSON);
        }
        Struts2Utils.renderJson(shareCommentJSONList);
    }

    /**
     * 发表评论
     */
    public void postComment() {
        try {
            commentText = htmlFilter.filter(commentText);
            shareId = htmlFilter.filter(shareId);
            userId = htmlFilter.filter(userId);
            sharecomments = new Sharecomments();
            sharecomments.setContent(commentText);
            sharecomments.setCreateDate(DateUtil.DateTimeToStr(new Date()));
            if (reCommentId != null) {
                sharecomments.setReCommentId(Integer.parseInt(reCommentId));
                Sharecomments sharecomments = shareService.findBySharecommentsId(reCommentId);
                Integer reCount = sharecomments.getReCount();
                sharecomments.setReCount(reCount + 1);
                shareService.createComment(sharecomments);
            }
            sharecomments.setShareInfoId(Integer.parseInt(shareId));
            sharecomments.setUserId(Integer.parseInt(userId));
            sharecomments.setReCount(0);
            shareService.createComment(sharecomments);
            shareinfo = shareService.findById(shareId);
            Integer replyCount = shareinfo.getReplyCount();
            shareinfo.setReplyCount(replyCount + 1);
            shareService.add(shareinfo);
            Struts2Utils.renderText("true");
        } catch (Exception e) {
            e.printStackTrace();
            Struts2Utils.renderText("false");
        }

    }

    /**
     * 顶一下
     */
    public void upShareInfo() {
        shareinfo = shareService.findById(shareId);
        Integer upCount = shareinfo.getUpCount();
        shareinfo.setUpCount(upCount + 1);
        shareService.add(shareinfo);
        Struts2Utils.renderText("true");
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

    public ShareInfoPro getShareInfoPro() {
        return shareInfoPro;
    }

    public void setShareInfoPro(ShareInfoPro shareInfoPro) {
        this.shareInfoPro = shareInfoPro;
    }

    public Sharecomments getSharecomments() {
        return sharecomments;
    }

    public void setSharecomments(Sharecomments sharecomments) {
        this.sharecomments = sharecomments;
    }

    public List<ShareCommentJSON> getShareCommentJSONList() {
        return shareCommentJSONList;
    }

    public void setShareCommentJSONList(List<ShareCommentJSON> shareCommentJSONList) {
        this.shareCommentJSONList = shareCommentJSONList;
    }

    public Shareinfo getShareinfo() {
        return shareinfo;
    }

    public void setShareinfo(Shareinfo shareinfo) {
        this.shareinfo = shareinfo;
    }

    public List<Shareimage> getShareimageList() {
        return shareimageList;
    }

    public void setShareimageList(List<Shareimage> shareimageList) {
        this.shareimageList = shareimageList;
    }

    public Latestlottery getLatestlottery() {
        return latestlottery;
    }

    public void setLatestlottery(Latestlottery latestlottery) {
        this.latestlottery = latestlottery;
    }

    public List<Latestlottery> getLatestlotteryList() {
        return latestlotteryList;
    }

    public void setLatestlotteryList(List<Latestlottery> latestlotteryList) {
        this.latestlotteryList = latestlotteryList;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getShareId() {
        return shareId;
    }

    public void setShareId(String shareId) {
        this.shareId = shareId;
    }

    public ShareCommentJSON getShareCommentJSON() {
        return shareCommentJSON;
    }

    public void setShareCommentJSON(ShareCommentJSON shareCommentJSON) {
        this.shareCommentJSON = shareCommentJSON;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getShareCommentId() {
        return shareCommentId;
    }

    public void setShareCommentId(String shareCommentId) {
        this.shareCommentId = shareCommentId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public String getReCommentId() {
        return reCommentId;
    }

    public void setReCommentId(String reCommentId) {
        this.reCommentId = reCommentId;
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

    public ProductJSON getProductJSON() {
        return productJSON;
    }

    public void setProductJSON(ProductJSON productJSON) {
        this.productJSON = productJSON;
    }

    public List<ShareJSON> getShareJSONList() {
        return ShareJSONList;
    }

    public void setShareJSONList(List<ShareJSON> shareJSONList) {
        ShareJSONList = shareJSONList;
    }

    public ShareJSON getShareJSON() {
        return shareJSON;
    }

    public void setShareJSON(ShareJSON shareJSON) {
        this.shareJSON = shareJSON;
    }

    public Shareimage getShareimage() {
        return shareimage;
    }

    public void setShareimage(Shareimage shareimage) {
        this.shareimage = shareimage;
    }

}
