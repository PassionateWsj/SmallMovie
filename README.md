### 作品展示
这是之前做过的App，正好还留着录下的gif，太大了有点卡，加载一会就流畅了。后面也有下载链接，可以下载下来看，可能会耽误您几分钟时间，请您谅解。
- ![](https://github.com/PassionateWsj/Test2/blob/master/app/src/main/assets/%E5%B0%8F%E7%94%B5%E5%BD%B1%E5%B1%8F%E5%B9%95%E5%BD%95%E5%88%B6.gif)
- https://github.com/PassionateWsj/Test2/blob/master/app/src/main/assets/%E5%B0%8F%E7%94%B5%E5%BD%B1%E5%B1%8F%E5%B9%95%E5%BD%95%E5%88%B6.gif


# 小电影接口文档
---
## 主界面
###基本url：

>http://www.moviebase.cn/uread/app/%s?pageContext=1&platform=2&appVersion=1.7.0&deviceId=A69955C048730AB54857C04E67D23A47

- 推荐： recommend/recommend
- 分类： category/categoryList
- 发现： film/recommendList
	
1. **推荐**界面url（包括**广告条**、item是**多类型**），其中object字段的bean对象要自己写
	- **内容页**是以WebView形式加载（objectType）
		- 1（普通item）：articleContentUrl
		- 2（视频）：cmsVideo/后面id	
			- 视频上半内容
		> http://www.moviebase.cn/uread/app/video/cmsVideo/%s

			- 文章评论(根据id):末尾+id
			>http://www.moviebase.cn/uread/app/comment/userComment?contentType=2&contentId=%s

		- 4（第一个item）：articleContentUrl
		- 5（订阅、根据id）：topicId=id
		 > http://www.moviebase.cn/uread/app/topic/topicDetail/articleList?pageContext=1&platform=2&appVersion=1.7.0&deviceId=A69955C048730AB54857C04E67D23A47&topicId=%s

2. **分类**界面url(包括**专题**、**热门文章**)
	1.  专题内容详情（根据id）：topicId=id
		>http://www.moviebase.cn/uread/app/topic/topicDetail/articleList?pageContext=1&platform=2&appVersion=1.7.0&deviceId=A69955C048730AB54857C04E67D23A47&topicId=%s

		- 视频：cmsVideo/后面id	
			> http://www.moviebase.cn/uread/app/video/cmsVideo/%s

		- 文章评论(根据id):末尾+id
			>http://www.moviebase.cn/uread/app/comment/userComment?contentType=2&contentId=%s
	
	2. 专题-查看全部
	>http://www.moviebase.cn/uread/app/topic/topicList?pageContext=1&appVersion=1.7.0
	
	2. 热门文章：articleContentUrl
	3. 搜索(分类型app后面article/movie)：query=???
		>http://www.moviebase.cn/uread/app/%s/search?platform=2&appVersion=1.7.0&deviceId=A69955C048730AB54857C04E67D23A47&query=%s
		- 文章viewArt后加_id
		>http://www.moviebase.cn/uread/app/viewArt/viewArt-%s.html
		- 电影:根据id，参照发现界面

3. **发现界面**url
	- 有分页加载：根据pageContext=%d

		- 点击电影的详情区域：根据filmResourcesId=id
	>http://www.moviebase.cn/uread/app/film/filmResource?appVersion=1.7.0&id=AVblI8DpBE2xqBHKFmfT
	
		- 点击电影的评论：根据filmResourcesId=id
	>http://www.moviebase.cn/uread/app/film/filmResourceComments?pageContext=1&platform=2&appVersion=1.7.0&id=%d



