document.getElementById('frm-register').addEventListener("submit", function(event) {
    event.preventDefault();

    // 收集表单数据
    let formData = {
        username: document.getElementById('username').value,
        password: document.getElementById('password').value,
        phone: document.getElementById('phone').value,
        address: document.getElementById('address').value,
        birth: document.getElementById('birth').value
    };

    // 发送Ajax请求到后端Servlet
    let xhr = new XMLHttpRequest();
    xhr.open("POST", "RegisterServlet", true); // 修改为您的Servlet URL
    xhr.setRequestHeader("Content-Type", "application/json");

    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            let response = JSON.parse(xhr.responseText);
            if (response.success) {
                alert("注册成功！");
                window.location.href = "login.html"; // 注册成功后跳转
            } else {
                document.getElementById('message').innerText = "注册失败: " + response.message;
            }
        }
    };

    xhr.send(JSON.stringify(formData));
});