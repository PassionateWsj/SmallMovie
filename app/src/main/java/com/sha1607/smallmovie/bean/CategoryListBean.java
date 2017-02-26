package com.sha1607.smallmovie.bean;

import java.io.Serializable;
import java.util.List;

/**
 * ***********************************************************
 * author: crane
 * time: 2016/10/26 9:41
 * name:
 * desc:
 * step:
 * ***********************************************************
 */


public class CategoryListBean {

    /**
     * topicRecommendList : [{"headImgUrl":"http://img.moviebase.cn/img/topic/2016/10/a5bd75c35c8748068aa9ea066fa3a80a.jpeg@461w","imgUrl":"http://img.moviebase.cn/img/topic/2016/10/acfeb712f6c1426f9c7ebcc21fc77983.jpeg@!1080","id":"9da57ada526e4a58b1b553fc803a731c","title":"一个好故事","desc":"几分钟看一个好故事，过一把别人的人生","recommendImgUrl":"http://img.moviebase.cn/img/topic/2016/10/a5bd75c35c8748068aa9ea066fa3a80a.jpeg@465w","isSubscribe":0,"subscribeNum":48,"articlesNum":9},{"headImgUrl":"http://img.moviebase.cn/img/topic/2016/10/d9f3fb4f989049bb8029ca790d73b61f.jpeg@461w","imgUrl":"http://img.moviebase.cn/img/topic/2016/10/f68675aa2eb845e5995bf94c76cedbf8.jpeg@!1080","id":"681a4e153f3343c3bc73c1467b2f941c","title":"趣味视频","desc":"脑洞大不大？蒙太奇说话","recommendImgUrl":"http://img.moviebase.cn/img/topic/2016/10/d9f3fb4f989049bb8029ca790d73b61f.jpeg@465w","isSubscribe":0,"subscribeNum":2431,"articlesNum":190},{"headImgUrl":"http://img.moviebase.cn/img/topic/2016/10/a0a5e76b2855489e97d815d573d2aa5e.jpeg@461w","imgUrl":"http://img.moviebase.cn/img/topic/2016/10/11a1412c9a364b1dbfb51fa180170232.jpeg@!1080","id":"f9bc542661e64306916f84bbe658d19f","title":"热映热评","desc":"新鲜热门的影视评论","recommendImgUrl":"http://img.moviebase.cn/img/topic/2016/10/a0a5e76b2855489e97d815d573d2aa5e.jpeg@465w","isSubscribe":0,"subscribeNum":850,"articlesNum":248},{"headImgUrl":"http://img.moviebase.cn/img/topic/2016/08/e61a39747f054da6ae8901383ceb42ab.jpeg@461w","imgUrl":"http://img.moviebase.cn/img/topic/2016/08/bf57acc91f8d4d7e9821797ea13b8a53.jpeg@!1080","id":"6553349b7f414fc5937310f4ad617388","title":"巴塞专访","desc":"巴塞电影出品","recommendImgUrl":"http://img.moviebase.cn/img/topic/2016/08/e61a39747f054da6ae8901383ceb42ab.jpeg@465w","isSubscribe":0,"subscribeNum":310,"articlesNum":20},{"headImgUrl":"http://img.moviebase.cn/img/topic/2016/10/ffdd6c3acdb346138b5a281ee30ac2b7.jpeg@461w","imgUrl":"http://img.moviebase.cn/img/topic/2016/10/dd979ccec03e4066a476897d514aa6e6.jpeg@!1080","id":"553e103c29a645279bee88f41745585c","title":"经典重温","desc":"","recommendImgUrl":"http://img.moviebase.cn/img/topic/2016/10/ffdd6c3acdb346138b5a281ee30ac2b7.jpeg@465w","isSubscribe":0,"subscribeNum":1400,"articlesNum":164},{"headImgUrl":"http://img.moviebase.cn/img/topic/2016/10/c447f7eff4b243cf9081a3d2705f9821.jpeg@461w","imgUrl":"http://img.moviebase.cn/img/topic/2016/10/399458dc385f4b579d3c1359ae83f06e.jpeg@!1080","id":"c5ffb5c41b8d4918832fd1010eaf4c01","title":"台词金句","desc":"一句击中你的影视金句","recommendImgUrl":"http://img.moviebase.cn/img/topic/2016/10/c447f7eff4b243cf9081a3d2705f9821.jpeg@465w","isSubscribe":0,"subscribeNum":1932,"articlesNum":42}]
     * retCode : 0
     * topicSubscribeUpdateNum : 0
     * topicSubscribeUpdateList :
     * requestId : 0b41dbfc-a6a6-4c32-904b-47e297e9d15d
     * retMsg :
     * sourceList : []
     * articleList : [{"id":"722725d309c44a81bfb6edacf72c3d5e","articleContentUrl":"http://www.moviebase.cn/uread/app/viewArt/viewArt-722725d309c44a81bfb6edacf72c3d5e.html?appVersion=1.7.0","praiseCount":"47","title":"香港电影中的小人物，温情的故事最动人","sourceName":"巴塞电影","image":"http://img.moviebase.cn/img/poster/2016/10/639199d1ea8d4516a3ac9d2146878390.jpeg@353w","articleUrl":"http://www.moviebase.cn/uread/app/viewArt/viewArt-722725d309c44a81bfb6edacf72c3d5e.html?appVersion=1.7.0","sourceId":"19f2cd89ca904eb69e51f7d6e8a1e679"},{"id":"b5e6c6acc10f424980796f61a6ef060a","articleContentUrl":"http://www.moviebase.cn/uread/app/viewArt/viewArt-b5e6c6acc10f424980796f61a6ef060a.html?appVersion=1.7.0","praiseCount":"43","title":"还在骂郭敬明？不是CG技术比不上好莱坞，这是一个心理学问题","sourceName":"每经影视","image":"http://img.moviebase.cn/img/poster/2016/10/e59216ad605d4deab659e846ae9b90cb.jpeg@353w","articleUrl":"http://www.moviebase.cn/uread/app/viewArt/viewArt-b5e6c6acc10f424980796f61a6ef060a.html?appVersion=1.7.0","sourceId":"14846f1dcdb7487898c6e3cb5ae49f6f"},{"id":"1ebf826a8583429887013d091a053bdb","articleContentUrl":"http://www.moviebase.cn/uread/app/viewArt/viewArt-1ebf826a8583429887013d091a053bdb.html?appVersion=1.7.0","praiseCount":"57","title":"二百万成本16天拍完，曾志伟余文乐\"零片酬\"出演！","sourceName":"香港电影","image":"http://img.moviebase.cn/img/poster/2016/09/d514ae48e3924175a27cf4c1f8ab3ab4.jpeg@353w","articleUrl":"http://www.moviebase.cn/uread/app/viewArt/viewArt-1ebf826a8583429887013d091a053bdb.html?appVersion=1.7.0","sourceId":"3e67f0e1c9784bb0b4bd03e6ae3686f5"},{"id":"c5494b38762f4dc0ba860be0fabc743f","articleContentUrl":"http://www.moviebase.cn/uread/app/viewArt/viewArt-c5494b38762f4dc0ba860be0fabc743f.html?appVersion=1.7.0","praiseCount":"27","title":"还记得这位像漫画少年一样的\u201c浩南哥\u201d吗？","sourceName":"巴塞电影","image":"http://img.moviebase.cn/img/poster/2016/09/1aaabce5f8734873bd225b5e8b5878fc.jpeg@353w","articleUrl":"http://www.moviebase.cn/uread/app/viewArt/viewArt-c5494b38762f4dc0ba860be0fabc743f.html?appVersion=1.7.0","sourceId":"19f2cd89ca904eb69e51f7d6e8a1e679"},{"id":"afd4354d0cf549c689435bd6be564df1","articleContentUrl":"http://www.moviebase.cn/uread/app/viewArt/viewArt-afd4354d0cf549c689435bd6be564df1.html?appVersion=1.7.0","praiseCount":"49","title":"回望阿巴斯：电影始于格里菲斯，止于阿巴斯","sourceName":"巴塞电影","image":"http://img.moviebase.cn/img/poster/2016/09/762ef5092c7049d2a518f636585a565e.jpeg@353w","articleUrl":"http://www.moviebase.cn/uread/app/viewArt/viewArt-afd4354d0cf549c689435bd6be564df1.html?appVersion=1.7.0","sourceId":"19f2cd89ca904eb69e51f7d6e8a1e679"},{"id":"2b6758f7d7fb4cf393e68e14cdd5b5b2","articleContentUrl":"http://www.moviebase.cn/uread/app/viewArt/viewArt-2b6758f7d7fb4cf393e68e14cdd5b5b2.html?appVersion=1.7.0","praiseCount":"37","title":"为电影奋斗的人：在台北听李安谈电影","sourceName":"巴塞电影","image":"http://img.moviebase.cn/img/poster/2016/10/5141764be2384604adf5beb68c9c07a3.jpeg@353w","articleUrl":"http://www.moviebase.cn/uread/app/viewArt/viewArt-2b6758f7d7fb4cf393e68e14cdd5b5b2.html?appVersion=1.7.0","sourceId":"19f2cd89ca904eb69e51f7d6e8a1e679"},{"id":"d5684520b144404faa0dad6db77a9459","articleContentUrl":"http://www.moviebase.cn/uread/app/viewArt/viewArt-d5684520b144404faa0dad6db77a9459.html?appVersion=1.7.0","praiseCount":"19","title":"这部30年前的国产奇片，仅出现过一次，便成绝响","sourceName":"影画志","image":"http://img.moviebase.cn/img/poster/2016/09/eb8cbc429adb4110bb4dbf06c38a1446.jpeg@353w","articleUrl":"http://www.moviebase.cn/uread/app/viewArt/viewArt-d5684520b144404faa0dad6db77a9459.html?appVersion=1.7.0","sourceId":"858f358fbd3641338f3ee1070bdc39c6"},{"id":"f8cc88120ca84411890c1fe2dfdf5981","articleContentUrl":"http://www.moviebase.cn/uread/app/viewArt/viewArt-f8cc88120ca84411890c1fe2dfdf5981.html?appVersion=1.7.0","praiseCount":"55","title":"日本电影黄金时代的20部最佳作品","sourceName":"巴塞电影","image":"http://img.moviebase.cn/img/poster/2016/09/214ae4c8e9e943eb90eeee092b5e2f28.jpeg@353w","articleUrl":"http://www.moviebase.cn/uread/app/viewArt/viewArt-f8cc88120ca84411890c1fe2dfdf5981.html?appVersion=1.7.0","sourceId":"19f2cd89ca904eb69e51f7d6e8a1e679"},{"id":"d45dd32b122e42f6a2d1d5ee19bbf87a","articleContentUrl":"http://www.moviebase.cn/uread/app/viewArt/viewArt-d45dd32b122e42f6a2d1d5ee19bbf87a.html?appVersion=1.7.0","praiseCount":"98","title":"不能有鬼，不能有同性恋，不能有裸露镜头：在中国要怎么拍电影？","sourceName":"巴塞电影","image":"http://img.moviebase.cn/img/poster/2016/09/2e15ae417a7f4ec99cab8824362da654.jpeg@353w","articleUrl":"http://www.moviebase.cn/uread/app/viewArt/viewArt-d45dd32b122e42f6a2d1d5ee19bbf87a.html?appVersion=1.7.0","sourceId":"19f2cd89ca904eb69e51f7d6e8a1e679"},{"id":"be136efff3db4e85a5c5f5008043fdb0","articleContentUrl":"http://www.moviebase.cn/uread/app/viewArt/viewArt-be136efff3db4e85a5c5f5008043fdb0.html?appVersion=1.7.0","praiseCount":"18","title":"生活如此艰难，你需要一部让人发笑的电影","sourceName":"巴塞电影","image":"http://img.moviebase.cn/img/poster/2016/09/95218a96518b4c4fbdaad865c70342b1.jpeg@353w","articleUrl":"http://www.moviebase.cn/uread/app/viewArt/viewArt-be136efff3db4e85a5c5f5008043fdb0.html?appVersion=1.7.0","sourceId":"19f2cd89ca904eb69e51f7d6e8a1e679"}]
     * articleSubscribeUpdateNum : 0
     */

