package com.ifreedomer.cplus.entity;

import java.io.Serializable;

public class BlogContentInfo implements Serializable {

    /**
     * Description : 写在前面的话传递参数的行为对于现在编程语言来说，再寻常不过的概念参数（英语：parameter）是使用通用变量来建立函数和变量之间关系（当这种关系很难用方程来阐述时）的一个数量。-来自wikipedia先来看一个例子：#来源于https://docs.python.org/2/library/itertools.htmldefchain(*iterables):	for...
     * CommentAuth : 2
     * OutlinkCount : 0
     * FileName :
     * ChannelId : 16
     * is_digg : false
     * IsTop : false
     * AvatarUrl : https://avatar.csdn.net/A/C/7/3_aa375809600.jpg
     * PostTime : 2018-11-27 21:28:57
     * ArticleId : 84573948
     * ArticleUrl : https://blog.csdn.net/aa375809600/article/details/84573948
     * Status : 1
     * UserName : aa375809600
     * Digg : 3
     * Bury : 0
     * IP : 106.39.150.116
     * Title : 九、Python的可变参数
     * ViewCount : 151
     * NickName : 怪叔叔萝莉控
     * TypeCopy : 0
     * TextBody : <!DOCTYPE html>
     <html>
     <head>
     <meta charset="utf-8">
     <meta http-equiv="content-type" content="text/html; charset=utf-8">
     <meta http-equiv="X-UA-Compatible" content="IE=Edge">
     <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
     <meta name="apple-mobile-web-app-status-bar-style" content="black">
     <meta content="yes" name="apple-mobile-web-app-capable">
     <meta content="black" name="apple-mobile-web-app-status-bar-style">
     <meta content="telephone=no" name="format-detection">
     <meta content="email=no" name="format-detection">
     <meta name="referrer" content="always">
     <title>九、Python的可变参数 - CSDN博客</title>
     <link href="https://csdnimg.cn/public/favicon.ico" rel="SHORTCUT ICON">
     <link rel="stylesheet" href="https://csdnimg.cn/release/phoenix/production/app_detail_article-f90f9f6169.css">
     <style>
     .MathJax_Preview{
     display: none !important;
     }
     </style>
     <script>
     //h5定义rem
     initpage();
     window.onresize = initpage;
     function initpage(){
     var view_width = document.getElementsByTagName('html')[0].getBoundingClientRect().width;
     var _html = document.getElementsByTagName('html')[0];
     window.fontSize = view_width>750?750/7.5:view_width/7.5;
     _html.style.fontSize= window.fontSize+'px';
     }
     </script>
     </head>
     <body class="">
     <!-- flowchart 箭头图标 勿删 -->
     <svg xmlns="http://www.w3.org/2000/svg" style="display: none;"><path stroke-linecap="round" d="M5,0 0,2.5 5,5z" id="raphael-marker-block" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></path></svg>
     <div class="article_content ">

     <link href="https://csdnimg.cn/release/phoenix/mdeditor/markdown_views-7b4cdcb592.css" rel="stylesheet">
     <link rel="stylesheet" href="https://csdnimg.cn/release/phoenix/production/wapedit_views_md-80a94b72a1.css" />
     <div id="content_views" class="markdown_views  prism-atom-one-dark">
     <h3><a id="_0"></a>写在前面的话</h3>
     <p>传递参数的行为对于现在编程语言来说，再寻常不过的概念</p>
     <blockquote>
     <p>参数（英语：parameter）是使用通用变量来建立函数和变量之间关系（当这种关系很难用方程来阐述时）的一个数量。 - 来自 wikipedia</p>
     </blockquote>
     <p>先来看一个例子：</p>
     <pre><code># 来源于 https://docs.python.org/2/library/itertools.html
     def chain(*iterables):
     for it in iterables:
     for element in it:
     yield element
     </code></pre>
     <p>大家可能注意到 <code>*iterables</code> 了，对了，就是他， <strong>不定参数</strong>。</p>
     <pre><code>&gt;&gt;&gt; from itertool import chain
     &gt;&gt;&gt; chan([1,2], [2, 3])				# 你可以这么用
     [1, 2, 2, 3]
     &gt;&gt;&gt; chan([1,2], [2, 3], [4, 5])		# 你还可以这么用
     [1, 2, 2, 3, 4, 5]
     &gt;&gt;&gt; chan([1,2], [2, 3], [4, 5])		# 你也可以这么用
     [1, 2, 2, 3, 4, 5]
     ...	// 随心所欲的加参数
     </code></pre>
     <p>很神奇把，(<sup>_</sup>)v，来讲讲枯燥的概念把！</p>
     <p>可能很多人用了几年的 Python 都没真正使用过可变参数，就比如我，为了学写通用模块，就会对它有需求；或许你经常看 Python 模块库代码，会发现很多函数的参数定义，都会跟上 <code>*args</code> 和 <code>**kwargs</code>（不定参数的另一种形式，后面会讲到）。</p>
     <blockquote>
     <p>在计算机程序设计，一个可变参数函数是指一个函数拥有不定引数，即是它接受一个可变量目的参数。 - 来自 wikipedia</p>
     </blockquote>
     <p>通俗的说就是，函数可以处理不同数量的参数。</p>
     <p>在我看来，几乎80%的使用可变参数列表的场景，都可以使用数组和字典来解决。但是使用可变参数列表的函数可以提供一种数组和字典无法提供的东西：<strong>优雅</strong>。</p>
     <h3><a id="args_39"></a>*args</h3>
     <pre><code>def argsFunc(a, *args):
     print a
     print args

     &gt;&gt;&gt; argsFunc(1, 2, 3, 4)
     1
     (2, 3, 4)
     </code></pre>
     <p><code>argsFunc</code> 中匹配完定义好的参数，<strong>剩余的参数以元组的形式存储在 args</strong>（args 名称你可以自行定义），因此在上述程序中只要你传入不小于 1 个参数，该函数都会接受，当然你也可以直接定义只接受可变参数，你就可以自由传递你的参数:</p>
     <pre><code>def argsFunc(*my_args):
     print my_args

     &gt;&gt;&gt; argsFunc(1, 2, 3, 4)
     (1, 2, 3, 4)
     &gt;&gt;&gt; argsFunc()
     ()
     </code></pre>
     <p>很简单把，现在来将另一个种不定参数形式</p>
     <h3><a id="kwargs_65"></a>**kwargs</h3>
     <p>形参名前加两个*表示，参数在函数内部将被存放在以形式名为标识符的 <code>dictionary</code> 中，这时调用函数的方法则需要采用 <code>arg1=value1,arg2=value2</code> 这样的形式。</p>
     <p>为了区分，我把 *<strong>args</strong> 称作为数组参数，*<strong>*kwargs</strong> 称作为字典参数</p>
     <pre><code>&gt;&gt;&gt; def a(**x):print x
     &gt;&gt;&gt; a(x=1,y=2,z=3)
     {'y': 2, 'x': 1, 'z': 3} #存放在字典中
     </code></pre>
     <p>不过，有个需要注意，采用 **kwargs 传递参数的时候，你不能传递数组参数</p>
     <pre><code>&gt;&gt;&gt; a(1,2,3) #这种调用则报错
     Traceback (most recent call last):
     File "&lt;stdin&gt;", line 1, in &lt;module&gt;
     TypeError: a() takes exactly 0 arguments (3 given)
     </code></pre>
     <p>同样很简单，但是我们什么时候可以用到他呢？</p>
     <pre><code>import mysql.connector

     db_conf = {
     user='xx',
     password='yy',
     host='xxx.xxx.xxx.xxx',
     database='zz'
     }

     cnx = mysql.connector.connect(
     user=db_conf['user'],
     password=db_conf['password'],
     host=db_conf['host'],
     database=db_conf['database']
     )
     ...
     </code></pre>
     <p>相比，使用 Mysql Python 库时候，经常看到这个样子的代码，<code>db_conf</code> 一般都从配置文件读取，这是优雅的不定字典参数就派上用途了！</p>
     <pre><code>import mysql.connector

     db_conf = {
     user='xx',
     password='yy',
     host='xxx.xxx.xxx.xxx',
     database='zz'
     }

     cnx = mysql.connector.connect(**db_conf)
     ...
     </code></pre>
     <p>怎样，是不是顺眼多了，代码也省了不少！<sup>_</sup></p>
     <p>今天就到这里了，很早就开始写这一篇了，不想像网路上的大部分博客，只是写一个使用文档类型的教程，看完就忘了。</p>
     <p>适当的考虑应用场景，希望能印象深刻。学会，就尽可能的使用它；再优雅的概念，不用也是百搭。</p>
     <p>引用:<a href="https://n3xtchen.github.io/n3xtchen/python/2014/08/08/python-args-and-kwargs" rel="nofollow">https://n3xtchen.github.io/n3xtchen/python/2014/08/08/python-args-and-kwargs</a></p>
     <blockquote>
     <p>博主开发的第三方CSDN客户端.体验很棒哦，快来体验下载吧<br>
     <img src='https://csdnimg.cn/release/phoenix/write/assets/img_default.png' data-src='https://www.pgyer.com/app/qrcode/uEDg'  alt="在这里插入图片描述"></p>
     </blockquote>

     </div>
     <script src="https://csdnimg.cn/public/common/libs/jquery/jquery-1.9.1.min.js" type="text/javascript"></script>
     <script  type="text/javascript">
     $(function () {
     var observer = lozad('.article_content img', {
     rootMargin: '10px 0px', // syntax similar to that of CSS Margin
     threshold: 0.1 // ratio of element convergence
     });
     observer.observe();
     if($('div.markdown_views pre.prettyprint code.hljs').length > 0 ){
     $('div.markdown_views')[0].className = 'markdown_views ';
     }
     dp.SyntaxHighlighter.HighlightAll('pre');
     });
     </script>
     <script  type="text/javascript">
     $(function () {
     $(".MathJax").remove();
     dp.SyntaxHighlighter.HighlightAll('pre');
     });
     </script>
     <script type="text/x-mathjax-config">
     MathJax.Hub.Config({
     "HTML-CSS": {
     linebreaks: { automatic: true, width: "94%container" },
     imageFont: null
     },
     tex2jax: {
     preview: "none"
     },
     mml2jax: {
     preview: 'none'
     },
     messageStyle: "none"
     });
     </script>
     <script type="text/javascript" src="https://csdnimg.cn/release/blog_mathjax/MathJax.js?config=TeX-AMS-MML_HTMLorMML"></script>
     <script type="text/javascript" src="https://csdnimg.cn/release/phoenix/production/app_blog_article-24440deba9.js"></script></div>
     </body>
     </html>
     * Type : 1
     * Note : null
     * BlogId : 2056551
     * UpdateTime : 2018-11-27 21:29:42
     * CommentCount : 4
     * Level : 4
     */

