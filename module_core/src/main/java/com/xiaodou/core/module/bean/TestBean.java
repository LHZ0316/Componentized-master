package com.xiaodou.core.module.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * lhz  on 2019/8/21.
 */

public class TestBean {
    /**
     * message :
     * retOk : true
     * retcode : 0
     * retdesc : 操作成功
     * serverIp : 172.17.1.57
     * status : 1
     * token : afc2cae1-30bc-43f3-b0a9-af1a5b150b10
     * user : {"age":"0","bePraiseNum":"0","benchmarkFace":"","coin":"0","createTime":"2018-09-25 12:04:34.0","credit":"5161","gender":"1","id":"20545274033","idCard":"","identificationCardCode":"","importCategoryId":"","importCategoryName":"","importChiefUnitName":"","importChiefUnitType":"","importPilotUnitName":"","importPilotUnitType":"","latestDeviceIp":"172.17.20.81","learnDays":0,"loginDays":30,"mainAccount":"","major":"040106","majorId":"98","majorName":"学前教育(本科)","medalId":"1 ","medalImg":"http://7xigj3.com1.z0.glb.clouddn.com/image_2015_11_13_15_59_230f0054d4-e12a-41ee-b88d-ad67d728a263  ","medalName":"无名的旅人 ","message":"","nickName":"刘皓志","officialGender":"1","officialStatus":"0","picList":[],"portrait":"","realName":"刘浩志","realRegion":"3","region":"3","regionName":"贵州省","retOk":false,"sign":"","status":"1","token":"afc2cae1-30bc-43f3-b0a9-af1a5b150b10","tokenTime":"2019-09-18 18:44:50.853","usedDeviceId":"867913030525865","userId":"20545274033","userName":"","userType":"3","xdUniqueId":"20180925545662173"}
     */

    private String message;
    private boolean retOk;
    private String retcode;
    private String retdesc;
    private String serverIp;
    private String status;
    private String token;
    private UserBean user;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isRetOk() {
        return retOk;
    }

    public void setRetOk(boolean retOk) {
        this.retOk = retOk;
    }

    public String getRetcode() {
        return retcode;
    }

    public void setRetcode(String retcode) {
        this.retcode = retcode;
    }

    public String getRetdesc() {
        return retdesc;
    }