    private int retCode;
    private int topicSubscribeUpdateNum;
    private String topicSubscribeUpdateList;
    private String requestId;
    private String retMsg;
    private int articleSubscribeUpdateNum;
    /**
     * headImgUrl : http://img.moviebase.cn/img/topic/2016/10/a5bd75c35c8748068aa9ea066fa3a80a.jpeg@461w
     * imgUrl : http://img.moviebase.cn/img/topic/2016/10/acfeb712f6c1426f9c7ebcc21fc77983.jpeg@!1080
     * id : 9da57ada526e4a58b1b553fc803a731c
     * title : 一个好故事
     * desc : 几分钟看一个好故事，过一把别人的人生
     * recommendImgUrl : http://img.moviebase.cn/img/topic/2016/10/a5bd75c35c8748068aa9ea066fa3a80a.jpeg@465w
     * isSubscribe : 0
     * subscribeNum : 48
     * articlesNum : 9
     */

    private List<TopicRecommendListBean> topicRecommendList;
    private List<?> sourceList;
    /**
     * id : 722725d309c44a81bfb6edacf72c3d5e
     * articleContentUrl : http://www.moviebase.cn/uread/app/viewArt/viewArt-722725d309c44a81bfb6edacf72c3d5e.html?appVersion=1.7.0
     * praiseCount : 47
     * title : 香港电影中的小人物，温情的故事最动人
     * sourceName : 巴塞电影
     * image : http://img.moviebase.cn/img/poster/2016/10/639199d1ea8d4516a3ac9d2146878390.jpeg@353w
     * articleUrl : http://www.moviebase.cn/uread/app/viewArt/viewArt-722725d309c44a81bfb6edacf72c3d5e.html?appVersion=1.7.0
     * sourceId : 19f2cd89ca904eb69e51f7d6e8a1e679
     */

