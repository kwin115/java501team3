let map;
let markers = [];
let ajaxData = [];

// 목록 불러오기
$.ajax({
    url: "/main/hotPlaceJson",
    type: "POST",
    success: function (data) {
        const body = $("#tab1");
        body.empty();
        let tag = "";
        ajaxData = data;

        if (data.length > 0) {
            for (let i = 0; i < data.length; i++) {
                let favListStoreIdx=i;
                tag += `<div class="border-bottom mt-2">
                                    <a class="text-decoration-none text-dark" href="#" onclick="createMarker(${data[i].lat}, ${data[i].lng}, '${data[i].main_TITLE}','${i}')">
                                        <b>${data[i].main_TITLE}</b>
                                    </a>
                                    <span class="toggleButton noHeart" id="toggleButton${i}" onclick="toggleHeart(${favListStoreIdx})"></span>
                                    <div class="mt-2">
                                        <p>주소 : ${data[i].addr1}
                                        <br>메뉴 : ${data[i].rprsntv_MENU}<br></p>
                                        <a class="text-decoration-none text-dark" href="/main/detail?idx=${i}" id="details">상세보기</a>
                                        <br>
                                    </div>
                                </div>`;
            }
        }
        body.append(tag);
        myMap(data);
        addFavList(data);
    },
    error: function () {
        alert("통신 중 오류가 발생했습니다.");
    }
});

// 지도 블러오기
function myMap(data) {
    var mapOptions = {
        center: new google.maps.LatLng(35.19184, 129.01126),
        zoom: 11
    };

    map = new google.maps.Map(
        document.getElementById("googleMap"),
        mapOptions);

    addMarkers(data);
}

// 마커찍기 (여러개)
function addMarkers(data) {
    clearMarkers();

    for (let i = 0; i < data.length; i++) {
        const latitude = parseFloat(data[i].lat);
        const longitude = parseFloat(data[i].lng);
        const title = data[i].main_TITLE;
        var idx = i;

        addMarker(latitude, longitude, title, idx);
    }
}

// 마커찍기 (한개)
function addMarker(latitude, longitude, title, i) {
    var marker = new google.maps.Marker({
        position: new google.maps.LatLng(latitude, longitude),
        map: map,
        title: title
    });

    marker.addListener('click', function() {
        var modalBody = $("#markerModalBody");
        modalBody.empty();

        modalBody.append(`<a class="text-decoration-none" href="/main/detail?idx=${i}">${title}</a>`);

        $("#markerModal").modal("show");
        $("#markerModal .btn-close").click(function() {
            $("#markerModal").modal("hide");
        });
    });

    markers.push(marker);
}

function createMarker(latitude, longitude, title,i) {
    clearMarkers();

    addMarker(latitude, longitude, title,i);
}

function clearMarkers() {
    for (let i = 0; i < markers.length; i++) {
        markers[i].setMap(null);
    }

    markers = [];
}

// 즐겨찾기 표시, 추가/삭제
function toggleHeart(favListStoreIdx) {
    var button = document.getElementById(`toggleButton${favListStoreIdx}`);

    // 'heart' 클래스가 있을 때
    if (button.classList.contains('heart')) {
        // 'heart' 클래스를 제거
        button.classList.remove('heart');
        button.classList.add('noHeart');

        // AJAX를 이용해 찜 목록 삭제를 수행
        $.ajax({
            url: "/mainDeleteFav",
            type: "GET",
            data: {favListStoreIdx: favListStoreIdx},
            success: function (response) {
                console.log(response);
            },
            error: function () {
                alert("삭제 통신 중 오류가 발생했습니다.");
            }
        });
    }
    // 'noHeart' 클래스가 있을 때
    else {
        // 'noHeart' 클래스를 제거하고 'heart' 클래스를 추가
        button.classList.remove('noHeart');
        button.classList.add('heart');

        // AJAX를 이용해 찜 목록 추가를 수행
        $.ajax({
            url: "/mainAddFav",
            type: "GET",
            data: {favListStoreIdx: favListStoreIdx},
            success: function (response) {
                console.log(response);
            },
            error: function (error) {
                console.error("Ajax 요청 에러:", error);
            }
        });
    }
}


document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('tab' + tabIndex).classList.add('active');
});

// 목록/즐겨찾기 탭 변경
function changeTab(tabIndex) {
    document.querySelectorAll('.tab-content').forEach(function(content) {
        content.classList.remove('active');
    });
    document.getElementById('tab' + tabIndex).classList.add('active');

    if (tabIndex === 2) {
        const body = $("#tab2");
        body.empty();
        addFavList(ajaxData);
    }
}

// 즐겨찾기에서 지정한 목록 삭제
function toggleHeart2(favListStoreIdx) {
    var button = document.getElementById(`toggleButton${favListStoreIdx}`);
    var button2 = document.getElementById(`toggleButtonb${favListStoreIdx}`);

    if (button2.classList.contains('heart')) {
        button2.classList.remove('heart');
        button2.classList.add('noHeart');
        $.ajax({
            url: "/mainDeleteFav",
            type: "GET",
            data: {favListStoreIdx: favListStoreIdx},
            success: function (response) {
                console.log(response);
                changeTab(2);
                button.classList.remove('heart');
                button.classList.add('noHeart');
            },
            error: function (error) {
                alert("삭제 통신 중 오류가 발생했습니다.");
                console.error("Ajax 요청 에러:", error);
            }
        });
    }
}

// 즐겨찾기에서 목록 불러오기
function addFavList(ajaxData) {
    const body = $("#tab2");
    let tag = "";
    body.empty();

    // 서버에서 favList를 받아오는 AJAX 요청
    $.ajax({
        url: "/mainViewFavList",
        type: "GET",
        dataType: "json",
        success: function (favList) {
            // favList의 각 요소에 대해 동적으로 HTML 태그 생성
            favList.forEach(function (item) {
                // favList가 배열이라면 item을 직접 사용
                tag += `<div class="border-bottom mt-2">
                            <a class="text-decoration-none text-dark" href="#" onclick="createMarker(${ajaxData[item.favListStoreIdx].lat}, ${ajaxData[item.favListStoreIdx].lng}, '${ajaxData[item.favListStoreIdx].main_TITLE}','${item.favListStoreIdx}')">
                                <b>${ajaxData[item.favListStoreIdx].main_TITLE}</b>
                            </a>
                            <span class="toggleButton heart" id="toggleButtonb${item.favListStoreIdx}" onclick="toggleHeart2(${item.favListStoreIdx})"></span>
                            <div class="mt-2">
                                <p>주소 : ${ajaxData[item.favListStoreIdx].addr1}
                                <br>메뉴 : ${ajaxData[item.favListStoreIdx].rprsntv_MENU}<br></p>
                                <a class="text-decoration-none text-dark" href="/main/detail?idx=${item.favListStoreIdx}" id="details">상세보기</a>
                                <br>
                            </div>
                        </div>`;
            });

            // 생성된 HTML을 body에 추가
            body.append(tag);
        },
        error: function () {
            console.error("통신 중 오류가 발생했습니다.");
        }
    });
}

