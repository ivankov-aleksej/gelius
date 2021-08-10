let id = 1

$("#plus").click(function () {
    $.ajax({
        type: "GET",
        url: "/api/model/plus/" + id,
        data: $("#values").serialize()
    }).done(function (data) {
        showResult(data.value, "green")
    })
})

$("#minus").click(function () {
    $.ajax({
        type: "GET",
        url: "/api/model/minus/" + id,
        data: $("#values").serialize()
    }).done(function (data) {
        showResult(data.value, "red")
    })
})

function showResult(value, color) {
    let item = $("#okno")
    if (value > 0) {
        item.removeClass("run-animation").removeAttr("style")
        item.width()

        item.addClass("run-animation")
            .css("background-color", color)
            .css("animation-iteration-count", value)
            .css("webkit-animation-iteration-count", value)
    }

    item.on(
        "webkitAnimationEnd animationEnd",
        function () {
            item.removeClass("run-animation").removeAttr("style")
            item.width()
        }
    )
}

