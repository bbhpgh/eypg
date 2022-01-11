package com.eypg.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eypg.dao.Pagination;
import com.eypg.pojo.Latestlottery;
import com.eypg.pojo.ShareJSON;
import com.eypg.pojo.Shareimage;
import com.eypg.pojo.Shareinfo;
import com.eypg.service.ShareService;
import com.eypg.util.ApplicationListenerImpl;
import com.eypg.util.DateUtil;
import com.eypg.util.PaginationUtil;
import com.eypg.util.Struts2Utils;
import com.opensymphony.xwork2.ActionSupport;

@Component("ShareAction")
public class ShareAction extends ActionSupport {

    private static final long serialVersionUID = -8202280107475087499L;
    @Autowired
    ShareService shareService;

    private Shareinfo shareinfo;
    private Shareimage shareimage;
    private Latestlottery latestlottery;
    private List<ShareJSON> ShareJSONList;
    private ShareJSON shareJSON;
    private String id;

    private int pageNo;
    private String pages;
    private String pageString;
    private int pageSize = 20;
    private int pageCount;
    private int resultCount;

    private static List<ShareJSON> ShareJSONByIndexList;
    private static Long nowDateByShareJSONByIndex = System.currentTimeMillis();
    private static Long beginDateByShareJSONByIndex;

