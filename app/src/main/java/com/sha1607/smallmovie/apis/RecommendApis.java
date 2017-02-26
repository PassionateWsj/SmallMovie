package com.sha1607.smallmovie.apis;

/*
 **********************************
 * author: 胡佳俊
 * time: 2016/10/26 下午 -3:48
 * name:
 * desc:
 * step:
 **********************************
 */

import com.sha1607.smallmovie.bean.AllTopicListBean;
import com.sha1607.smallmovie.bean.AuthorBean;
import com.sha1607.smallmovie.bean.CategoryListBean;
import com.sha1607.smallmovie.bean.ContentFilmBean;
import com.sha1607.smallmovie.bean.DiscoverListBean;
import com.sha1607.smallmovie.bean.MovieCommentsBean;
import com.sha1607.smallmovie.bean.MovieDetailBean;
import com.sha1607.smallmovie.bean.RecommandVideoBean;
import com.sha1607.smallmovie.bean.RecommandVideoCommentBean;
import com.sha1607.smallmovie.bean.RecommendListBean;
import com.sha1607.smallmovie.bean.SearchResultBean;
import com.sha1607.smallmovie.bean.SourceBean;

import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

public interface RecommendApis {
    String HOST = "http://www.moviebase.cn/uread/app/";

    @GET("recommend/recommend?pageContext=1&platform=2&naviId=2&channelId=1005&appVersion=1.7.0&deviceId=A69955C048730AB54857C04E67D23A47")
    Observable<RecommendListBean> getRecommendList();

    @GET("category/categoryList")
    Observable<CategoryListBean> getCategotyList();

    String CONTENT_URL = "http://www.moviebase.cn/uread/app/topic/topicDetail/articleList?pageContext=1&platform=2&appVersion=1.7.0&deviceId=A69955C048730AB54857C04E67D23A47&topicId=%s";

    @GET
    Observable<ContentFilmBean> getContentList(@Url String url);

    String ALLTOPICLIST_URL = "http://www.moviebase.cn/uread/app/topic/topicList?appVersion=1.7.0&pageContext=%d";

    @GET
    Observable<AllTopicListBean> getAllTopicList(@Url String url);

    String SEARCH_URL = "http://www.moviebase.cn/uread/app/%s/search?platform=2&appVersion=1.7.0&deviceId=A69955C048730AB54857C04E67D23A47&start=%d&query=%s";

    @GET
    Observable<SearchResultBean> getSearchBean(@Url String url);

    String MOVIE_DETAIL_URL = "http://www.moviebase.cn/uread/app/film/filmResource?appVersion=1.7.0&id=%s";

    @GET
    Observable<MovieDetailBean> getMovieDetailBean(@Url String url);

    String MOVIE_COMMENTS_URL = "http://www.moviebase.cn/uread/app/film/filmResourceComments?pageContext=%d&platform=2&appVersion=1.7.0&id=%s";

    @GET
    Observable<MovieCommentsBean> getMovieCommentsBean(@Url String url);

    String RECOMMAND_VIDEO = "http://www.moviebase.cn/uread/app/video/cmsVideo/%s";

    @GET
    Observable<RecommandVideoBean> getRecommandVideo(@Url String url);

    String VIDEO_COMMENT = "http://www.moviebase.cn/uread/app/comment/userComment?contentType=2&contentId=%s";

    @GET
    Observable<RecommandVideoCommentBean> getVidemoComment(@Url String url);

    String DISCOVE_LIST = "http://www.moviebase.cn/uread/app/film/recommendList?pageContext=%d&platform=2&appVersion=1.7.0&deviceId=A69955C048730AB54857C04E67D23A47";
    @GET
    Observable<DiscoverListBean> getDiscoverList(@Url String url);


    String SOURCE_LIST="http://www.moviebase.cn/uread/app/source/sourceDetail?pageContext=%d&platform=2&resolutionWidth=720&sysver=4.4.2&channelId=1004&appVersion=1.7.0&versionCode=1070&osType=2&resolutionHeight=1280&deviceId=E5ACBE3A81735AC73BD55D9372D4EA6F&sourceId=%s";
    @GET
    Observable<SourceBean> getSorurceList(@Url String url);

    String AUTHOR_LIST="http://www.moviebase.cn/uread/app/source/authorArtList?pageContext=%d&platform=2&resolutionWidth=720&sysver=4.4.2&channelId=1005&appVersion=1.7.0&versionCode=1070&osType=2&resolutionHeight=1280&deviceId=E5ACBE3A81735AC73BD55D9372D4EA6F&authorId=%s";
    @GET
    Observable<AuthorBean> getAuthorList(@Url String url);
}
