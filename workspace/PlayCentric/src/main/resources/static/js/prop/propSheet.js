// 初始化 DataTable 並設置語言選項
var table;

$(document).ready(function () {
  // 初始化 DataTable 並設置語言選項
  table = $("#myTable").DataTable({
    language: {
      info: "顯示第 _START_ 到第 _END_ 筆，共 _TOTAL_ 筆記錄", // 設置顯示的信息
    },
  });

  // 加載所有遊戲和道具種類
  loadGames();
  loadAllPropTypes();

  // 提交按鈕點擊事件
  $("#submitBtn").click(function (event) {
    event.preventDefault(); // 取消表單的默認提交行為
    const selectedGameId = $("#SelectGame").val(); // 獲取選中的遊戲ID
    fetchPropTypesByGameId(selectedGameId, function (propTypes) {
      populatePropTypeSelect(propTypes); // 填充道具種類選單
      propTypes.forEach(function (propType) {
        propTypesMap[propType.propTypeId] = propType.propType; // 填充 propTypesMap
      });
      findAllPropsByGameId(selectedGameId); // 查找所有道具
    });
  });

  // 新增道具表單提交事件
  $("#savePropForm").submit(function (event) {
    event.preventDefault(); // 防止表單的默認提交行為
    const prop = {
      propName: $("#propName").val(), // 道具名稱
      propRarity: $("#propRarity").val(), // 道具稀有度
      propDescription: $("#propDescription").val(), // 道具描述
      propImageId: $("#propImageId").val(), // 道具圖片ID
      propTypeId: $("#propTypeId").val(), // 道具種類ID
      gameId: $("#SelectGame").val(), // 遊戲ID
      createdTime: new Date().toISOString(), // 當前時間
    };
    saveProp(prop); // 保存道具
  });
});

// 存儲道具種類的映射
const propTypesMap = {};

// 加載所有遊戲
function loadGames() {
  axios
    .get("http://localhost:8080/PlayCentric/prop/findAllGame")
    .then(function (res) {
      const games = res.data;
      const selectGame = $("#SelectGame");
      selectGame.empty();
      games.forEach(function (game) {
        const option = $("<option></option>")
          .val(game.gameId)
          .text(game.gameName);
        selectGame.append(option);
      });

      if (games.length > 0) {
        selectGame.val(games[0].gameId);
        $("#submitBtn").click();
      }
    })
    .catch(function (error) {
      console.error("Error fetching game data:", error);
    });
}

// 加載所有道具種類
function loadAllPropTypes() {
  axios
    .get("http://localhost:8080/PlayCentric/prop/findAllPropTypes")
    .then(function (res) {
      const propTypes = res.data;
      propTypes.forEach(function (propType) {
        propTypesMap[propType.propTypeId] = propType.propType;
      });
    })
    .catch(function (error) {
      console.error("Error fetching all prop types data:", error);
    });
}

// 根據 gameId 獲取所有道具種類
function fetchPropTypesByGameId(gameId, callback) {
  axios
    .get("http://localhost:8080/PlayCentric/prop/findAllPropTypesByGameId", {
      params: { gameId: gameId },
    })
    .then((res) => {
      callback(res.data);
    })
    .catch((error) => {
      console.error("Error fetching prop types data:", error);
    });
}

// 填充道具種類選單
function populatePropTypeSelect(propTypes) {
  const propTypeSelect = $("#propTypeId");
  propTypeSelect.empty();
  propTypes.forEach(function (propType) {
    const option = $("<option></option>")
      .val(propType.propTypeId)
      .text(propType.propType);
    propTypeSelect.append(option);
  });
}

// 根據 gameId 查找所有道具
function findAllPropsByGameId(gameId) {
  axios
    .get("http://localhost:8080/PlayCentric/prop/findAllPropsByGameId", {
      params: { gameId: gameId },
    })
    .then((res) => {
      const props = res.data;
      table.clear();
      props.forEach(function (prop) {
        const row = [
          prop.propId, // 道具ID
          prop.propName, // 道具名稱
          `<img src="${prop.propImageId}" alt="${prop.propImageId}" width="50">`, // 道具圖片
          propTypesMap[prop.propTypeId] || "未知", // 道具種類名稱
          prop.propRarity, // 道具稀有度
          prop.propDescription, // 道具描述
          prop.createdTime, // 創建時間
          prop.updatedTime, // 更新時間
          '<button class="btn btn-primary">修改</button>', // 修改按鈕
          '<button class="btn btn-danger">刪除</button>', // 刪除按鈕
        ];
        table.row.add(row);
      });
      table.draw();
    })
    .catch((error) => {
      console.error("Error fetching props data:", error);
    });
}

// 保存道具
function saveProp(prop) {
  axios
    .post("http://localhost:8080/PlayCentric/prop/saveProp", prop)
    .then((res) => {
      console.log("道具已成功保存", res);
      $("#savePropModal").modal("hide");
      findAllPropsByGameId($("#SelectGame").val());
    })
    .catch((error) => {
      console.error("保存道具時出錯", error);
    });
}

// 顯示新增道具的模態框
function savePropWindow() {
  $("#savePropModal").modal("show");
}
