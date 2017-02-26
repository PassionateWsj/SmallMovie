package com.sha1607.smallmovie.bean;

import java.util.List;

/**
 * ***********************************************************
 * author: crane
 * time: 2016/10/26 10:12
 * name:
 * desc:
 * step:
 * ***********************************************************
 */


public class RecommandVideoCommentBean {

    /**
     * canLoadMore : 0
     * userCommentList : [{"commentinfo":{"praiseNum":"0","content":"居然能看到以前喜欢的印度片\u2026","id":"3fda1a90df854751962675d16c51404f","parentId":"","contentTitle":"《一个好故事》第11期：功夫小蝇","reportStatus":"0","isHot":"0","contentType":"2","type":"0","createDate":"1477397942","contentId":"6ed63e1c5c2f44aaaa314c9f6781a66c","replyNum":"0"},"isPraise":"0","replyuserinfo":{},"userInfo":{"headImgUrl":"http://q.qlogo.cn/qqapp/1105107664/7EC8893827954A059D0044FF6642D0B8/100","id":"2158c40f032f433a92f443ef99170901","nickname":"穆罕默德\u2022北领地\u2022瓜批酱"}}]
     * retCode : 0
     * currentPageContext : 1
     * totalCount : 1
     * requestId : 476f738d-eaee-4d21-b463-1e21533f2f2c
     * retMsg :
     */

    private String canLoadMore;
    private int retCode;
    private int currentPageContext;
    private int totalCount;
    private String requestId;
    private String retMsg;
    /**
     * commentinfo : {"praiseNum":"0","content":"居然能看到以前喜欢的印度片\u2026","id":"3fda1a90df854751962675d16c51404f","parentId":"","contentTitle":"《一个好故事》第11期：功夫小蝇","reportStatus":"0","isHot":"0","contentType":"2","type":"0","createDate":"1477397942","contentId":"6ed63e1c5c2f44aaaa314c9f6781a66c","replyNum":"0"}
     * isPraise : 0
     * replyuserinfo : {}
     * userInfo : {"headImgUrl":"http://q.qlogo.cn/qqapp/1105107664/7EC8893827954A059D0044FF6642D0B8/100","id":"2158c40f032f433a92f443ef99170901","nickname":"穆罕默德\u2022北领地\u2022瓜批酱"}
     */

    private List<UserCommentListBean> userCommentList;

    public String getCanLoadMore() {
        return canLoadMore;
    }

    public void setCanLoadMore(String canLoadMore) {
        this.canLoadMore = canLoadMore;
    }

    public int getRetCode() {
        return retCode;
    }

    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }

    public int getCurrentPageContext() {
        return currentPageContext;
    }

    public void setCurrentPageContext(int currentPageContext) {
        this.currentPageContext = currentPageContext;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public List<UserCommentListBean> getUserCommentList() {
        return userCommentList;
    }

    public void setUserCommentList(List<UserCommentListBean> userCommentList) {
        this.userCommentList = userCommentList;
    }

    public static class UserCommentListBean {
        /**
         * praiseNum : 0
         * content : 居然能看到以前喜欢的印度片…
         * id : 3fda1a90df854751962675d16c51404f
         * parentId :
         * contentTitle : 《一个好故事》第11期：功夫小蝇
         * reportStatus : 0
         * isHot : 0
         * contentType : 2
         * type : 0
         * createDate : 1477397942
         * contentId : 6ed63e1c5c2f44aaaa314c9f6781a66c
         * replyNum : 0
         */

        private CommentinfoBean commentinfo;
        private String isPraise;
        private ReplyuserinfoBean replyuserinfo;
        /**
         * headImgUrl : http://q.qlogo.cn/qqapp/1105107664/7EC8893827954A059D0044FF6642D0B8/100
         * id : 2158c40f032f433a92f443ef99170901
         * nickname : 穆罕默德•北领地•瓜批酱
         */

        private UserInfoBean userInfo;

        public CommentinfoBean getCommentinfo() {
            return commentinfo;
        }

        public void setCommentinfo(CommentinfoBean commentinfo) {
            this.commentinfo = commentinfo;
        }

        public String getIsPraise() {
            return isPraise;
        }

        public void setIsPraise(String isPraise) {
            this.isPraise = isPraise;
        }

        public ReplyuserinfoBean getReplyuserinfo() {
            return replyuserinfo;
        }

        public void setReplyuserinfo(ReplyuserinfoBean replyuserinfo) {
            this.replyuserinfo = replyuserinfo;
        }

        public UserInfoBean getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfoBean userInfo) {
            this.userInfo = userInfo;
        }

        public static class CommentinfoBean {
            private String praiseNum;
            private String content;
            private String id;
            private String parentId;
            private String contentTitle;
            private String reportStatus;
            private String isHot;
            private String contentType;
            private String type;
            private String createDate;
            private String contentId;
            private String replyNum;

            public String getPraiseNum() {
                return praiseNum;
            }

            public void setPraiseNum(String praiseNum) {
                this.praiseNum = praiseNum;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getParentId() {
                return parentId;
            }

            public void setParentId(String parentId) {
                this.parentId = parentId;
            }

            public String getContentTitle() {
                return contentTitle;
            }

            public void setContentTitle(String contentTitle) {
                this.contentTitle = contentTitle;
            }

            public String getReportStatus() {
                return reportStatus;
            }

            public void setReportStatus(String reportStatus) {
                this.reportStatus = reportStatus;
            }

            public String getIsHot() {
                return isHot;
            }

            public void setIsHot(String isHot) {
                this.isHot = isHot;
            }

            public String getContentType() {
                return contentType;
            }

            public void setContentType(String contentType) {
                this.contentType = contentType;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public String getContentId() {
                return contentId;
            }

            public void setContentId(String contentId) {
                this.contentId = contentId;
            }

            public String getReplyNum() {
                return replyNum;
            }

            public void setReplyNum(String replyNum) {
                this.replyNum = replyNum;
            }
        }

        public static class ReplyuserinfoBean {
        }

        public static class UserInfoBean {
            private String headImgUrl;
            private String id;
            private String nickname;

            public String getHeadImgUrl() {
                return headImgUrl;
            }

            public void setHeadImgUrl(String headImgUrl) {
                this.headImgUrl = headImgUrl;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }
        }
    }
}
