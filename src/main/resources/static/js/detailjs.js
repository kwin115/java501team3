let i = parseInt(document.getElementById('idxValue').getAttribute('data-idx'));

$.ajax({
    url: "/main/hotPlaceJson",
    type: "POST",
    success: function (data) {
        const body = $("#body");
        let tag ="";
        tag += `
                            <div class="container pb-5 text-center mt-5 my-auto">
                                <h1 id="title" style="font-size: 75px; font-weight: bold">${data[i].main_TITLE}</h1>
                            </div>
                            <div class="container" style="border: 1px solid #ddd; border-radius: 8px; padding: 20px; font-size: larger; background: white">
                                <div class="container mb-5" style="text-align: center; display: flex; justify-content: center; align-items: center;">
                                    <img src="${data[i].main_IMG_NORMAL}" style="width: 900px; height: 400px;">
                                </div>
                                <div class="container" style="padding-left: 100px; padding-right: 100px">
                                    <div class="pb-3">
                                        <label for="addr" class="rounded-2 mb-2"><img src="../sources/addr.png" style="width: 20px; height: 20px" class="mx-2">주소</label>
                                        <p id="addr" style="text-indent: 30px;">${data[i].addr1}</p>
                                    </div>
                                    <div class="pb-3">
                                        <label for="itemcntnts" class="rounded-2 mb-2"><img src="../sources/check.png" style="width: 20px; height: 20px" class="mx-2">소개</label>
                                        <p id="itemcntnts" style="padding-left: 30px;">${data[i].itemcntnts}</p>
                                    </div>
                                    <div class="pb-3">
                                        <label for="rprsntv_MENU" class="rounded-2 mb-2"><img src="../sources/cart.png" style="width: 20px; height: 20px" class="mx-2">메뉴</label>
                                        <p id="rprsntv_MENU" style="text-indent: 30px;">${data[i].rprsntv_MENU}</p>
                                    </div>
                                    <div class="pb-3">
                                        <label for="cntct_TEL" class="rounded-2 mb-2"><img src="../sources/tel.png" style="width: 20px; height: 20px" class="mx-2">문의</label>
                                        <p id="cntct_TEL" style="text-indent: 30px;">${data[i].cntct_TEL}</p>
                                    </div>
                                    <div class="pb-3">
                                        <label for="usage_DAY_WEEK_AND_TIME" class="rounded-2 mb-2"><img src="../sources/time.png" style="width: 20px; height: 20px" class="mx-2">운영시간</label>
                                        <p id="usage_DAY_WEEK_AND_TIME" style="text-indent: 30px;">${data[i].usage_DAY_WEEK_AND_TIME}</p>
                                    </div>
                                </div>
                            </div>`;
        body.append(tag);
        myMap(data);
        addMarker(data);
    },
    error: function () {
        alert("통신 중 오류가 발생했습니다.");
    }
});
function myMap(data) {
    var mapOptions = {
        center: new google.maps.LatLng(data[i].lat,data[i].lng),
        zoom: 15
    };

    map = new google.maps.Map(
        document.getElementById("googleMap"),
        mapOptions);

}
function addMarker(data) {
    var marker = new google.maps.Marker({
        position: new google.maps.LatLng(data[i].lat, data[i].lng),
        map: map,
        title: data[i].main_TITLE
    });

    markers.push(marker);
}

$(document).ready(function () {
    document.getElementById('showFormBtn').addEventListener('click', function () {
        var formContainer = document.getElementById('reviewFormContainer');
        formContainer.classList.toggle('hide');
    });
})

$.ajax({
    url: "/review/list",
    type: "GET",
    data: {idx : i},
    success: function (data) {
        const reviewList = $("#reviewList");
        let reviewTag ="";
        if (data.length > 0) {
            for (let j = 0; j < data.length; j++) {
                reviewTag +=    `<div class="border-top d">
                                        <div class="d-flex">
                                            <span class=" justify-content-start">${data[j].reviewUserId}</span> 
                                            <span class=" justify-content-end">${data[j].reviewCreateDate}</span>
                                        </div> 
                                    <div>
                                        <img src="/img/20231116_14492925.jpg"
                                    </div>
                                    내용:
                                    <span>${data[j].reviewContent}</span>
                                </div>`;
            }
        }
        reviewList.append(reviewTag);
    },
    error: function () {
        alert("리뷰리스트 로딩 중  오류가 발생했습니다.");
    }
})