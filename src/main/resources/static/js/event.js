window.onload = function() {

    /* 화면에 랜더링 된 태그들이 존재하지 않는 경우 에러 발생 가능성이 있어서 if문으로 태그가 존재하는지 부터 확인하고 이벤트를 연결한다. */
    if (document.getElementById("join")) {
        const $regist = document.getElementById("join");
        $regist.onclick = function () {
            location.href = "/member/join";
        }
    }

    if (document.getElementById("duplicationCheck")) {

        const $duplication = document.getElementById("duplicationCheck");

        $duplication.onclick = function () {
            let memberId = document.getElementById("memberId").value.trim();

            fetch("/member/idDupCheck", {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json;charset=UTF-8'
                },
                body: JSON.stringify({memberId: memberId})
            })
                .then(result => result.text())
                .then(result => alert(result))
                .catch((error) => error.text().then((res) => alert(res)));

        }
    }

    if (document.getElementById("login")) {
        const $login = document.getElementById("login");
        $login.onclick = function () {
            location.href = "/member/login";
        }
    }

    if (document.getElementById("logout")) {
        const $logout = document.getElementById("logout");
        $logout.onclick = function () {
            location.href = "/member/logout";
        }
    }

    if (document.getElementById("updateMember")) {
        const $update = document.getElementById("updateMember");
        $update.onclick = function () {
            location.href = "/member/info";
        }
    }

    if (document.getElementById("deleteMember")) {
        const $update = document.getElementById("deleteMember");
        $update.onclick = function () {
            location.href = "/member/leave";
        }
    }
}