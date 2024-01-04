function login() {
    let xhr = new XMLHttpRequest();
    let url = "http://localhost:8080/login"; // 更改为您的Servlet URL
    xhr.open("POST", url, true);
    xhr.setRequestHeader("Content-Type", "application/json");

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            let response = JSON.parse(xhr.responseText);
            if (response.userId !== "111111") {
                document.cookie = "userId=" + response.userId;
                window.location.href = "index.html";
            } else {
                alert("登录失败");
            }
        }
    };

    let data = JSON.stringify({
        "username": document.forms["loginForm"]["username"].value,
        "password": document.forms["loginForm"]["password"].value
    });
    xhr.send(data);
}
