package com.eypg.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.eypg.dao.Pagination;
import com.eypg.pojo.BuyHistoryJSON;
import com.eypg.pojo.Friends;
import com.eypg.pojo.Latestlottery;
import com.eypg.pojo.Product;
import com.eypg.pojo.ShareJSON;
import com.eypg.pojo.Shareimage;
import com.eypg.pojo.Shareinfo;
import com.eypg.pojo.Spellbuyproduct;
import com.eypg.pojo.Spellbuyrecord;
import com.eypg.pojo.User;
import com.eypg.pojo.Visitors;
import com.eypg.service.FriendsService;
import com.eypg.service.LatestlotteryService;
import com.eypg.service.ShareService;
import com.eypg.service.SpellbuyrecordService;
import com.eypg.service.UserService;
import com.eypg.service.VisitorsService;
import com.eypg.util.DateUtil;
import com.eypg.util.Struts2Utils;
import com.eypg.util.UserNameUtil;
import com.opensymphony.xwork2.ActionSupport;

public class UserInfoAction extends ActionSupport {

    private static final long serialVersionUID = 8426857698329495895L;

    @Autowired
    private UserService userService;
    @Autowired
    private SpellbuyrecordService spellbuyrecordService;
    @Autowired
    private LatestlotteryService latestlotteryService;
    @Autowired
    private ShareService shareService;
    @Autowired
    private FriendsService friendsService;
    @Autowired
    private VisitorsService visitorsService;

    private User user;
    private String id;
    private String userId;
    private int pageNo;
    private int pageSize = 12;
    private int pageCount;
    private int resultCount;
    private String userName;
    private Product product;
    private Spellbuyproduct spellbuyproduct;
    private Spellbuyrecord spellbuyrecord;
    private BuyHistoryJSON buyHistoryJSON;
    private Latestlottery latestlottery;
    private List<BuyHistoryJSON> buyHistoryJSONList;
    private List<ShareJSON> ShareJSONList;
    private ShareJSON shareJSON;
    private Shareinfo shareinfo;
    private Shareimage shareimage;
    private List<User> userList;
    private Friends friends;
    private Visitors visitors;

    HttpServletRequest request = null;

