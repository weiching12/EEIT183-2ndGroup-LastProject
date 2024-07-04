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

    var formData = new FormData();
    formData.append("propName", $("#propName").val()); // 道具名稱
    formData.append("propRarity", $("#propRarity").val()); // 道具稀有度
    formData.append("propDescription", $("#propDescription").val()); // 道具描述
    formData.append("propTypeId", $("#propTypeId").val()); // 道具種類ID
    formData.append("gameId", $("#SelectGame").val()); // 遊戲ID
    formData.append("propImage", $("#propImage")[0].files[0]); // 道具圖片文件
    saveProp(formData); // 保存道具
  });

  // 監聽刪除按鈕點擊事件
  $("#myTable").on("click", ".btn-danger", function () {
    if (window.confirm("確定要刪除嗎？")) {
      var propId = $(this).data("prop-id");
      deleteProp(propId); // 確認刪除道具
    } else {
      // 用戶取消刪除操作
      console.log("用戶取消刪除操作");
      // 或者可以在取消時做一些提示或其他操作
    }
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
      console.error("獲取遊戲數據時出錯:", error);
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
      console.error("獲取所有道具種類數據時出錯:", error);
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
      console.error("獲取道具種類數據時出錯:", error);
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
          `<button class="btn btn-danger" data-prop-id="${prop.propId}">刪除</button>`, // 刪除按鈕
        ];
        table.row.add(row);
      });
      table.draw();
    })
    .catch((error) => {
      console.error("獲取道具數據時出錯:", error);
    });
}

// 保存道具
function saveProp(formData) {
  axios
    .post("http://localhost:8080/PlayCentric/prop/saveProp", formData, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    })
    .then((res) => {
      console.log("道具已成功保存", res);
      $("#savePropModal").modal("hide");
      findAllPropsByGameId($("#SelectGame").val());
    })
    .catch((error) => {
      console.error("保存道具時出錯", error);
    });
}

// 刪除道具
function deleteProp(propId) {
  axios
    .delete("http://localhost:8080/PlayCentric/prop/deleteProp", {
      params: { id: propId }, // 這裡應該使用函數參數中的 propId，而不是 propIdToDelete
    })
    .then((res) => {
      console.log("道具已成功刪除", res);
      findAllPropsByGameId($("#SelectGame").val());
    })
    .catch((error) => {
      console.error("刪除道具時出錯", error);
    });
}

// 顯示新增道具的模態框
function savePropWindow() {
  $("#savePropForm")[0].reset(); // 使用原生 JavaScript 方法重置表單
  $("#savePropModal").modal("show");
}
