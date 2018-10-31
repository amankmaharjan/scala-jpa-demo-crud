if (window.console) {
  console.log("Welcome to your Play application's JavaScript!");
}
function sendDeleteRequest(url, rUrl, token) {
    $.ajax({
        url: url,
        method: "DELETE",
        data: {
            csrfToken: token
        },
        success: function () {
            window.location = rUrl;
        },
        error: function () {
            //window.location.reload();
        }
    });
}