    private List<ArticleListBean> articleList;

    public int getRetCode() {
        return retCode;
    }

    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }

    public int getTopicSubscribeUpdateNum() {
        return topicSubscribeUpdateNum;
    }

    public void setTopicSubscribeUpdateNum(int topicSubscribeUpdateNum) {
        this.topicSubscribeUpdateNum = topicSubscribeUpdateNum;
    }

    public String getTopicSubscribeUpdateList() {
        return topicSubscribeUpdateList;
    }

    public void setTopicSubscribeUpdateList(String topicSubscribeUpdateList) {
        this.topicSubscribeUpdateList = topicSubscribeUpdateList;
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

    public int getArticleSubscribeUpdateNum() {
        return articleSubscribeUpdateNum;
    }

    public void setArticleSubscribeUpdateNum(int articleSubscribeUpdateNum) {
        this.articleSubscribeUpdateNum = articleSubscribeUpdateNum;
    }

    public List<TopicRecommendListBean> getTopicRecommendList() {
        return topicRecommendList;
    }

    public void setTopicRecommendList(List<TopicRecommendListBean> topicRecommendList) {
        this.topicRecommendList = topicRecommendList;
    }

    public List<?> getSourceList() {
        return sourceList;
    }

    public void setSourceList(List<?> sourceList) {
        this.sourceList = sourceList;
    }

    public List<ArticleListBean> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<ArticleListBean> articleList) {
        this.articleList = articleList;
    }

    public static class TopicRecommendListBean implements Serializable{
        private String headImgUrl;
        private String imgUrl;
        private String id;
        private String title;
        private String desc;
        private String recommendImgUrl;
        private int isSubscribe;
        private int subscribeNum;
        private int articlesNum;

        public String getHeadImgUrl() {
            return headImgUrl;
        }

        public void setHeadImgUrl(String headImgUrl) {
            this.headImgUrl = headImgUrl;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getRecommendImgUrl() {
            return recommendImgUrl;
        }

        public void setRecommendImgUrl(String recommendImgUrl) {
            this.recommendImgUrl = recommendImgUrl;
        }

        public int getIsSubscribe() {
            return isSubscribe;
        }

        public void setIsSubscribe(int isSubscribe) {
            this.isSubscribe = isSubscribe;
        }

        public int getSubscribeNum() {
            return subscribeNum;
        }

        public void setSubscribeNum(int subscribeNum) {
            this.subscribeNum = subscribeNum;
        }

        public int getArticlesNum() {
            return articlesNum;
        }

        public void setArticlesNum(int articlesNum) {
            this.articlesNum = articlesNum;
        }
    }

    public static class ArticleListBean {
        private String id;
        private String articleContentUrl;
        private String praiseCount;
        private String title;
        private String sourceName;
        private String image;
        private String articleUrl;
        private String sourceId;

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

        public String getPraiseCount() {
            return praiseCount;
        }

        public void setPraiseCount(String praiseCount) {
            this.praiseCount = praiseCount;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSourceName() {
            return sourceName;
        }

        public void setSourceName(String sourceName) {
            this.sourceName = sourceName;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getArticleUrl() {
            return articleUrl;
        }

        public void setArticleUrl(String articleUrl) {
            this.articleUrl = articleUrl;
        }

        public String getSourceId() {
            return sourceId;
        }

        public void setSourceId(String sourceId) {
            this.sourceId = sourceId;
        }
    }
}