    public String index() {
        if (pageNo == 0) {
            pageNo = 1;
        }
        if (pages != null) {
            pageNo = Integer.parseInt(pages.split("_")[1]);
        }
        if (id.equals("new20")) {
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
            resultCount = page.getResultCount();
            pageString = PaginationUtil.getPaginationHtml(resultCount, pageSize, pageNo, 2, 5, ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/share/" + id + "/p_");
        }
        if (id.equals("hot20")) {
            Pagination page = shareService.loadPageShare("hot20", pageNo, pageSize);
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
            resultCount = page.getResultCount();
            pageString = PaginationUtil.getPaginationHtml(resultCount, pageSize, pageNo, 2, 5, ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/share/" + id + "/p_");
        }
        if (id.equals("reply20")) {
            Pagination page = shareService.loadPageShare("reply20", pageNo, pageSize);
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
            resultCount = page.getResultCount();
            pageString = PaginationUtil.getPaginationHtml(resultCount, pageSize, pageNo, 2, 5, ApplicationListenerImpl.sysConfigureJson.getWwwUrl() + "/share/" + id + "/p_");
        }

        return "index";
    }

    public String ajaxPage() {
        if (pageNo == 0) {
            pageNo = 1;
        } else {
            pageNo += 1;
        }
        nowDateByShareJSONByIndex = System.currentTimeMillis();
        if (beginDateByShareJSONByIndex == null) {
            Pagination page = shareService.loadPageShare("new20", pageNo, pageSize);
            List<Object[]> pageList = (List<Object[]>) page.getList();
            ShareJSONByIndexList = new ArrayList<ShareJSON>();
            for (int i = 0; i < pageList.size(); i++) {
                shareJSON = new ShareJSON();
                shareinfo = (Shareinfo) pageList.get(i)[0];
                shareimage = (Shareimage) pageList.get(i)[1];
                latestlottery = (Latestlottery) pageList.get(i)[2];
                String userer = null;
                if (latestlottery.getUserName() != null && !latestlottery.getUserName().equals("")) {
                    userer = latestlottery.getUserName();
                } else if (latestlottery.getBuyUser() != null && !latestlottery.getBuyUser().equals("")) {
                    userer = latestlottery.getBuyUser();
                    if (userer.indexOf("@") != -1) {
                        String[] u = userer.split("@");
                        String u1 = u[0].substring(0, 2) + "***";
                        userer = u1 + "@" + u[1];
                    } else {
                        userer = userer.substring(0, 4) + "*** " + userer.substring(7);
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
                shareJSON.setUserName(userer);
                shareJSON.setUserFace(latestlottery.getUserFace());
                shareJSON.setUserId(latestlottery.getUserId() + "");
                ShareJSONByIndexList.add(shareJSON);
            }
            beginDateByShareJSONByIndex = System.currentTimeMillis();
            Struts2Utils.renderJson(ShareJSONByIndexList);
        } else {
            if ((nowDateByShareJSONByIndex - beginDateByShareJSONByIndex) < 5000) {
                Struts2Utils.renderJson(ShareJSONByIndexList);
            } else {
                beginDateByShareJSONByIndex = System.currentTimeMillis();
                Pagination page = shareService.loadPageShare("new20", pageNo, pageSize);
                List<Object[]> pageList = (List<Object[]>) page.getList();
                ShareJSONByIndexList = new ArrayList<ShareJSON>();
                for (int i = 0; i < pageList.size(); i++) {
                    shareJSON = new ShareJSON();
                    shareinfo = (Shareinfo) pageList.get(i)[0];
                    shareimage = (Shareimage) pageList.get(i)[1];
                    latestlottery = (Latestlottery) pageList.get(i)[2];
                    String userer = null;
                    if (latestlottery.getUserName() != null && !latestlottery.getUserName().equals("")) {
                        userer = latestlottery.getUserName();
                    } else if (latestlottery.getBuyUser() != null && !latestlottery.getBuyUser().equals("")) {
                        userer = latestlottery.getBuyUser();
                        if (userer.indexOf("@") != -1) {
                            String[] u = userer.split("@");
                            String u1 = u[0].substring(0, 2) + "***";
                            userer = u1 + "@" + u[1];
                        } else {
                            userer = userer.substring(0, 4) + "*** " + userer.substring(7);
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
                    shareJSON.setUserName(userer);
                    shareJSON.setUserFace(latestlottery.getUserFace());
                    shareJSON.setUserId(latestlottery.getUserId() + "");
                    ShareJSONByIndexList.add(shareJSON);
                }
                Struts2Utils.renderJson(ShareJSONByIndexList);
            }
        }
//		if(id.equals("new20")){
//			Pagination page = shareService.loadPageShare("new20",pageNo, pageSize);
//			List<Object[]> pageList = (List<Object[]>) page.getList();
//			ShareJSONList = new ArrayList<ShareJSON>();
//			for (int i = 0; i < pageList.size(); i++) {
//				shareJSON = new ShareJSON();
//				shareinfo = (Shareinfo) pageList.get(i)[0];
//				shareimage = (Shareimage) pageList.get(i)[1];
//				latestlottery = (Latestlottery) pageList.get(i)[2];
//				String userer = null;
//				if(latestlottery.getUserName()!=null && !latestlottery.getUserName().equals("")){
//					userer = latestlottery.getUserName();
//				}else if(latestlottery.getBuyUser()!=null && !latestlottery.getBuyUser().equals("")){
//					userer = latestlottery.getBuyUser();
//					if(userer.indexOf("@")!=-1){
//						String[] u = userer.split("@");
//						String u1 = u[0].substring(0,2)+"***";
//						userer = u1+"@"+u[1];
//					}else{
//						userer = userer.substring(0,4)+ "*** "+userer.substring(7);
//					}
//				}
//				shareJSON.setAnnouncedTime(latestlottery.getAnnouncedTime().substring(0,10));
//				shareJSON.setReplyCount(shareinfo.getReplyCount());
//				shareJSON.setReward(shareinfo.getReward());
//				shareJSON.setShareContent(shareinfo.getShareContent());
//				shareJSON.setShareDate(shareinfo.getShareDate());
//				shareJSON.setShareImages(shareimage.getImages());
//				shareJSON.setShareTitle(shareinfo.getShareTitle());
//				shareJSON.setUid(shareinfo.getUid());
//				shareJSON.setUpCount(shareinfo.getUpCount());
//				shareJSON.setUserName(userer);
//				shareJSON.setUserFace(latestlottery.getUserFace());
//				shareJSON.setUserId(latestlottery.getUserId()+"");
//				ShareJSONList.add(shareJSON);
//			}
//			Struts2Utils.renderJson(ShareJSONList);
//		}
//		if(id.equals("hot20")){
//			Pagination page = shareService.loadPageShare("hot20",pageNo, pageSize);
//			List<Object[]> pageList = (List<Object[]>) page.getList();
//			ShareJSONList = new ArrayList<ShareJSON>();
//			for (int i = 0; i < pageList.size(); i++) {
//				shareJSON = new ShareJSON();
//				shareinfo = (Shareinfo) pageList.get(i)[0];
//				shareimage = (Shareimage) pageList.get(i)[1];
//				latestlottery = (Latestlottery) pageList.get(i)[2];
//				String userer = null;
//				if(latestlottery.getBuyUser()!=null && !latestlottery.getBuyUser().equals("")){
//					userer = latestlottery.getBuyUser();
//					if(userer.indexOf("@")!=-1){
//						String[] u = userer.split("@");
//						String u1 = u[0].substring(0,2)+"***";
//						userer = u1+"@"+u[1];
//					}else{
//						userer = userer.substring(0,4)+ "*** "+userer.substring(7); 
//					}
//				}
//				shareJSON.setAnnouncedTime(latestlottery.getAnnouncedTime().substring(0,10));
//				shareJSON.setReplyCount(shareinfo.getReplyCount());
//				shareJSON.setReward(shareinfo.getReward());
//				shareJSON.setShareContent(shareinfo.getShareContent());
//				shareJSON.setShareDate(shareinfo.getShareDate());
//				shareJSON.setShareImages(shareimage.getImages());
//				shareJSON.setShareTitle(shareinfo.getShareTitle());
//				shareJSON.setUid(shareinfo.getUid());
//				shareJSON.setUpCount(shareinfo.getUpCount());
//				shareJSON.setUserName(userer);
//				shareJSON.setUserFace(latestlottery.getUserFace());
//				shareJSON.setUserId(latestlottery.getUserId()+"");
//				ShareJSONList.add(shareJSON);
//			}
//			Struts2Utils.renderJson(ShareJSONList);
//		}
//		if(id.equals("reply20")){
//			Pagination page = shareService.loadPageShare("reply20",pageNo, pageSize);
//			List<Object[]> pageList = (List<Object[]>) page.getList();
//			ShareJSONList = new ArrayList<ShareJSON>();
//			for (int i = 0; i < pageList.size(); i++) {
//				shareJSON = new ShareJSON();
//				shareinfo = (Shareinfo) pageList.get(i)[0];
//				shareimage = (Shareimage) pageList.get(i)[1];
//				latestlottery = (Latestlottery) pageList.get(i)[2];
//				String userer = null;
//				if(latestlottery.getBuyUser()!=null && !latestlottery.getBuyUser().equals("")){
//					userer = latestlottery.getBuyUser();
//					if(userer.indexOf("@")!=-1){
//						String[] u = userer.split("@");
//						String u1 = u[0].substring(0,2)+"***";
//						userer = u1+"@"+u[1];
//					}else{
//						userer = userer.substring(0,4)+ "*** "+userer.substring(7); 
//					}
//				}
//				shareJSON.setAnnouncedTime(latestlottery.getAnnouncedTime().substring(0,10));
//				shareJSON.setReplyCount(shareinfo.getReplyCount());
//				shareJSON.setReward(shareinfo.getReward());
//				shareJSON.setShareContent(shareinfo.getShareContent());
//				shareJSON.setShareDate(shareinfo.getShareDate());
//				shareJSON.setShareImages(shareimage.getImages());
//				shareJSON.setShareTitle(shareinfo.getShareTitle());
//				shareJSON.setUid(shareinfo.getUid());
//				shareJSON.setUpCount(shareinfo.getUpCount());
//				shareJSON.setUserName(userer);
//				shareJSON.setUserFace(latestlottery.getUserFace());
//				shareJSON.setUserId(latestlottery.getUserId()+"");
//				ShareJSONList.add(shareJSON);
//			}
//			Struts2Utils.renderJson(ShareJSONList);
//		}

        return null;
    }

    public String shareShow() {

        return null;
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

    public Latestlottery getLatestlottery() {
        return latestlottery;
    }

    public void setLatestlottery(Latestlottery latestlottery) {
        this.latestlottery = latestlottery;
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
