let i = parseInt(document.getElementById('idxValue').getAttribute('data-idx'));

$.ajax({
    url: "/main/hotPlaceJson",
    type: "POST",
    success: function (data) {
        const body = $("#body");
        let tag ="";
        tag += `
                            <div class="mt-2">
                                <p>상호명 : ${data[i].main_TITLE}</p>
                                <p>주소 : ${data[i].addr1}</p>
                                <p>소개 : ${data[i].itemcntnts}</p>
                                <p>메뉴 : ${data[i].rprsntv_MENU}</p>
                                <p>문의 : ${data[i].cntct_TEL}</p>
                                <p>운영시간 : ${data[i].usage_DAY_WEEK_AND_TIME}</p>
                                <img src="${data[i].main_IMG_NORMAL}" style="width: 500px; height: 300px;">
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