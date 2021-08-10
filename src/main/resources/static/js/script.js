let failedNote

$("#plus").click(function () {
    let id = $("#id").val()
    $.ajax({
        type: "GET",
        url: "/api/model/plus/" + id,
        data: $("#values").serialize()
    }).done(function (data) {
        showResult(data.value, "green")
    }).fail(function (event) {
        failNoty(event)
    })
})

$("#minus").click(function () {
    let id = $("#id").val()
    $.ajax({
        type: "GET",
        url: "/api/model/minus/" + id,
        data: $("#values").serialize()
    }).done(function (data) {
        showResult(data.value, "red")
    }).fail(function (event) {
        failNoty(event)
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

function closeNoty() {
    if (failedNote) {
        failedNote.close()
        failedNote = undefined
    }
}

function failNoty(value) {
    closeNoty()
    let errorInfo = value["responseJSON"]
    failedNote = new Noty({
        text: errorInfo["error"] + "<br>" + errorInfo["message"] + "<br>",
        type: "error",
        layout: "bottomRight"
    }).show()
}
