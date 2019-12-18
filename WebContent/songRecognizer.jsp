<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="java.util.ArrayList,utils.contain"%>
	<%@ page language="java" import="Data.SongData"  %>
<jsp:useBean id="titles" class ="java.util.ArrayList" scope ="session"></jsp:useBean>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="renderer" content="webkit">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="./css/bootstrap.min.css">
<link rel="stylesheet" href="./css/font-awesome.min.css">
<link rel='stylesheet' href='https://cdn.plyr.io/3.5.6/plyr.css'>
<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet" href="./css/my.css">
<link rel="stylesheet" href="./css/upload.css">
<link rel="stylesheet" type="text/css" href="css/bootoast.css">

<script type="text/javascript">
	<% 
	String username = (String)request.getSession().getAttribute("name");	
    String email = (String)request.getSession().getAttribute("email");
    String song=(String)request.getSession().getAttribute("song");
    String singer=(String)request.getSession().getAttribute("singer");
    if(song==null){
    	song=" ";
    	singer=" ";
    }
	%>
	
	</script>
</head>

<body>
<label name="test" hidden="hidden"><%=username %></label>  
  
	<nav class="navbar navbar-expand-lg navbar-dark"> <a
		class="navbar-brand" href="#"> <img src="img/logo.png"
		class="nav-logo"> 云音乐
	</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<div class="navbar-nav mr-auto">
			<ul class="pagination pagination-sm btn2">
				<li class="page-item"><a class="page-link" href="#"><i
						class="fa fa-chevron-left"></i></a></li>
				<li class="page-item"><a class="page-link" href="#"><i
						class="fa fa-chevron-right"></i></a></li>
			</ul>
			<form class="form-inline" action="SearchServlet" method="post">
				<input class="form-control mr-sm-2 input2"  name="searchname" type="search" 
					placeholder="搜索音乐，歌手，专辑..." aria-label="Search">
			</form>
		</div>
		<div class="dropdown avatar2">
			<a class="avatar" href="#" role="button"
				id="dropdownMenuLink" data-toggle="dropdown"
				aria-haspopup="true" aria-expanded="false"> <img
				src="./img/avatar.jpg"> <span id="username"><%=username%></span> <i
				class="fa fa-sort-down"></i>
			</a>

			<div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
				<a class="dropdown-item" href="#">Action</a> <a
					class="dropdown-item" href="#">Another action</a> <a
					class="dropdown-item" href="#">Something else here</a>
			</div>
		</div>
		<ul class="navbar-nav">
			<li class="nav-item active"><a class="nav-link" href="#"><i
					class="fa fa-cog fa-fw"></i></a></li>
			<li class="nav-item active"><a class="nav-link" href="#"><i
					class="fa fa-home fa-fw"></i></a></li>
			<li class="nav-item active"><a class="nav-link" href="#"><i
					class="fa fa-book fa-fw"></i></a></li>
		</ul>
	</div>
	</nav>
	<div class="main">
		<div class="menu">
			<ul class="list-group list-group-flush">
				<h3 class="menu-title">推荐</h3>
				<a href="FindMusic">
					<li class="list-group-item"><i class="fa fa-music"
						aria-hidden="true"></i>发现音乐</li>
				</a>
				<a href="SongRecognizer">
					<li class="list-group-item active"><i class="fa fa-assistive-listening-systems"
						aria-hidden="true"></i>听歌识曲</li>
				</a>
			</ul>
			<ul class="list-group list-group-flush">
				<h3 class="menu-title">我的音乐</h3>
				<a href="DownloadManager"><li class="list-group-item"><i
						class="fa fa-download" aria-hidden="true"></i>下载管理</li></a>
				<a href="MyStar"><li class="list-group-item"><i
						class="fa fa-star" aria-hidden="true"></i>我的收藏</li></a>
			</ul>
			<ul class="list-group list-group-flush">
				<h3 class="menu-title">听歌历史</h3>
				<a href="MyListened"><li class="list-group-item"><i
						class="fa fa-heartbeat" aria-hidden="true"></i>我听过的音乐</li></a>
			</ul>
			<ul class="list-group list-group-flush">
				<h3 class="menu-title">热门歌单</h3>
				<a href="HotSongs"><li class="list-group-item"><i
						class="fa fa-music" aria-hidden="true"></i>排行榜</li></a>
			</ul>
		</div>
		<div class="body">
			
            <div class="container">
				<div class="upload">
					<div class="upload-files">
                    <form method="post" action="TestUpload" enctype="multipart/form-data">
						<div id="drop">
							<i class="fa fa-file-text-o pointer-none" aria-hidden="true"></i>
							<p class="pointer-none" style="font-size: 20px;"><b>Drag and drop</b> files here <br /> or
								<a href="" id="triggerFile">browse</a> to
								begin the upload</p>
							<input type="file" multiple="multiple" name="uploadFile"/>
						</div>
						<footer>
							<div class="divider">
								<span>
									<AR>FILES</AR>
								</span>
							</div>
							<div class="list-files">
								<!--   template   -->
							</div>

							<button class="importar" type="submit">UPDATE FILES</button>
						</footer>
                        </form>
					</div>
				</div>
			</div>


			<div class="xunfei">
				<div id="">歌曲名：<%=song %></div><br>
				<div id="">歌手：<%=singer %></div>
				<div id=""></div>
			</div>
		</div>
	</div>
	<div class="music2">
		<div class="left-avatar">
			<img src="./img/album.jpg" class="music2-avatar" />
		</div>
		<div class="right-info">
			<p class="music2-title">A Sky Full Of Stars</p>
			<p class="music2-user">Coldplay</p>
		</div>
	</div>
	<div class="footer col-md-12">
		<audio id="audio1" preload controls>
            Your browser does not support the audio element.
        </audio>
	</div>
</body>

<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js'></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/html5media/1.1.8/html5media.min.js'></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/plyr/3.5.6/plyr.min.js'></script>
<script src="./js/player.js"></script>
<script src="./js/jquery.min.js"></script>
<script src="./js/bootstrap.min.js"></script>
<script src="./js/my.js"></script>
<script src="./js/upload.js"></script>
<script type="text/javascript" src="js/bootoast.js"></script>

</html>