    public String index() {
        request = Struts2Utils.getRequest();
        if (pageNo == 0) {
            pageNo = 1;
        } else {
            pageNo += 1;
        }

        user = userService.findById(id);

        userName = UserNameUtil.userName(user);

        Pagination datePage = spellbuyrecordService.buyHistoryByUser(id, null, null, pageNo, 6);
        List<BuyHistoryJSON> dataList = (List<BuyHistoryJSON>) datePage.getList();
        if (dataList != null && dataList.size() > 0) {
            buyHistoryJSONList = new ArrayList<BuyHistoryJSON>();
            for (int j = 0; j < dataList.size(); j++) {
                try {
                    buyHistoryJSON = dataList.get(j);
                    if (buyHistoryJSON.getBuyStatus() == 1) {
                        List<Latestlottery> list = latestlotteryService.getBuyHistoryByDetail(buyHistoryJSON.getProductId());
                        if (list != null && list.size() > 0) {
                            latestlottery = (Latestlottery) latestlotteryService.getBuyHistoryByDetail(buyHistoryJSON.getProductId()).get(0);
                        }
                        if (latestlottery != null) {
                            buyHistoryJSON.setBuyTime(DateUtil.getTime(DateUtil.SDateTimeToDate(latestlottery.getAnnouncedTime())));
                            buyHistoryJSON.setWinDate(latestlottery.getAnnouncedTime());
                            buyHistoryJSON.setWinId(latestlottery.getRandomNumber());
                            String userer = null;
                            if (latestlottery.getUserName() != null && !latestlottery.getUserName().equals("")) {
                                userer = latestlottery.getUserName();
                            } else {
                                if (latestlottery.getBuyUser() != null && !latestlottery.getBuyUser().equals("")) {
                                    userer = latestlottery.getBuyUser();
                                    if (userer.indexOf("@") != -1) {
                                        String[] u = userer.split("@");
                                        String u1 = u[0].substring(0, 2) + "***";
                                        userer = u1 + "@" + u[1];
                                    } else {
                                        userer = userer.substring(0, 4) + "*** " + userer.substring(7);
                                    }
                                }
                            }
                            buyHistoryJSON.setWinUserId(latestlottery.getUserId());
                            buyHistoryJSON.setWinUser(userer);
                        }
                    } else {
                        String userName = UserNameUtil.userName(user);
                        String timeStr = DateUtil.getTime(DateUtil.SDateTimeToDate(buyHistoryJSON.getBuyTime()));
                        buyHistoryJSON.setBuyTime(timeStr);
                        buyHistoryJSON.setWinUser(userName);
                    }
                    buyHistoryJSONList.add(buyHistoryJSON);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }

        /**
         * 访客
         */
        Cookie[] cookies = request.getCookies();
        if (request.isRequestedSessionIdFromCookie()) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookie.getName().equals("userId")) {
                    userId = cookie.getValue();
                    if (userId != null && !userId.equals("")) {
                        visitors = visitorsService.findVisitorsByUserIdAndVisitorsId(id, userId);
                        if (visitors != null) {
                            List<Visitors> visitorsList = visitorsService.findVisitors(id, userId, DateUtil.DateTimeToStr(DateUtils.addHours(new Date(), -1)), DateUtil.DateTimeToStr(new Date()));
                            if (visitorsList.size() < 0) {
//								String ip = Struts2Utils.getRequest().getRemoteAddr();
                                String ip = Struts2Utils.getRequest().getHeader("X-Real-IP");
                                if (ip == null) {
                                    ip = "127.0.0.1";
                                }
                                String ipLocation = RegisterAction.seeker.getAddress(ip);
                                visitors.setAddress(ipLocation);
                                visitors.setDate(DateUtil.DateTimeToStr(new Date()));
                                visitors.setUid(Integer.parseInt(id));
                                visitors.setVisitorsId(Integer.parseInt(userId));
                                visitorsService.add(visitors);
                            }
                        } else {
//							String ip = Struts2Utils.getRequest().getRemoteAddr();
                            String ip = Struts2Utils.getRequest().getHeader("X-Real-IP");
                            if (ip == null) {
                                ip = "127.0.0.1";
                            }
                            String ipLocation = RegisterAction.seeker.getAddress(ip);
                            visitors = new Visitors();
                            visitors.setAddress(ipLocation);
                            visitors.setDate(DateUtil.DateTimeToStr(new Date()));
                            visitors.setUid(Integer.parseInt(id));
                            visitors.setVisitorsId(Integer.parseInt(userId));
                            visitorsService.add(visitors);
                        }
                    }
                }
            }
        }

