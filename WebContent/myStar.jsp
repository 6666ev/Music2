<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
		<%@ page import="java.util.ArrayList"%>
	<%@ page language="java" import="Data.SongData"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="renderer" content="webkit">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>我的收藏</title>

<link rel="stylesheet" href="./css/bootstrap.min.css">
<link rel="stylesheet" href="./css/font-awesome.min.css">
<link rel='stylesheet' href='https://cdn.plyr.io/3.5.6/plyr.css'>
<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet" href="./css/my.css">
<link rel="stylesheet" type="text/css" href="css/bootoast.css">

<%-- 加图标 --%>
<link rel="icon" href="img/favicon.ico" type="image/x-icon" />
<link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon" />
<link rel="bookmark" href="img/favicon.ico" type="image/x-icon" />

<script type="text/javascript">
	<% 
	String username = (String)request.getSession().getAttribute("name");
	String email = (String)request.getSession().getAttribute("email");
	%>
	
	</script>
</head>

<body>
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
					<li class="list-group-item"><i class="fa fa-assistive-listening-systems"
						aria-hidden="true"></i>听歌识曲</li>
				</a>
			</ul>
			<ul class="list-group list-group-flush">
				<h3 class="menu-title">我的音乐</h3>
				<a href="DownloadManager"><li class="list-group-item"><i
						class="fa fa-download" aria-hidden="true"></i>下载管理</li></a>
				<a href="MyStar"><li class="list-group-item active"><i
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

				<table class="table table-striped">
					<thead>
						<tr>
							<th></th>
							<th>操作</th>

							<th>音乐标题</th>
							<th>歌手</th>
							<th>专辑</th>
							<th>时长</th>

						</tr>
					</thead>
					<tbody>
						<%
						if(null != session.getAttribute("myStar"))
						{
						ArrayList <SongData> Datas = (ArrayList <SongData>)request.getSession().getAttribute("myStar"); 
						for(int i=0;i<Datas.size();i++)
						{ 
						int songid = Datas.get(i).getSongid();
							String strsongid =""+songid+"";
							System.out.println(strsongid);
						%>
						<tr>
							<th scope="row"><%=i+1%></th>
							<td>
								<a href='#'><span onclick='download("<%=strsongid%>","<%=email%>")'
										class="glyphicon glyphicon-download-alt" aria-hidden="true"></span></a>
								<a href="#"><span id="heart<%=i+1%>" 
										class="glyphicon glyphicon-heart"
										onclick='liked("<%=strsongid%>","<%=email%>","<%=i+1%>")'
										aria-hidden="true"></span></a> 
								<a href="#"><span class="glyphicon glyphicon-headphones" aria-hidden="true" onclick='listened("<%=strsongid%>","<%=email%>")'></span></a>
								
							<td><%=Datas.get(i).getSongName() %></td>
							<td><%=Datas.get(i).getSingerName() %></td>
							<td><%=Datas.get(i).getAlbumName() %></td>
							<td><%=Datas.get(i).getSongTime() %></td>
						
						</tr>
							<% 
							}
						}
						%>
					</tbody>
					
				</table>
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
<script type="text/javascript" src="js/bootoast.js"></script>

<script>

</script>


</html>