package com.sha1607.smallmovie.bean;

import java.io.Serializable;
import java.util.List;

/**
 * ***********************************************************
 * author: crane
 * time: 2016/10/25 20:23
 * name:
 * desc:
 * step:
 * ***********************************************************
 */


public class RecommendListBean implements Serializable{

    private int retCode;
    private String requestId;
    private String retMsg;

    private List<InfoListBean> infoList;

    private List<ForcusImageListBean> forcusImageList;

    public int getRetCode() {
        return retCode;
    }

    public void setRetCode(int retCode) {
        this.retCode = retCode;
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

    public List<InfoListBean> getInfoList() {
        return infoList;
    }

    public void setInfoList(List<InfoListBean> infoList) {
        this.infoList = infoList;
    }

    public List<ForcusImageListBean> getForcusImageList() {
        return forcusImageList;
    }

    public void setForcusImageList(List<ForcusImageListBean> forcusImageList) {
        this.forcusImageList = forcusImageList;
    }

    public static class InfoListBean implements Serializable {

        private String requestId;

        private ObjectBean object;
        private String objectType;

        public String getRequestId() {
            return requestId;
        }

        public void setRequestId(String requestId) {
            this.requestId = requestId;
        }

        public ObjectBean getObject() {
            return object;
        }

        public void setObject(ObjectBean object) {
            this.object = object;
        }

        public String getObjectType() {
            return objectType;
        }

        public void setObjectType(String objectType) {
            this.objectType = objectType;
        }

        public static class ObjectBean implements Serializable {
            private String id;
            private String articleContentUrl;
            private String title;
            private String articleTitle;
            private String sourceName;
            private String keyword;
            private String today;
            private String praiseCount;
            private String cssType;
            private String imageProportion;
            private String imgWidth;
            private String commentCount;
            private String imgUrl;
            private String description;
            private String sourceAuthor;
            private String createDate;
            private String recommendDate;
            private String imgHeight;
            private ArticleJumpInfo articleJumpInfo;
            private ArticleSourceBean articleSource;
            private String articleId;
            private String sourceId;
            private String praiseNum;
            private String pubDate;
            private String keywords;
            private String videoTime;
            private String coverUrl;
            private String videoUrl;
            private String producer;
            private String headImgUrl;
            private String desc;
            private int subscribeNum;
            private int articlesNum;

            private List<InfosBean> infos;

            public String getPraiseNum() {
                return praiseNum;
            }

            public void setPraiseNum(String praiseNum) {
                this.praiseNum = praiseNum;
            }

            public int getArticlesNum() {
                return articlesNum;
            }

            public void setArticlesNum(int articlesNum) {
                this.articlesNum = articlesNum;
            }

            public int getSubscribeNum() {
                return subscribeNum;
            }

            public void setSubscribeNum(int subscribeNum) {
                this.subscribeNum = subscribeNum;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getHeadImgUrl() {
                return headImgUrl;
            }

            public void setHeadImgUrl(String headImgUrl) {
                this.headImgUrl = headImgUrl;
            }

            public String getProducer() {
                return producer;
            }

            public void setProducer(String producer) {
                this.producer = producer;
            }

            public String getVideoUrl() {
                return videoUrl;
            }

            public void setVideoUrl(String videoUrl) {
                this.videoUrl = videoUrl;
            }

            public String getCoverUrl() {
                return coverUrl;
            }

            public void setCoverUrl(String coverUrl) {
                this.coverUrl = coverUrl;
            }

            public String getVideoTime() {
                return videoTime;
            }

            public void setVideoTime(String videoTime) {
                this.videoTime = videoTime;
            }

            public String getKeywords() {
                return keywords;
            }

            public void setKeywords(String keywords) {
                this.keywords = keywords;
            }

            public String getPubDate() {
                return pubDate;
            }

            public void setPubDate(String pubDate) {
                this.pubDate = pubDate;
            }

            public String getSourceAuthor() {
                return sourceAuthor;
            }

            public void setSourceAuthor(String sourceAuthor) {
                this.sourceAuthor = sourceAuthor;
            }

            public String getImgWidth() {
                return imgWidth;
            }

            public void setImgWidth(String imgWidth) {
                this.imgWidth = imgWidth;
            }

            public String getCommentCount() {
                return commentCount;
            }

            public void setCommentCount(String commentCount) {
                this.commentCount = commentCount;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public String getRecommendDate() {
                return recommendDate;
            }

            public void setRecommendDate(String recommendDate) {
                this.recommendDate = recommendDate;
            }

            public String getImgHeight() {
                return imgHeight;
            }

            public void setImgHeight(String imgHeight) {
                this.imgHeight = imgHeight;
            }


            public String getImageProportion() {
                return imageProportion;
            }

            public void setImageProportion(String imageProportion) {
                this.imageProportion = imageProportion;
            }

            public String getCssType() {
                return cssType;
            }

            public void setCssType(String cssType) {
                this.cssType = cssType;
            }

            public String getPraiseCount() {
                return praiseCount;
            }

            public void setPraiseCount(String praiseCount) {
                this.praiseCount = praiseCount;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getArticleContentUrl() {
                return articleContentUrl;
            }

            public void setArticleContentUrl(String articleContentUrl) {
                this.articleContentUrl = articleContentUrl;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getArticleTitle() {
                return articleTitle;
            }

            public void setArticleTitle(String articleTitle) {
                this.articleTitle = articleTitle;
            }

            public String getSourceName() {
                return sourceName;
            }

            public void setSourceName(String sourceName) {
                this.sourceName = sourceName;
            }

            public String getKeyword() {
                return keyword;
            }

            public void setKeyword(String keyword) {
                this.keyword = keyword;
            }

            public String getToday() {
                return today;
            }

            public void setToday(String today) {
                this.today = today;
            }

            public ArticleSourceBean getArticleSource() {
                return articleSource;
            }

            public void setArticleSource(ArticleSourceBean articleSource) {
                this.articleSource = articleSource;
            }

            public String getArticleId() {
                return articleId;
            }

            public void setArticleId(String articleId) {
                this.articleId = articleId;
            }

            public String getSourceId() {
                return sourceId;
            }

            public void setSourceId(String sourceId) {
                this.sourceId = sourceId;
            }

            public List<InfosBean> getInfos() {
                return infos;
            }

            public void setInfos(List<InfosBean> infos) {
                this.infos = infos;
            }

            public ArticleJumpInfo getArticleJumpInfo() {
                return articleJumpInfo;
            }

            public void setArticleJumpInfo(ArticleJumpInfo articleJumpInfo) {
                this.articleJumpInfo = articleJumpInfo;
            }

            public static class ArticleJumpInfo implements Serializable {

                private String jumpType;

                public String getJumpType() {
                    return jumpType;
                }

                public void setJumpType(String jumpType) {
                    this.jumpType = jumpType;
                }

            }

            public static class ArticleSourceBean implements Serializable {
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

            public static class InfosBean  implements Serializable{
                private String title;
                private String description;

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }
            }
        }
    }

    public static class ForcusImageListBean  implements Serializable{
        private String id;
        private String subTitle;
        private String forcusImageType;
        private String imageUrl;
        private String requestId;
        private String forcusName;
        private String contentId;
        private String mainTitle;
        private String articleId;

        public String getArticleId() {
            return articleId;
        }

        public void setArticleId(String articleId) {
            this.articleId = articleId;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSubTitle() {
            return subTitle;
        }

        public void setSubTitle(String subTitle) {
            this.subTitle = subTitle;
        }

        public String getForcusImageType() {
            return forcusImageType;
        }

        public void setForcusImageType(String forcusImageType) {
            this.forcusImageType = forcusImageType;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getRequestId() {
            return requestId;
        }

        public void setRequestId(String requestId) {
            this.requestId = requestId;
        }

        public String getForcusName() {
            return forcusName;
        }

        public void setForcusName(String forcusName) {
            this.forcusName = forcusName;
        }

        public String getContentId() {
            return contentId;
        }

        public void setContentId(String contentId) {
            this.contentId = contentId;
        }

        public String getMainTitle() {
            return mainTitle;
        }

        public void setMainTitle(String mainTitle) {
            this.mainTitle = mainTitle;
        }
    }
}