    private String Description;
    private int CommentAuth;
    private String OutlinkCount;
    private String FileName;
    private String ChannelId;
    private boolean is_digg;
    private boolean IsTop;
    private String AvatarUrl;
    private String PostTime;
    private String ArticleId;
    private String ArticleUrl;
    private String Status;
    private String UserName;
    private int Digg;
    private String Bury;
    private String IP;
    private String Title;
    private int ViewCount;
    private String NickName;
    private String TypeCopy;
    private String TextBody;
    private int Type;
    private Object Note;
    private String BlogId;
    private String UpdateTime;
    private String CommentCount;
    private String Level;

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public int getCommentAuth() {
        return CommentAuth;
    }

    public void setCommentAuth(int CommentAuth) {
        this.CommentAuth = CommentAuth;
    }

    public String getOutlinkCount() {
        return OutlinkCount;
    }

    public void setOutlinkCount(String OutlinkCount) {
        this.OutlinkCount = OutlinkCount;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String FileName) {
        this.FileName = FileName;
    }

    public String getChannelId() {
        return ChannelId;
    }

    public void setChannelId(String ChannelId) {
        this.ChannelId = ChannelId;
    }

    public boolean isIs_digg() {
        return is_digg;
    }

    public void setIs_digg(boolean is_digg) {
        this.is_digg = is_digg;
    }