    public void setRetdesc(String retdesc) {
        this.retdesc = retdesc;
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public static class UserBean {
        /**
         * age : 0
         * bePraiseNum : 0
         * benchmarkFace :
         * coin : 0
         * createTime : 2018-09-25 12:04:34.0
         * credit : 5161
         * gender : 1
         * id : 20545274033
         * idCard :
         * identificationCardCode :
         * importCategoryId :
         * importCategoryName :
         * importChiefUnitName :
         * importChiefUnitType :
         * importPilotUnitName :
         * importPilotUnitType :
         * latestDeviceIp : 172.17.20.81
         * learnDays : 0
         * loginDays : 30
         * mainAccount :
         * major : 040106
         * majorId : 98
         * majorName : 学前教育(本科)
         * medalId : 1
         * medalImg : http://7xigj3.com1.z0.glb.clouddn.com/image_2015_11_13_15_59_230f0054d4-e12a-41ee-b88d-ad67d728a263
         * medalName : 无名的旅人
         * message :
         * nickName : 刘皓志
         * officialGender : 1
         * officialStatus : 0
         * picList : []
         * portrait :
         * realName : 刘浩志
         * realRegion : 3
         * region : 3
         * regionName : 贵州省
         * retOk : false
         * sign :
         * status : 1
         * token : afc2cae1-30bc-43f3-b0a9-af1a5b150b10
         * tokenTime : 2019-09-18 18:44:50.853
         * usedDeviceId : 867913030525865
         * userId : 20545274033
         * userName :
         * userType : 3
         * xdUniqueId : 20180925545662173
         */

        private String age;
        private String bePraiseNum;
        private String benchmarkFace;
        private String coin;
        private String createTime;
        private String credit;
        private String gender;
        @SerializedName("id")
        private String idX;
        private String idCard;
        private String identificationCardCode;
        private String importCategoryId;
        private String importCategoryName;
        private String importChiefUnitName;
        private String importChiefUnitType;
        private String importPilotUnitName;
        private String importPilotUnitType;
        private String latestDeviceIp;
        private int learnDays;
        private int loginDays;
        private String mainAccount;
        private String major;
        private String majorId;
        private String majorName;
        private String medalId;
        private String medalImg;
        private String medalName;
        private String message;
        private String nickName;
        private String officialGender;
        private String officialStatus;
        private String portrait;
        private String realName;
        private String realRegion;
        private String region;
        private String regionName;
        private boolean retOk;
        private String sign;
        private String status;
        private String token;
        private String tokenTime;
        private String usedDeviceId;
        private String userId;
        private String userName;
        private String userType;
        private String xdUniqueId;
        private List<?> picList;

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getBePraiseNum() {
            return bePraiseNum;
        }

        public void setBePraiseNum(String bePraiseNum) {
            this.bePraiseNum = bePraiseNum;
        }

        public String getBenchmarkFace() {
            return benchmarkFace;
        }

        public void setBenchmarkFace(String benchmarkFace) {
            this.benchmarkFace = benchmarkFace;
        }

        public String getCoin() {
            return coin;
        }

        public void setCoin(String coin) {
            this.coin = coin;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getCredit() {
            return credit;
        }

        public void setCredit(String credit) {
            this.credit = credit;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getIdX() {
            return idX;
        }

        public void setIdX(String idX) {
            this.idX = idX;
        }

        public String getIdCard() {
            return idCard;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
        }

        public String getIdentificationCardCode() {
            return identificationCardCode;
        }

        public void setIdentificationCardCode(String identificationCardCode) {
            this.identificationCardCode = identificationCardCode;
        }

        public String getImportCategoryId() {
            return importCategoryId;
        }

        public void setImportCategoryId(String importCategoryId) {
            this.importCategoryId = importCategoryId;
        }

        public String getImportCategoryName() {
            return importCategoryName;
        }

        public void setImportCategoryName(String importCategoryName) {
            this.importCategoryName = importCategoryName;
        }

        public String getImportChiefUnitName() {
            return importChiefUnitName;
        }

        public void setImportChiefUnitName(String importChiefUnitName) {
            this.importChiefUnitName = importChiefUnitName;
        }

        public String getImportChiefUnitType() {
            return importChiefUnitType;
        }

        public void setImportChiefUnitType(String importChiefUnitType) {
            this.importChiefUnitType = importChiefUnitType;
        }

        public String getImportPilotUnitName() {
            return importPilotUnitName;
        }

        public void setImportPilotUnitName(String importPilotUnitName) {
            this.importPilotUnitName = importPilotUnitName;
        }

        public String getImportPilotUnitType() {
            return importPilotUnitType;
        }

        public void setImportPilotUnitType(String importPilotUnitType) {
            this.importPilotUnitType = importPilotUnitType;
        }

        public String getLatestDeviceIp() {
            return latestDeviceIp;
        }

        public void setLatestDeviceIp(String latestDeviceIp) {
            this.latestDeviceIp = latestDeviceIp;
        }

        public int getLearnDays() {
            return learnDays;
        }

        public void setLearnDays(int learnDays) {
            this.learnDays = learnDays;
        }

        public int getLoginDays() {
            return loginDays;
        }

        public void setLoginDays(int loginDays) {
            this.loginDays = loginDays;
        }

        public String getMainAccount() {
            return mainAccount;
        }

        public void setMainAccount(String mainAccount) {
            this.mainAccount = mainAccount;
        }

        public String getMajor() {
            return major;
        }

        public void setMajor(String major) {
            this.major = major;
        }

        public String getMajorId() {
            return majorId;
        }

        public void setMajorId(String majorId) {
            this.majorId = majorId;
        }

        public String getMajorName() {
            return majorName;
        }

        public void setMajorName(String majorName) {
            this.majorName = majorName;
        }

        public String getMedalId() {
            return medalId;
        }

        public void setMedalId(String medalId) {
            this.medalId = medalId;
        }

        public String getMedalImg() {
            return medalImg;
        }

        public void setMedalImg(String medalImg) {
            this.medalImg = medalImg;
        }

        public String getMedalName() {
            return medalName;
        }

        public void setMedalName(String medalName) {
            this.medalName = medalName;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getOfficialGender() {
            return officialGender;
        }

        public void setOfficialGender(String officialGender) {
            this.officialGender = officialGender;
        }

        public String getOfficialStatus() {
            return officialStatus;
        }

        public void setOfficialStatus(String officialStatus) {
            this.officialStatus = officialStatus;
        }

        public String getPortrait() {
            return portrait;
        }

        public void setPortrait(String portrait) {
            this.portrait = portrait;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getRealRegion() {
            return realRegion;
        }

        public void setRealRegion(String realRegion) {
            this.realRegion = realRegion;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getRegionName() {
            return regionName;
        }

        public void setRegionName(String regionName) {
            this.regionName = regionName;
        }

        public boolean isRetOk() {
            return retOk;
        }

        public void setRetOk(boolean retOk) {
            this.retOk = retOk;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getTokenTime() {
            return tokenTime;
        }

        public void setTokenTime(String tokenTime) {
            this.tokenTime = tokenTime;
        }

        public String getUsedDeviceId() {
            return usedDeviceId;
        }

        public void setUsedDeviceId(String usedDeviceId) {
            this.usedDeviceId = usedDeviceId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }

        public String getXdUniqueId() {
            return xdUniqueId;
        }

        public void setXdUniqueId(String xdUniqueId) {
            this.xdUniqueId = xdUniqueId;
        }

        public List<?> getPicList() {
            return picList;
        }

        public void setPicList(List<?> picList) {
            this.picList = picList;
        }
    }
}
