$('.form').find('input, textarea').on('keyup blur focus', function (e) {

  var $this = $(this),
    label = $this.prev('label');

  if (e.type === 'keyup') {
    if ($this.val() === '') {
      label.removeClass('active highlight');
    } else {
      label.addClass('active highlight');
    }
  } else if (e.type === 'blur') {
    if ($this.val() === '') {
      label.removeClass('active highlight');
    } else {
      label.removeClass('highlight');
    }
  } else if (e.type === 'focus') {

    if ($this.val() === '') {
      label.removeClass('highlight');
    }
    else if ($this.val() !== '') {
      label.addClass('highlight');
    }
  }

});

$('.tab a').on('click', function (e) {

  e.preventDefault();

  $(this).parent().addClass('active');
  $(this).parent().siblings().removeClass('active');

  target = $(this).attr('href');

  $('.tab-content > div').not(target).hide();

  $(target).fadeIn(600);

});


function registerTest() {
  var $register_email = $("#register_email").val();
  $.ajax({
    url: "RegisterServlet",
    type: "get",
    data: "register_email=" + $register_email,
    success: function (result) {
      if (result == "isRegistered") {
        $("#register_btn").attr("disabled", true);
        // alert("adsfff");
        bootoast({
          message: '该邮箱已被注册！',
          type: 'warning',
          position: 'top-center',
          timeout: 2
        });

      } else {
        $("#register_btn").removeAttr("disabled");
        bootoast({
          message: '该邮箱还未被注册',
          type: 'success',
          position: 'top-center',
          timeout: 2
        });
      }
    },
  })
}