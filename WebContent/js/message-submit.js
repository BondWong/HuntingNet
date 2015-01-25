$("#message-submit").click(
		function(e) {
			e.preventDefault();
			var name = $("#sender-name").val();
			var emailAddr = $("#sender-email-addr").val();
			var message = $("#sender-message").val();

			if (name.length == 0) {
				$("#message-error-alert").empty().append("请输入您的尊称");
				$("#message-error-alert").fadeIn(500).fadeOut(3000);
			} else if (emailAddr.length == 0) {
				$("#message-error-alert").empty().append("请输入您的邮箱");
				$("#message-error-alert").fadeIn(500).fadeOut(3000);
			} else if (message.length == 0) {
				$("#message-error-alert").empty().append("请输入您的信息");
				$("#message-error-alert").fadeIn(500).fadeOut(3000);
			} else {
				$("#message-response-img").attr("src",
						"images/message-submitting.gif");
				$("#message-response-img").fadeIn(100);
				$("#sender-name").val("");
				$("#sender-email-addr").val("");
				$("#sender-message").val("");
				$.ajax({
					type : "POST",
					url : "app/mail/create",
					contentType : "application/json",
					dataType : "json",
					data : JSON.stringify({
						"name" : name,
						"addr" : emailAddr,
						"message" : message
					}),
					success : function(data, status, jqXHR) {
						$("#message-response-img").attr("src",
								"images/message-ok.png");
						$("#message-response-img").fadeOut(2000);
					},
					fail : function(data, status, jqXHR) {
						$("#message-error-alert").empty().append(
								"服务器出错，我们将马上修复");
						$("#message-error-alert").fadeIn(500).fadeOut(3000);
					}
				});
			}
		});