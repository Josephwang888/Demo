<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
    <meta charset="UTF-8">
    <script type="text/javascript" src="js/jquery.js"></script>
    <title>香菇街——你的剁手街</title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/normalize/8.0.0/normalize.min.css">
    <link rel="stylesheet" href="css/main.css">
    <script src="js/slider.js" type="text/javascript"></script>
</head>
<head>

</head>
<body>
<script type="text/javascript">
    $(document).ready(function() {
        var length,
            currentIndex = 0,
            interval,
            hasStarted = false, //是否已经开始轮播
            t = 3000; //轮播时间间隔
        length = $('.slider-panel').length;
        //将除了第一张图片隐藏
        $('.slider-panel:not(:first)').hide();
        //将第一个slider-item设为激活状态
        $('.slider-item:first').addClass('slider-item-selected');
        //隐藏向前、向后翻按钮
        $('.slider-page').hide();
        //鼠标上悬时显示向前、向后翻按钮,停止滑动，鼠标离开时隐藏向前、向后翻按钮，开始滑动
        $('.slider-panel, .slider-pre, .slider-next').hover(function() {
            stop();
            $('.slider-page').show();
        }, function() {
            $('.slider-page').hide();
            start();
        });
        $('.slider-item').hover(function(e) {
            stop();
            var preIndex = $(".slider-item").filter(".slider-item-selected").index();
            currentIndex = $(this).index();
            play(preIndex, currentIndex);
        }, function() {
            start();
        });
        $('.slider-pre').unbind('click');
        $('.slider-pre').bind('click', function() {
            pre();
        });
        $('.slider-next').unbind('click');
        $('.slider-next').bind('click', function() {
            next();
        });
        /**
         * 向前翻页
         */
        function pre() {
            var preIndex = currentIndex;
            currentIndex = (--currentIndex + length) % length;
            play(preIndex, currentIndex);
        }
        /**
         * 向后翻页
         */
        function next() {
            var preIndex = currentIndex;
            currentIndex = ++currentIndex % length;
            play(preIndex, currentIndex);
        }
        /**
         * 从preIndex页翻到currentIndex页
         * preIndex 整数，翻页的起始页
         * currentIndex 整数，翻到的那页
         */
        function play(preIndex, currentIndex) {
            $('.slider-panel').eq(preIndex).fadeOut(500)
                .parent().children().eq(currentIndex).fadeIn(1000);
            $('.slider-item').removeClass('slider-item-selected');
            $('.slider-item').eq(currentIndex).addClass('slider-item-selected');
        }
        /**
         * 开始轮播
         */
        function start() {
            if(!hasStarted) {
                hasStarted = true;
                interval = setInterval(next, t);
            }
        }
        /**
         * 停止轮播
         */
        function stop() {
            clearInterval(interval);
            hasStarted = false;
        }
        //开始轮播
        start();
    });
</script>

<div class="top-nav">
      <div class="container clearf">
          <div class="fl">
              <a class="item" href="#">首页</a>
          </div>
          <div class="fr">
              <a class="item" href="#">我的订单</a>
              <a class="item" href="#">收藏夹</a>
              
              <a class="item" href="Tb_managerServlet?f=exit">退出</a>
          </div>
      </div>
   </div>
   <div class="header clearf">
       <div class="container">
           <div class="logo col_2">

           </div>
           <div class="col_5 search-bar">
               <input type="text"><button>搜索</button>
           </div>
           <div class="col_3 cart">
               <a href="#">我的购物车</a>
           </div>
       </div>
   </div>
   <div class="main-promorte clearf">
       <div class="container">
           <div class="col_2 cat">
               <div class="item">item / item</div>
               <div class="item">item / item</div>
               <div class="item">item / item</div>
               <div class="item">item / item</div>
               <div class="item">item / item</div>
               <div class="item">item / item</div>
               <div class="item">item / item</div>
               <div class="item">item / item</div>
               <div class="item">item / item</div>
           </div>
           <div class="col_6">
               <div class="slider">
                   <ul class="slider-main">
                       <li class="slider-panel">
                           <a href="#" target="_blank"><img alt="" title="" src="image/slider_01.png"></a>
                       </li>
                       <li class="slider-panel">
                           <a href="#" target="_blank"><img alt="" title="" src="image/002.png"></a>
                       </li>
                       <li class="slider-panel">
                           <a href="#" target="_blank"><img alt="" title="" src="image/001.png"></a>
                       </li>
                       <li class="slider-panel">
                           <a href="#" target="_blank"><img alt="" title="" src="image/slider_01.png"></a>
                       </li>
                   </ul>
                   <div class="slider-extra">
                       <ul class="slider-nav">
                           <li class="slider-item">1</li>
                           <li class="slider-item">2</li>
                           <li class="slider-item">3</li>
                           <li class="slider-item">4</li>
                       </ul>
                       <div class="slider-page">
                           <a class="slider-pre" href="javascript:;;"><</a>
                           <a class="slider-next" href="javascript:;;">></a>
                       </div>
                   </div>
               </div>

               <div class="sub-promote">
                   <div class="col_5">
                       <img src="image/001.png" alt="">
                   </div>
                   <div class="col_5">
                       <img src="image/002.png" alt="">
                   </div>
               </div>
           </div>
           <div class="col_2 info">
               <div class="auth clearf">
                   <div class="avatar"></div>
                   你好，欢迎 ${sessionScope.manager.manName}
               </div>
               <div class="auto">
                   <div class="title">公告</div>
                   <div class="content">
                       在IDEA中，默认的代码自动提示不够智能，现在配置成更加智能的方式。
                       File-Settings-Editor-General-Code Completion中
                       把最上面的大小写敏感度改成none，下面的钩全打。
                       在IDEA中，默认的代码自动提示不够智能，现在配置成更加智能的方式。
                       File-Settings-Editor-General-Code Completion中
                       把最上面的大小写敏感度改成none，下面的钩全打。
                       在IDEA中，默认的代码自动提示不够智能，现在配置成更加智能的方式。
                   </div>
               </div>
           </div>
       </div>
   </div>
   <div class="container">
       <div class="cat-promorte clearf">
           <div class="title">女装</div>
           <div class="content">
               <div class="col_2 item">
                   <div class="card"></div>
               </div>
               <div class="col_3 item">
                   <div class="card"></div>
               </div>
               <div class="col_2 item">
                   <div class="card"></div>
           </div>
               <div class="col_3 item">
                   <div class="card"></div>
               </div>
            </div>
       </div>
       <div class="cat-promorte clearf">
           <div class="title">女装</div>
           <div class="content">
               <div class="col_2 item">
                   <div class="card"></div>
               </div>
               <div class="col_3 item">
                   <div class="card"></div>
               </div>
               <div class="col_2 item">
                   <div class="card"></div>
               </div>
               <div class="col_3 item">
                   <div class="card"></div>
               </div>
           </div>
       </div>
       <div class="cat-promorte clearf">
           <div class="title">女装</div>
           <div class="content">
               <div class="col_2 item">
                   <div class="card"></div>
               </div>
               <div class="col_3 item">
                   <div class="card"></div>
               </div>
               <div class="col_2 item">
                   <div class="card"></div>
               </div>
               <div class="col_3 item">
                   <div class="card"></div>
               </div>
           </div>
       </div>
   </div>
   <div class="footer clearf">
       <div class="container">
           <a href="#">item</a>
           <a href="#">item</a>
           <a href="#">item</a>
           <a href="#">item</a>
           <div>  File-Settings-Editor-General-Code Completion中
               把最上面的大小写敏感度改成none，下面的钩全打。
           </div>

       </div>
   </div>
</body>
</html>