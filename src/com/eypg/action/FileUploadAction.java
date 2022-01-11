package com.eypg.action;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

import com.eypg.util.ConfigUtil;
import com.eypg.util.StringUtil;
import com.eypg.util.Struts2Utils;
import com.opensymphony.xwork2.ActionSupport;

@Component("FileUploadAction")
public class FileUploadAction extends ActionSupport {

    private static final long serialVersionUID = -6979064905632902960L;
    Log log = LogFactory.getLog(FileUploadAction.class);
    String folder;
    File filedata;
    String filedataContentType;
    String filedataFileName;
    String id;

    public String uploadImage() {
        if (filedata == null || StringUtil.isBlank(filedataFileName)) {
            log.error("FileUploadAction.updateImage 文件上传失败!文件为空");
        }
        File savefile = null;
        try {
            /**拼接文件存放路径**/
            String saveRealFilePath = ServletActionContext.getRequest()
                    .getSession().getServletContext().getRealPath("/")
                    + ConfigUtil.getValue("upfilefolder");
            if (StringUtil.isNotBlank(folder)) {
                saveRealFilePath = saveRealFilePath + folder.replaceAll("[/\\\\]", "");
            }
            File fileDir = new File(saveRealFilePath);
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
            //Todo 如果文件已经存在……
            savefile = new File(saveRealFilePath + filedataFileName);
            if (savefile.exists()) {
                Struts2Utils.renderText("{\"err\":\"\",\"msg\":\"上传文件失败！文件已存在.请修改文件名后重新上传.}");
                return null;
            }
            FileUtils.copyFile(filedata, savefile);
            Struts2Utils.renderText("{\"success\":\"\",\"msg\":\""
                    + savefile + "\"}");
            return null;
        } catch (IOException e) {
            log.error("上传文件失败!", e);
            Struts2Utils.renderJson("{\"err\":\"" + e.getMessage()
                    + "\",\"msg\":\"" + "上传文件失败!" + "\"}");
        }
        return null;
    }

    public File getFiledata() {
        return filedata;
    }

    public void setFiledata(File filedata) {
        this.filedata = filedata;
    }

    public String getFiledataContentType() {
        return filedataContentType;
    }

    public void setFiledataContentType(String filedataContentType) {
        this.filedataContentType = filedataContentType;
    }

    public String getFiledataFileName() {
        return filedataFileName;
    }

    public void setFiledataFileName(String filedataFileName) {
        this.filedataFileName = filedataFileName;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }
}