        return "index";
    }

    public static void main(String[] args) {
        System.err.println(DateUtil.DateTimeToStr(DateUtils.addHours(new Date(), -1)));
    }

    /**
     * 拍购记录
     *
     * @return
     */
    public String userBuy() {
        if (pageNo == 0) {
            pageNo = 1;
        } else {
            pageNo += 1;
        }

        user = userService.findById(id);

        userName = UserNameUtil.userName(user);

        resultCount = spellbuyrecordService.buyHistoryByUserByCount(id, null, null);
        return "UserBuy";
    }

    /**
     * 拍购记录AJAX分页
     *
     * @return
     */
    public String userBuyAjaxPage() {
        if (pageNo == 0) {
            pageNo = 1;
        } else {
            pageNo += 1;
        }
        user = userService.findById(id);
        Pagination datePage = spellbuyrecordService.buyHistoryByUser(id, null, null, pageNo, pageSize);
        List<BuyHistoryJSON> dataList = (List<BuyHistoryJSON>) datePage.getList();
        buyHistoryJSONList = new ArrayList<BuyHistoryJSON>();
        if (dataList != null && dataList.size() > 0) {
            for (int j = 0; j < dataList.size(); j++) {
                buyHistoryJSON = dataList.get(j);
                if (buyHistoryJSON.getBuyStatus() == 1) {
                    latestlottery = (Latestlottery) latestlotteryService.getBuyHistoryByDetail(buyHistoryJSON.getProductId()).get(0);
                    buyHistoryJSON.setWinDate(latestlottery.getAnnouncedTime());
                    buyHistoryJSON.setWinId(latestlottery.getRandomNumber());
                    String userer = null;
                    if (latestlottery.getUserName() != null && !latestlottery.getUserName().equals("")) {
                        userer = latestlottery.getUserName();
                    } else {
                        if (latestlottery.getBuyUser() != null && !latestlottery.getBuyUser().equals("")) {
                            userer = latestlottery.getBuyUser();
                            if (userer.indexOf("@") != -1) {
                                String[] u = userer.split("@");
                                String u1 = u[0].substring(0, 2) + "***";
                                userer = u1 + "@" + u[1];
                            } else {
                                userer = userer.substring(0, 4) + "*** " + userer.substring(7);
                            }
                        }
                    }
                    buyHistoryJSON.setWinUser(userer);
                    buyHistoryJSON.setWinUserId(latestlottery.getUserId());
                } else {
                    String userName = UserNameUtil.userName(user);
                    buyHistoryJSON.setWinUser(userName);
                }
                buyHistoryJSONList.add(buyHistoryJSON);
            }
        }

        Struts2Utils.renderJson(buyHistoryJSONList);
        return null;
    }

    /**
     * 获得的商品
     *
     * @return
     */
    public String userRaffle() {
        if (pageNo == 0) {
            pageNo = 1;
        } else {
            pageNo += 1;
        }

        user = userService.findById(id);

        userName = UserNameUtil.userName(user);
        resultCount = latestlotteryService.getProductByUserByCount(id, null, null);
        return "UserRaffle";
    }

    /**
     * 获得的商AJAX品分页
     *
     * @return
     */
    public String userRaffleAjaxPage() {
        if (pageNo == 0) {
            pageNo = 1;
        } else {
            pageNo += 1;
        }
        Pagination datePage = latestlotteryService.getProductByUser(id, null, null, pageNo, pageSize);
        List<Latestlottery> dataList = (List<Latestlottery>) datePage.getList();
        buyHistoryJSONList = new ArrayList<BuyHistoryJSON>();
        if (dataList != null && dataList.size() > 0) {
            for (int j = 0; j < dataList.size(); j++) {
                buyHistoryJSON = new BuyHistoryJSON();
                latestlottery = dataList.get(j);
                buyHistoryJSON.setProductId(latestlottery.getSpellbuyProductId());
                buyHistoryJSON.setProductImg(latestlottery.getProductImg());
                buyHistoryJSON.setProductName(latestlottery.getProductName());
                buyHistoryJSON.setProductPeriod(latestlottery.getProductPeriod());
                buyHistoryJSON.setProductPrice(latestlottery.getProductPrice());
                buyHistoryJSON.setProductTitle(latestlottery.getProductTitle());
                buyHistoryJSON.setWinDate(latestlottery.getAnnouncedTime());
                buyHistoryJSON.setWinId(latestlottery.getRandomNumber());
                buyHistoryJSONList.add(buyHistoryJSON);
            }
        }

        Struts2Utils.renderJson(buyHistoryJSONList);
        return null;
    }

    /**
     * 晒单
     *
     * @return
     */
    public String userPost() {
        if (pageNo == 0) {
            pageNo = 1;
        } else {
            pageNo += 1;
        }

        user = userService.findById(id);

        userName = UserNameUtil.userName(user);
        Pagination datePage = shareService.shareByUser(id, null, null, pageNo, pageSize);
        resultCount = datePage.getResultCount();
        return "UserPost";
    }

    /**
     * 晒单AJAX分页
     *
     * @return
     */
    public String userPostAjaxPage() {
        if (pageNo == 0) {
            pageNo = 1;
        } else {
            pageNo += 1;
        }
        Pagination page = shareService.shareByUser(id, null, null, pageNo, pageSize);
        List<Object[]> pageList = (List<Object[]>) page.getList();
        ShareJSONList = new ArrayList<ShareJSON>();
        if (pageList != null && pageList.size() > 0) {
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
                shareJSON.setShareDate(shareinfo.getShareDate());
                shareJSON.setShareImages(shareimage.getImages());
                shareJSON.setShareTitle(shareinfo.getShareTitle());
                shareJSON.setUid(shareinfo.getUid());
                shareJSON.setUpCount(shareinfo.getUpCount());
                shareJSON.setUserName(userName);
                shareJSON.setUserFace(latestlottery.getUserFace());
                shareJSON.setUserId(latestlottery.getUserId() + "");
                ShareJSONList.add(shareJSON);
            }
        }

        Struts2Utils.renderJson(ShareJSONList);
        return null;
    }

    /**
     * 好友
     *
     * @return
     */
    public String userFriends() {
        if (pageNo == 0) {
            pageNo = 1;
        } else {
            pageNo += 1;
        }

        user = userService.findById(id);

        userName = UserNameUtil.userName(user);
        Pagination datePage = friendsService.getFriends(id, pageNo, pageSize);
        resultCount = datePage.getResultCount();
        return "UserFriends";
    }

    /**
     * 好友AJAX分页
     *
     * @return
     */
    public String userFriendsAjaxPage() {
        if (pageNo == 0) {
            pageNo = 1;
        } else {
            pageNo += 1;
        }
        Pagination page = friendsService.getFriends(id, pageNo, pageSize);
        List<Object[]> pageList = (List<Object[]>) page.getList();
        userList = new ArrayList<User>();
        if (pageList != null && pageList.size() > 0) {
            for (int i = 0; i < pageList.size(); i++) {
                friends = (Friends) pageList.get(i)[0];
                user = (User) pageList.get(i)[1];
                userName = UserNameUtil.userName(user);
                user.setUserId(friends.getFriendsId());
                user.setUserName(userName);
                userList.add(user);
            }
        }
        Struts2Utils.renderJson(userList);
        return null;
    }

    /**
     * 最近访客(访问)
     *
     * @return
     */
    public String visitors() {
        request = Struts2Utils.getRequest();
        Cookie[] cookies = request.getCookies();
        if (request.isRequestedSessionIdFromCookie()) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookie.getName().equals("userId")) {
                    userId = cookie.getValue();
                    if (userId != null && !userId.equals("")) {
                        user = userService.findById(userId);
                        visitors = new Visitors();
                        visitors.setAddress(user.getIpLocation());
                        visitors.setDate(DateUtil.DateTimeToStr(new Date()));
                        visitors.setUid(Integer.parseInt(id));
                        visitors.setVisitorsId(Integer.parseInt(userId));
                        visitorsService.add(visitors);
                    }
                }
            }
        }
        return null;
    }

    /**
     * 访问列表
     *
     * @return
     */
    public String visitorsList() {
        if (pageNo == 0) {
            pageNo = 1;
        } else {
            pageNo += 1;
        }
        Pagination page = visitorsService.getVisitors(userId, pageNo, 9);
        List<Object[]> pageList = (List<Object[]>) page.getList();
        userList = new ArrayList<User>();
        if (pageList != null && pageList.size() > 0) {
            for (int i = 0; i < pageList.size(); i++) {
                user = (User) pageList.get(i)[0];
                visitors = (Visitors) pageList.get(i)[1];
                userName = UserNameUtil.userName(user);
                user.setNewDate(DateUtil.getTime(DateUtil.SDateTimeToDate(visitors.getDate())));
                user.setUserId(visitors.getVisitorsId());
                user.setUserName(userName);
                user.setIpLocation(visitors.getAddress());
                userList.add(user);
            }
        }
        Struts2Utils.renderJson(userList);
        return null;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Spellbuyrecord getSpellbuyrecord() {
        return spellbuyrecord;
    }

    public void setSpellbuyrecord(Spellbuyrecord spellbuyrecord) {
        this.spellbuyrecord = spellbuyrecord;
    }

    public BuyHistoryJSON getBuyHistoryJSON() {
        return buyHistoryJSON;
    }

    public void setBuyHistoryJSON(BuyHistoryJSON buyHistoryJSON) {
        this.buyHistoryJSON = buyHistoryJSON;
    }

    public Latestlottery getLatestlottery() {
        return latestlottery;
    }

    public void setLatestlottery(Latestlottery latestlottery) {
        this.latestlottery = latestlottery;
    }

    public List<BuyHistoryJSON> getBuyHistoryJSONList() {
        return buyHistoryJSONList;
    }

    public void setBuyHistoryJSONList(List<BuyHistoryJSON> buyHistoryJSONList) {
        this.buyHistoryJSONList = buyHistoryJSONList;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public Shareinfo getShareinfo() {
        return shareinfo;
    }

    public void setShareinfo(Shareinfo shareinfo) {
        this.shareinfo = shareinfo;
    }

    public Shareimage getShareimage() {
        return shareimage;
    }

    public void setShareimage(Shareimage shareimage) {
        this.shareimage = shareimage;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public Friends getFriends() {
        return friends;
    }

    public void setFriends(Friends friends) {
        this.friends = friends;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Visitors getVisitors() {
        return visitors;
    }

    public void setVisitors(Visitors visitors) {
        this.visitors = visitors;
    }
}
