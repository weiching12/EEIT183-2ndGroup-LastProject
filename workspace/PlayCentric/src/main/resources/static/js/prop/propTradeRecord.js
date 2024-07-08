$(document).ready(function () {
  const userId = "";
  // 初始化 DataTable
  let myTable = $("#TradeRecordTable").DataTable({
    language: {
      info: "顯示第 _START_ 到第 _END_ 筆，共 _TOTAL_ 筆記錄", // 設置顯示的信息
    },
  });

  const selGameSub = document.querySelector("#selGameSub");
  selGameSub.addEventListener("click", (event) => {
    event.preventDefault(); // 取消 form 預設的送出 (form 內有 submit 要寫，不然表單會送出)
    let SelectGame = document.querySelector("#SelectGame");
    let selectedOption = SelectGame.options[SelectGame.selectedIndex];
    const selectedGameId = selectedOption.value;
    console.log("gameId:" + selectedGameId);

    // 根據遊戲Id找賣單
    axios({
      method: "get",
      url: "http://localhost:8080/PlayCentric/prop/findAllpropSellOrder",
      params: {
        gameId: selectedGameId,
      },
    })
      .then((res) => {
        console.log(res.data); // 打印接收到的数据
      })
      .catch((err) => {
        console.log(err);
      });
  });

  // 根據遊戲Id找買單
  axios({
    method: "get",
    url: "http://localhost:8080/PlayCentric/prop/findAllpropSellOrder",
    params: {
      gameId: selectedGameId,
    },
  })
    .then((res) => {
      console.log(res.data); // 打印接收到的数据
    })
    .catch((err) => {
      console.log(err);
    });

  // 0.25秒後(等待game讀取完)自動點擊 selGameSub 按鈕一次
  setTimeout(() => {
    selGameSub.click();
  }, 250);
});
