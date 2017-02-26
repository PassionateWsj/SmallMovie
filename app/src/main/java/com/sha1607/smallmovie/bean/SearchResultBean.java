package com.sha1607.smallmovie.bean;

import java.util.List;

/**
 * ****************************************************
 * author: JamesWong
 * created on: 16/10/28 上午10:32
 * e-mail: PassinateWsj@outlook.com
 * name:
 * desc:
 * ****************************************************
 */

public class SearchResultBean {

    /**
     * total : 3
     * hits : [{"_type":"articletype","_id":"93f32c4d1c6f416db313fcf89b09ce93","_source":{"id":"93f32c4d1c6f416db313fcf89b09ce93","pubDate":"2016-03-26T17:08:52+0800","title":"玩A4腰关你们屁事，你们还不是在玩屁S","contentText":"蛇精脸，九头身，马甲线，夹笔胸，A4腰\u2026\u2026比脸比身材这股风到底什么时候才能过去？毕竟作为一个体重与国际接轨的人，我只敢在Instagram上聊这个话题。当A4腰这把火烧到歪果仁那里时，"},"_index":"lfstindex","_score":1.731064,"highlight":{"title":["玩<red>A<\/red>4腰关你们屁事，你们还不是在玩屁S"]}},{"_type":"articletype","_id":"8e7bd4eb0dd040fd9894190d2d13c641","_source":{"id":"8e7bd4eb0dd040fd9894190d2d13c641","pubDate":"2016-01-12T20:45:07+0800","title":"从A到Z带你回顾David Bowie的电影往事","contentText":"> 此文本是生日贺文，奈何生死一刹那\u2026\u2026让我们细数往事，怀缅宝爷的一生风骚。**\u201c我生来就是明星，只要加水，搅拌。\u201d\u2014\u2014David Bowie**要说地球上最风骚的人都有谁"},"_index":"lfstindex","_score":1.6595887,"highlight":{"title":["从<red>A<\/red>到Z带你回顾David Bowie的电影往事"]}},{"_type":"articletype","_id":"8a6af50669b14850aaf9e542aeea5476","_source":{"id":"8a6af50669b14850aaf9e542aeea5476","pubDate":"2016-03-08T17:08:54+0800","title":"电影《捉贼记》（To Catch a Thief）时尚解析","contentText":"导演希区柯克（Alfred Hitchcock）对金发女郎的情有独钟众所皆知，在他的电影里，女主角的形象几乎有着不约而同的标准：冷若冰霜、若即若离，让人难以触摸。提起希女郎中的佼佼者，中外公认最有代表"},"_index":"lfstindex","_score":1.6539654,"highlight":{"title":["电影《捉贼记》（To Catch <red>a<\/red> Thief）时尚解析"]}}]
     * retCode : 0
     * count : 20
     * start : 0
     * requestId : aa6e5539-628a-4cc2-b1eb-147d2b42f684
     * retMsg :
     * max_score : 1.731064
     */

    private int total;
    private int retCode;
    private int count;
    private int start;
    private String requestId;
    private String retMsg;
    private double max_score;
    /**
     * _type : articletype
     * _id : 93f32c4d1c6f416db313fcf89b09ce93
     * _source : {"id":"93f32c4d1c6f416db313fcf89b09ce93","pubDate":"2016-03-26T17:08:52+0800","title":"玩A4腰关你们屁事，你们还不是在玩屁S","contentText":"蛇精脸，九头身，马甲线，夹笔胸，A4腰\u2026\u2026比脸比身材这股风到底什么时候才能过去？毕竟作为一个体重与国际接轨的人，我只敢在Instagram上聊这个话题。当A4腰这把火烧到歪果仁那里时，"}
     * _index : lfstindex
     * _score : 1.731064
     * highlight : {"title":["玩<red>A<\/red>4腰关你们屁事，你们还不是在玩屁S"]}
     */

    private List<HitsBean> hits;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getRetCode() {
        return retCode;
    }

    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
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

    public double getMax_score() {
        return max_score;
    }

    public void setMax_score(double max_score) {
        this.max_score = max_score;
    }

    public List<HitsBean> getHits() {
        return hits;
    }

    public void setHits(List<HitsBean> hits) {
        this.hits = hits;
    }

    public static class HitsBean {

        private String _type;
        private String _id;
        /**
         * id : 93f32c4d1c6f416db313fcf89b09ce93
         * pubDate : 2016-03-26T17:08:52+0800
         * title : 玩A4腰关你们屁事，你们还不是在玩屁S
         * contentText : 蛇精脸，九头身，马甲线，夹笔胸，A4腰……比脸比身材这股风到底什么时候才能过去？毕竟作为一个体重与国际接轨的人，我只敢在Instagram上聊这个话题。当A4腰这把火烧到歪果仁那里时，
         */

        private SourceBean _source;
        private String _index;
        private double _score;
        private HighlightBean highlight;

        public String get_type() {
            return _type;
        }

        public void set_type(String _type) {
            this._type = _type;
        }

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public SourceBean get_source() {
            return _source;
        }

        public void set_source(SourceBean _source) {
            this._source = _source;
        }

        public String get_index() {
            return _index;
        }

        public void set_index(String _index) {
            this._index = _index;
        }

        public double get_score() {
            return _score;
        }

        public void set_score(double _score) {
            this._score = _score;
        }

        public HighlightBean getHighlight() {
            return highlight;
        }

        public void setHighlight(HighlightBean highlight) {
            this.highlight = highlight;
        }

        public static class SourceBean {
            private String id;
            private String pubDate;
            private String title;
            private String contentText;
            private String[] genres;
            private String alt;
            private String subtype;
            private List<CastsBean> casts;
            private String douban_id;
            private List<Directors> directors;
            private ImageBean images;

            public List<CastsBean> getCasts() {
                return casts;
            }

            public void setCasts(List<CastsBean> casts) {
                this.casts = casts;
            }

            public List<Directors> getDirectors() {
                return directors;
            }

            public void setDirectors(List<Directors> directors) {
                this.directors = directors;
            }

            public ImageBean getImages() {
                return images;
            }

            public void setImages(ImageBean images) {
                this.images = images;
            }


            public String getAlt() {
                return alt;
            }

            public void setAlt(String alt) {
                this.alt = alt;
            }

            public String getDouban_id() {
                return douban_id;
            }

            public void setDouban_id(String douban_id) {
                this.douban_id = douban_id;
            }

            public String[] getGenres() {
                return genres;
            }

            public void setGenres(String[] genres) {
                this.genres = genres;
            }

            public String getSubtype() {
                return subtype;
            }

            public void setSubtype(String subtype) {
                this.subtype = subtype;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getPubDate() {
                return pubDate;
            }

            public void setPubDate(String pubDate) {
                this.pubDate = pubDate;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContentText() {
                return contentText;
            }

            public void setContentText(String contentText) {
                this.contentText = contentText;
            }

            public static class ImageBean {
                private String small;
                private String medium;
                private String large;

                public String getLarge() {
                    return large;
                }

                public void setLarge(String large) {
                    this.large = large;
                }

                public String getMedium() {
                    return medium;
                }

                public void setMedium(String medium) {
                    this.medium = medium;
                }

                public String getSmall() {
                    return small;
                }

                public void setSmall(String small) {
                    this.small = small;
                }
            }

            public class Directors {
                private String name;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }

            public class CastsBean {
                private String name;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }
        }

        public static class HighlightBean {
            private List<String> title;

            public List<String> getTitle() {
                return title;
            }

            public void setTitle(List<String> title) {
                this.title = title;
            }
        }
    }
}