    public boolean isIsTop() {
        return IsTop;
    }

    public void setIsTop(boolean IsTop) {
        this.IsTop = IsTop;
    }

    public String getAvatarUrl() {
        return AvatarUrl;
    }

    public void setAvatarUrl(String AvatarUrl) {
        this.AvatarUrl = AvatarUrl;
    }

    public String getPostTime() {
        return PostTime;
    }

    public void setPostTime(String PostTime) {
        this.PostTime = PostTime;
    }

    public String getArticleId() {
        return ArticleId;
    }

    public void setArticleId(String ArticleId) {
        this.ArticleId = ArticleId;
    }

    public String getArticleUrl() {
        return ArticleUrl;
    }

    public void setArticleUrl(String ArticleUrl) {
        this.ArticleUrl = ArticleUrl;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public int getDigg() {
        return Digg;
    }

    public void setDigg(int Digg) {
        this.Digg = Digg;
    }

    public String getBury() {
        return Bury;
    }

    public void setBury(String Bury) {
        this.Bury = Bury;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public int getViewCount() {
        return ViewCount;
    }

    public void setViewCount(int ViewCount) {
        this.ViewCount = ViewCount;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String NickName) {
        this.NickName = NickName;
    }

    public String getTypeCopy() {
        return TypeCopy;
    }

    public void setTypeCopy(String TypeCopy) {
        this.TypeCopy = TypeCopy;
    }

    public String getTextBody() {
        return TextBody;
    }

    public void setTextBody(String TextBody) {
        this.TextBody = TextBody;
    }

    public int getType() {
        return Type;
    }

    public void setType(int Type) {
        this.Type = Type;
    }

    public Object getNote() {
        return Note;
    }

    public void setNote(Object Note) {
        this.Note = Note;
    }

    public String getBlogId() {
        return BlogId;
    }

    public void setBlogId(String BlogId) {
        this.BlogId = BlogId;
    }

    public String getUpdateTime() {
        return UpdateTime;
    }

    public void setUpdateTime(String UpdateTime) {
        this.UpdateTime = UpdateTime;
    }

    public String getCommentCount() {
        return CommentCount;
    }

    public void setCommentCount(String CommentCount) {
        this.CommentCount = CommentCount;
    }

    public String getLevel() {
        return Level;
    }

    public void setLevel(String Level) {
        this.Level = Level;
    }
}
