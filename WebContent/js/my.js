var player = new Plyr('#audio1', {
    controls: [
        'restart',
        'play',
        'progress',
        'current-time',
        'duration',
        'mute',
        'volume',
        'download'
    ]
});

var audio = $('#audio1').get(0);
// audio.src = "audio/dummy-audio.mp3";
audio.src = "http://music.163.com/song/media/outer/url?id=28528999.mp3";

function updateDownload(source) {
    $('a[data-plyr="download"]').attr('href', source);
}
//--------------------------------------

function sleep(seconds, callback) {
    setTimeout(callback, seconds)
}



function listened(songid, email) {
    var audio = $('#audio1').get(0);
    var source = "http://music.163.com/song/media/outer/url?id=" + songid + ".mp3"
    audio.src = source;
    audio.play();
    updateDownload(source);


    $.getJSON(
        "AddListened",
        { "songid": songid, "email": email },
        function (result) {
            // 修改左下角song info
            var song_name = $('.music2-title').get(0);
            var song_singer = $('.music2-user').get(0);
            var song_pic = $('.music2-avatar').get(0);

            song_pic.src = "img/album/" + result.Songid + ".jpg";
            song_name.innerHTML = result.SongName;
            song_singer.innerHTML = result.SingerName;
        }
    );
}

function show_song_info(songid, songname, singername, picURL) {
    var audio = $('#audio1').get(0);
    audio.src = "http://music.163.com/song/media/outer/url?id=" + songid + ".mp3";
    audio.play();

    var song_name = $('.music2-title').get(0);
    var singer_name = $('.music2-user').get(0);
    var song_pic = $('.music2-avatar').get(0);

    song_name.innerHTML = songname;
    singer_name.innerHTML = singername;
    song_pic.src = picURL;
}

function download(songid, email) {
    $.getJSON(
        "AddDownload",
        { "songid": songid, "email": email },
        function (result) {
            bootoast({
                message: '已加入下载',
                type: 'success',
                position: 'top-center',
                timeout: 2
            });
        }
    );
}

function delete_download(songid, email) {
    $.getJSON(
        "DeleteDownload",
        { "songid": songid, "email": email },
        function (result) {
            bootoast({
                message: '已经删除',
                type: 'success',
                position: 'top-center',
                timeout: 2
            });
        }
    );
}

function liked(songid, email, id) {

    var heart = document.getElementById("heart" + id);
    // console.log(id);
    if (heart.className == "glyphicon glyphicon-heart") {
        //    	alert("heart->empty");
        heart.className = "glyphicon glyphicon-heart-empty";
        $.getJSON(
            "DeleteLiked",
            { "songid": songid, "email": email },
            function (result) {
                if (result.msg == "true") {
                    bootoast({
                        message: '已移出收藏',
                        type: 'success',
                        position: 'top-center',
                        timeout: 2
                    });
                    $('.glyphicon-heart-empty').parent().parent().parent().hide();
                    // console.log(heart);
                } else {
                    //                    alert("delete liked false");
                }
            }
        );
    } else if (heart.className == "glyphicon glyphicon-heart-empty") {
        //    	alert("empty->heart");
        heart.className = "glyphicon glyphicon-heart";
        $.getJSON(
            "AddLiked",
            { "songid": songid, "email": email },
            function (result) {
                if (result.msg == "true") {
                    bootoast({
                        message: '已加入收藏',
                        type: 'success',
                        position: 'top-center',
                        timeout: 2
                    });
                } else {
                    //                    alert("add liked false");
                }
            }
        );
    } else {
        alert("wronggggggg");
    }

}

function getjson() {
    var $mobile = $("#mobile").val();
    $.getJSON(
        "TestAjax",
        { "mobile": $mobile },
        function (result) {
            if (result.msg == "true") {
                alert("json-true");
            } else {
                alert("json-false");
            }
        }
    );
}